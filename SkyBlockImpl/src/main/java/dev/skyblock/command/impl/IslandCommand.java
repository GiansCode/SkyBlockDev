package dev.skyblock.command.impl;

import dev.skyblock.command.Command;
import dev.skyblock.command.CommandSource;
import dev.skyblock.command.impl.home.IslandHomeCommand;
import dev.skyblock.command.impl.management.*;
import dev.skyblock.command.impl.misc.IslandTopCommand;
import dev.skyblock.command.impl.misc.IslandVisitCommand;
import dev.skyblock.command.impl.warp.IslandSetWarpCommand;
import dev.skyblock.command.impl.warp.IslandWarpCommand;
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
          new IslandHomeCommand(),

          new IslandBiomeCommand(),
          new IslandCreateCommand(),
          new IslandDeleteCommand(),
          new IslandLockCommand(),
          new IslandResetCommand(),
          new IslandSetSpawnCommand(),
          new IslandSpawnCommand(),
          new IslandUnlockCommand(),

          new IslandVisitCommand(),
          new IslandTopCommand(),

          new IslandSetWarpCommand(),
          new IslandWarpCommand()
        );
    }

    @Override
    protected void execute(CommandSender sender, String... args) throws Exception {
        this.getSubCommands().forEach(command -> {
            sender.sendMessage(ChatColor.BLUE + "/is " + command.getName() + ChatColor.DARK_GRAY + " » " + ChatColour.GRAY + command.getDescription());
        });
    }
}
