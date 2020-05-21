package dev.skyblock.command.impl.management;

import dev.skyblock.SkyBlockAPI;
import dev.skyblock.command.Command;
import dev.skyblock.command.CommandSource;
import dev.skyblock.islander.Islander;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class IslandResetCommand extends Command {

    /**
     * Represents a command.
     */
    public IslandResetCommand() {
        super("reset", "skyblock.island.reset");

        this.setDescription("Resets your Island.");
        this.addAliases("r");
        this.removePermittedSources(
          CommandSource.COMMAND_BLOCK,
          CommandSource.CONSOLE
        );

        this.setMinArgs(0);
        this.setMaxArgs(0);
    }

    @Override
    protected void execute(CommandSender sender, String... args) throws Exception {
        Islander islander = SkyBlockAPI.get().getIslanderAPI().getIslander((Player) sender);
    }
}
