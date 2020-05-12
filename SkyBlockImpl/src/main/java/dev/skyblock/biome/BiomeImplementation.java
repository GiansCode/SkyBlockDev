package dev.skyblock.biome;

import dev.skyblock.island.Island;
import dev.skyblock.util.UtilBiome;
import org.bukkit.block.Biome;

public class BiomeImplementation implements BiomeAPI {

    @Override
    public void setBiome(Island island, Biome biome) {
        UtilBiome.setBiome(island, biome);
    }

    @Override
    public Biome getBiome(Island island) {
        return island.getSpawn().toLocation().getBlock().getBiome();
    }
}
