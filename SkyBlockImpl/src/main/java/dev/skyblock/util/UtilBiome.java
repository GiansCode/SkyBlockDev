package dev.skyblock.util;

import com.google.common.collect.Sets;
import dev.skyblock.island.Island;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_15_R1.CraftChunk;

import javax.annotation.concurrent.Immutable;
import java.util.Set;

@Immutable
public final class UtilBiome {

    @SuppressWarnings("deprecated")
    public static void setBiome(Island island, Biome biome) {
        World world = island.getWorld();

        UtilConcurrency.runAsync(() -> {
            for (int x = island.getMinPoint().getBlockX(); x < island.getMaxPoint().getBlockX(); x++) {
                for (int z = island.getMinPoint().getBlockZ(); z < island.getMaxPoint().getBlockZ(); z++) {
                    final int finalX = x;
                    final int finalZ = z;
                    UtilConcurrency.runSync(() -> world.setBiome(finalX, finalZ, biome));
                }
            }
        });
    }
}
