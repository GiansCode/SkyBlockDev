package dev.skyblock.biome;

import dev.skyblock.island.Island;
import org.bukkit.block.Biome;

public interface BiomeAPI {

    void setBiome(Island island, Biome biome);

    Biome getBiome(Island island);
}
