package dev.skyblock.command.impl.management;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.skyblock.SkyBlockAPI;
import dev.skyblock.command.Command;
import dev.skyblock.command.CommandSource;
import dev.skyblock.command.CompletableCommand;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class IslandResetCommand extends Command implements CompletableCommand {

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
        Optional<Island> island = SkyBlockAPI.get().getIslandAPI().getByOwner(islander);

        if (!island.isPresent()) {
            sender.sendMessage(ChatColor.RED + "You do not have an island.");
            return;
        }

        SkyBlockAPI.get().getIslandAPI().resetIsland(island.get());
        sender.sendMessage(ChatColor.GREEN + "Your island has been reset.");
    }

    @Override
    public LiteralCommandNode<?> getCompletions() {
        return LiteralArgumentBuilder.literal("island")
          .then(LiteralArgumentBuilder.literal("reset"))
          .then(LiteralArgumentBuilder.literal("r"))
          .build();
    }
}
