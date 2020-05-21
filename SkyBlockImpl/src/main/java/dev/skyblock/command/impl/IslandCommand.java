package dev.skyblock.command.impl;

import dev.skyblock.command.Command;
import dev.skyblock.command.CommandSource;
import dev.skyblock.command.impl.management.IslandCreateCommand;
import dev.skyblock.command.impl.management.IslandResetCommand;
import org.bukkit.command.CommandSender;

public class IslandCommand extends Command {

    /**
     * Represents a command.
     */
    public IslandCommand() {
        super("island", "skyblock.island");

        this.setDescription("Main command for managing islands.");
        this.addAliases("is");
        this.removePermittedSources(
          CommandSource.COMMAND_BLOCK,
          CommandSource.CONSOLE
        );

        this.setMinArgs(0);
        this.setMaxArgs(0);

        this.addSubCommands(
          new IslandCreateCommand(),
          new IslandResetCommand()
        );
    }

    @Override
    protected void execute(CommandSender sender, String... args) throws Exception {

    }
}
