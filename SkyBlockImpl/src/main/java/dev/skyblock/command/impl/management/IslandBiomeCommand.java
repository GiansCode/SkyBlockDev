package dev.skyblock.command.impl.management;

import dev.skyblock.SkyBlockAPI;
import dev.skyblock.command.Command;
import dev.skyblock.command.CommandSource;
import dev.skyblock.island.Island;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class IslandBiomeCommand extends Command {

    /**
     * Represents a command.
     */
    public IslandBiomeCommand() {
        super("biome", "skyblock.island.biome");

        this.setDescription("Sets the biome of your island.");
        this.removePermittedSources(
          CommandSource.COMMAND_BLOCK,
          CommandSource.CONSOLE
        );

        this.setMinArgs(1);
        this.setMaxArgs(1);
    }

    @Override
    protected void execute(CommandSender sender, String... args) throws Exception {
        Player player = (Player) sender;
        Optional<Island> island = SkyBlockAPI.get().getIslandAPI().getByOwner(player);

        if (!island.isPresent()) {
            player.sendMessage(ChatColor.RED + "You do not have an island.");
            return;
        }

        try {
            Biome biome = Biome.valueOf(args[0].toUpperCase());

            SkyBlockAPI.get().getBiomeAPI().setBiome(island.get(), biome);
            player.sendMessage(ChatColor.GREEN + "You have set your island's biome to: " + args[0]);
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "Invalid biome: " + args[0]);
        }
    }
}
