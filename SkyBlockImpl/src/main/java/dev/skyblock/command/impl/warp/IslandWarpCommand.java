package dev.skyblock.command.impl.warp;

import dev.skyblock.SkyBlockAPI;
import dev.skyblock.command.Command;
import dev.skyblock.command.CommandSource;
import dev.skyblock.island.Island;
import dev.skyblock.warp.WarpData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class IslandWarpCommand extends Command {

    /**
     * Represents a command.
     */
    public IslandWarpCommand() {
        super("warp", "skyblock.island.warp");

        this.setDescription("Warps to an island warp.");
        this.addAliases("sw");
        this.removePermittedSources(
          CommandSource.COMMAND_BLOCK,
          CommandSource.CONSOLE
        );

        this.setMinArgs(1);
        this.setMaxArgs(1);
    }

    @Override
    protected void execute(CommandSender sender, String... args) throws Exception {
        String warpName = args[0];
        Player player = (Player) sender;
        Optional<Island> island = SkyBlockAPI.get().getIslandAPI().getByOwner(player);

        if (!island.isPresent()) {
            player.sendMessage(ChatColor.RED + "You do not have an island.");
            return;
        }

        Island is = island.get();
        WarpData data = SkyBlockAPI.get().getWarpAPI().getWarp(is, warpName);
        if (data == null) {
            player.sendMessage(ChatColor.RED + "No warp called: " + warpName + ".");
            return;
        }

        player.teleport(data.getLocation().toLocation());
        player.sendMessage(ChatColor.GREEN + "You have warped to: " + warpName + "!");
    }
}
