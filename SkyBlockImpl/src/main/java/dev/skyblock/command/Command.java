package dev.skyblock.command;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import dev.skyblock.command.exception.IllegalCommandArgException;
import dev.skyblock.command.exception.IllegalCommandUsageException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Command {

    /**
     * A handy-dandy method for automatically converting a command instance into a usage string.
     */
    protected static final Function<Command, String> GET_USAGE_FUNCTION = command -> getUsage(command);
    private static final String EMPTY = "";

    private final String name;
    private final Set<Command> subCommands;
    private final Set<String> aliases;
    private final String usage;

    private String description;

    private String permission;
    private boolean[] permittedSources;

    private int minArgs;
    private int maxArgs;

    /**
     * Represents a command.
     *
     * @param name Name of the command.
     * @param permission The optional permission node that is required to execute this command.
     */
    public Command(String name, @Nullable String permission) {
        this.name = name;
        this.subCommands = Sets.newHashSet();
        this.aliases = Sets.newHashSet();
        this.usage = this.getCommandUsage();

        this.description = EMPTY;

        this.permission = permission == null ? EMPTY : permission;
        this.permittedSources = new boolean[] {
          true, true, true
        };

        this.minArgs = 0;
        this.maxArgs = 0;
    }

    /**
     * Adds an array of subcommands to this command.
     *
     * @param commands Commands to register as subcommands.
     *
     * @return This command instance.
     */
    public Command addSubCommands(Command... commands) {
        this.subCommands.addAll(Stream.of(commands).collect(Collectors.toList()));

        return this;
    }

    /**
     * Adds an array of command aliases to this command.
     *
     * @param aliases Aliases to register.
     *
     * @return This command instance.
     */
    public Command addAliases(String... aliases) {
        this.aliases.addAll(Stream.of(aliases).collect(Collectors.toList()));

        return this;
    }

    /**
     * Sets the description of this command.
     *
     * @param description Description of this command.
     *
     * @return This command instance.
     */
    public Command setDescription(String description) {
        this.description = description;

        return this;
    }

    /**
     * Sets the permission node required to execute this command.
     *
     * @param permission Permission node.
     *
     * @return This command instance.
     */
    public Command setPermission(String permission) {
        this.permission = permission;

        return this;
    }

    /**
     * Adds an array of permitted CommandSources to this command.
     *
     * @param sources Sources to add.
     *
     * @return This command instance.
     */
    public Command addPermittedSources(CommandSource... sources) {
        Stream.of(sources).forEach(src -> this.permittedSources[src.ordinal()] = true);

        return this;
    }

    /**
     * Removes an array of permitted CommandSources from this command.
     *
     * @param sources Source to remove.
     *
     * @return This command instance.
     */
    public Command removePermittedSources(CommandSource... sources) {
        Stream.of(sources).forEach(src -> this.permittedSources[src.ordinal()] = false);

        return this;
    }

    /**
     * Sets the minimum arguments required to execute this command.
     *
     * @param minArgs Minimum arguments.
     *
     * @return This command instance.
     *
     * @throws IllegalArgumentException If the {@param minArgs} parameter is below 0.
     */
    public Command setMinArgs(int minArgs)  {
        if (minArgs < 0) {
            throw new IllegalArgumentException("Minimum arguments cannot be set below 0!");
        }

        this.minArgs = minArgs;

        return this;
    }

    /**
     * Sets the maximum arguments required to execute this command.
     *
     * @param maxArgs Maximum arguments.
     *
     * @return This command instance.
     */
    public Command setMaxArgs(int maxArgs) {
        this.maxArgs = maxArgs;

        return this;
    }

    /**
     * @return The name of this command.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The subcommands registered against this command.
     */
    public Set<Command> getSubCommands() {
        return Collections.unmodifiableSet(this.subCommands);
    }

    /**
     * @return The aliases registered against this command.
     */
    public Set<String> getAliases() {
        return Collections.unmodifiableSet(this.aliases);
    }

    /**
     * @return The usage of this command.
     */
    public String getUsage() {
        return (this.usage == null || this.usage.isEmpty()) ? GET_USAGE_FUNCTION.apply(this) : this.usage;
    }

    /**
     * @return The description of this command.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return The permission node required to execute this command.
     */
    public String getPermission() {
        return this.permission;
    }

    /**
     * @return The CommandSources that are allowed to execute this command.
     */
    public CommandSource[] getPermittedSources() {
        CommandSource[] sources = new CommandSource[3];
        for (int i = 0; i < this.permittedSources.length; i++) {
            if (this.permittedSources[i]) {
                sources[i] = CommandSource.values()[i];
            }
        }

        return sources;
    }

    /**
     * @return {@code true} If player's are allowed to execute this command.
     */
    public boolean isPlayerPermitted() {
        return this.permittedSources[0];
    }

    /**
     * @return {@code true} If the console is allowed to execute this command.
     */
    public boolean isConsolePermitted() {
        return this.permittedSources[1];
    }

    /**
     * @return {@code true} If command blocks are allowed to execute this command.
     */
    public boolean isCommandBlockPermitted() {
        return this.permittedSources[2];
    }

    /**
     * @return The minimum arguments required to execute this command.
     */
    public int getMinArgs() {
        return this.minArgs;
    }

    /**
     * @return The maximum arguments required to execute this command.
     */
    public int getMaxArgs() {
        return this.maxArgs;
    }

    /**
     * @return This command as a BukkitCommand.
     */
    @SuppressWarnings("all")
    public BukkitCommand asBukkitCommand() {
        return new BukkitCommand(this.name, this.description, this.getUsage(), Lists.newArrayList(this.aliases)) {
            @Override
            public boolean execute(CommandSender sender, String label, String[] args) {
                Command.this.fire(sender, args);
                return false;
            }
        };
    }

    private void fire(CommandSender sender, String... args) {
        if (args.length == 0) {
            this.runChecks(sender, args);
            return;
        }

        Command command = this.subCommands.stream()
          .filter(c -> c.getName().equalsIgnoreCase(args[0]))
          .findFirst()
          .orElse(null);

        if (command == null) {
            this.runChecks(sender, args);
            return;
        }

        command.fire(sender, Stream.of(args).skip(1).toArray(String[]::new));
    }

    private void runChecks(CommandSender sender, String... args) {
        CommandSource source = CommandSource.fromSender(sender);

        if (!this.permittedSources[source.ordinal()]) {
            sender.sendMessage(ChatColor.RED + "This command cannot be executed by your account type");
            return;
        }

        if (source == CommandSource.PLAYER && !sender.hasPermission(this.permission)) {
            sender.sendMessage(ChatColor.RED + "You have insufficient permissions to execute this command!");
            return;
        }

        if (args.length > this.maxArgs) {
            sender.sendMessage(ChatColor.RED + "You have specified too many arguments to execute this command!");
            return;
        }

        if (args.length < this.minArgs) {
            sender.sendMessage(ChatColor.RED + "You have not specified enough arguments to execute this command!");
            return;
        }

        try {
            this.execute(sender, args);
        } catch (Exception e) {
            if (e instanceof IllegalCommandArgException) {
                e.printStackTrace();
                IllegalCommandArgException ex = (IllegalCommandArgException) e;

                sender.sendMessage(ChatColor.RED + "You have specified the wrong argument type for, " + ex.getArgumentName() +
                  ", expected a " + ex.getRequiredType().getSimpleName() + "!");
                return;
            }

            if (e instanceof IllegalCommandUsageException) {
                e.printStackTrace();
                sender.sendMessage(ChatColor.RED + "Incorrect command usage, the correct usage is:\n" + ((IllegalCommandUsageException) e).getUsage());
                return;
            }

            e.printStackTrace();
        }
    }

    /**
     * Code to execute when this command has completed all checks.
     *
     * @param sender The sender of the command.
     * @param args The arguments that the sender has sent alongside this command.
     *
     * @throws Exception On specific use cases such as incorrect argument types or incorrect usage.
     */
    protected abstract void execute(CommandSender sender, String... args) throws Exception;

    /**
     * @return The command usage to accompany this command. If none, it defaults to invoking {@param GET_USAGE_FUNCTION}.
     */
    protected String getCommandUsage() {
        return GET_USAGE_FUNCTION.apply(this);
    }

    private static String getUsage(Command command) {
        StringBuilder builder = new StringBuilder();

        builder
          .append("/")
          .append(command.getName())
          .append(" ");

        if (command.getSubCommands() != null && command.getSubCommands().size() >= 1) {
            StringBuilder subBuilder = new StringBuilder();
            String cmds = command.getSubCommands().stream().map(cmd -> cmd.getName() + "/").collect(Collectors.joining());

            subBuilder.append("<").append(cmds, 0, cmds.length() - 1).append("> ");
            builder.append(subBuilder.toString());
        }

        String usage = builder.toString();
        return usage.substring(0, usage.length() - 1);
    }
}
