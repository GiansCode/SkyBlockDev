package dev.skyblock.command.impl.misc;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.skyblock.SkyBlockAPI;
import dev.skyblock.command.Command;
import dev.skyblock.command.CommandSource;
import dev.skyblock.command.CompletableCommand;
import dev.skyblock.island.Island;
import dev.skyblock.island.island.IslandValueCalculator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class IslandTopCommand extends Command implements CompletableCommand {

    /**
     * Represents a command.
     */
    public IslandTopCommand() {
        super("top", "skyblock.island.top");

        this.setDescription("View the top islands.");
        this.removePermittedSources(
          CommandSource.COMMAND_BLOCK,
          CommandSource.CONSOLE
        );

        this.setMinArgs(0);
        this.setMaxArgs(0);
    }

    @Override
    protected void execute(CommandSender sender, String... args) throws Exception {
        IslandValueCalculator calculator = SkyBlockAPI.get().getProviderAPI().getProvider().getValueCalculator();

        sender.sendMessage(ChatColor.GREEN + "Top Islands:");
        int index = 1;
        for (Island island : calculator.getTopIslands(10)) {
            sender.sendMessage(ChatColor.GREEN + "#" + index++ + " " + Bukkit.getOfflinePlayer(island.getOwnerUuid()).getName() + " - $" + calculator.getIslandValue(island));
        }
    }

    @Override
    public LiteralCommandNode<?> getCompletions() {
        return LiteralArgumentBuilder.literal("island")
          .then(LiteralArgumentBuilder.literal("top"))
          .build();
    }
}
