package dev.skyblock.command.impl.management;

import dev.skyblock.SkyBlockAPI;
import dev.skyblock.command.Command;
import dev.skyblock.command.CommandSource;
import dev.skyblock.island.Island;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class IslandUnlockCommand extends Command {

    /**
     * Represents a command.
     */
    public IslandUnlockCommand() {
        super("unlock", "skyblock.island.unlock");

        this.setDescription("Unlocks your island.");
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

        Island is = island.get();

        is.setLocked(false);
        SkyBlockAPI.get().getIslandAPI().updateIsland(is);
        player.sendMessage(ChatColor.GREEN + "You have unlocked your island.");
    }
}
