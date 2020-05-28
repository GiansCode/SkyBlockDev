package dev.skyblock.command.impl.management;

import dev.skyblock.SkyBlock;
import dev.skyblock.SkyBlockAPI;
import dev.skyblock.command.Command;
import dev.skyblock.command.CommandSource;
import dev.skyblock.island.Island;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class IslandDeleteCommand extends Command {

    /**
     * Represents a command.
     */
    public IslandDeleteCommand() {
        super("delete", "skyblock.island.delete");

        this.setDescription("Deletes your island.");
        this.addAliases("del");
        this.removePermittedSources(
          CommandSource.COMMAND_BLOCK,
          CommandSource.CONSOLE
        );

        this.setMinArgs(0);
        this.setMaxArgs(0);
    }

    @Override
    protected void execute(CommandSender sender, String... args) throws Exception {
        Player player = (Player) sender;
        Optional<Island> island = SkyBlockAPI.get().getIslandAPI().getByOwner(player);

        if (!island.isPresent()) {
            player.sendMessage(ChatColor.RED + "You do not have an island.");
            return;
        }

        SkyBlockAPI.get().getIslandAPI().deleteIsland(island.get());
        player.teleport(player.getWorld().getSpawnLocation());
        player.sendMessage(ChatColor.GREEN + "Your island has been deleted.");
    }
}
