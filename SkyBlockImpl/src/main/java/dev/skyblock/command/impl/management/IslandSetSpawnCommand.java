package dev.skyblock.command.impl.management;

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

public class IslandSetSpawnCommand extends Command implements CompletableCommand {

    /**
     * Represents a command.
     */
    public IslandSetSpawnCommand() {
        super("setspawn", "skyblock.island.setspawn");

        this.setDescription("Set's your Island's spawn.");
        this.addAliases("ss");
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

        is.setSpawn(new LazyLocation(player.getLocation()));
        SkyBlockAPI.get().getIslandAPI().updateIsland(is);
        player.sendMessage(ChatColor.GREEN + "You have set your island spawn to your location.");
    }

    @Override
    public LiteralCommandNode<?> getCompletions() {
        return LiteralArgumentBuilder.literal("island")
          .then(LiteralArgumentBuilder.literal("setspawn"))
          .then(LiteralArgumentBuilder.literal("ss"))
          .build();
    }
}
