package dev.skyblock.command.impl.misc;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.skyblock.SkyBlockAPI;
import dev.skyblock.command.Command;
import dev.skyblock.command.CommandSource;
import dev.skyblock.command.CompletableCommand;
import dev.skyblock.island.Island;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class IslandVisitCommand extends Command implements CompletableCommand {

    /**
     * Represents a command.
     */
    public IslandVisitCommand() {
        super("visit", "skyblock.island.visit");

        this.setDescription("Visits another player's island.");
        this.addAliases("go");
        this.removePermittedSources(
          CommandSource.COMMAND_BLOCK,
          CommandSource.CONSOLE
        );

        this.setMinArgs(1);
        this.setMaxArgs(1);
    }

    @Override
    protected void execute(CommandSender sender, String... args) throws Exception {
        String otherPlayer = args[0];
        Player player = Bukkit.getPlayer(otherPlayer);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Could not find player " + args[0]);
            return;
        }

        Optional<Island> island = SkyBlockAPI.get().getIslandAPI().getByOwner(player);
        if (!island.isPresent()) {
            sender.sendMessage(ChatColor.RED + args[0] + " does not have an island.");
            return;
        }

        Island is = island.get();
        if (is.isLocked()) {
            sender.sendMessage(ChatColor.RED + args[0] + "'s island is locked.");
            return;
        }

        ((Player) sender).teleport(is.getSpawn().toLocation());
        sender.sendMessage(ChatColor.GREEN + "You have visited " + args[0] + "'s island.");
    }

    @Override
    public LiteralCommandNode<?> getCompletions() {
        return LiteralArgumentBuilder.literal("island")
          .then(LiteralArgumentBuilder.literal("visit"))
          .then(LiteralArgumentBuilder.literal("go"))
          .build();
    }
}
