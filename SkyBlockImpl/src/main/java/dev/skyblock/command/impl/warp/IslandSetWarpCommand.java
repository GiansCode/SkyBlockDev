package dev.skyblock.command.impl.warp;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.skyblock.SkyBlockAPI;
import dev.skyblock.command.Command;
import dev.skyblock.command.CommandSource;
import dev.skyblock.command.CompletableCommand;
import dev.skyblock.island.Island;
import dev.skyblock.util.LazyLocation;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class IslandSetWarpCommand extends Command implements CompletableCommand {

    /**
     * Represents a command.
     */
    public IslandSetWarpCommand() {
        super("setwarp", "skyblock.island.setwarp");

        this.setDescription("Sets an island warp at your location.");
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

        SkyBlockAPI.get().getWarpAPI().createWarp(is, warpName, new LazyLocation(player.getLocation()));
        SkyBlockAPI.get().getIslandAPI().updateIsland(is);
        player.sendMessage(ChatColor.GREEN + "You have created the warp: " + warpName + "!");
    }

    @Override
    public LiteralCommandNode<?> getCompletions() {
        return LiteralArgumentBuilder.literal("island")
          .then(LiteralArgumentBuilder.literal("setwarp"))
          .then(LiteralArgumentBuilder.literal("sw"))
          .build();
    }
}
