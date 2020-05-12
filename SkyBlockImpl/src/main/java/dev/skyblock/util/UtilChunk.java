package dev.skyblock.util;

import com.google.common.collect.Lists;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import javax.annotation.concurrent.Immutable;
import java.util.List;

@Immutable
public final class UtilChunk {

    public static List<Chunk> getChunks(Location location, int radius) {
        if (location == null) {
            return Lists.newArrayList();
        }

        World world = location.getWorld();
        if (world == null) {
            return Lists.newArrayList();
        }

        Location minLocation = new Location(world, location.getBlockX() - radius, 0, location.getBlockZ() - radius);
        Location maxLocation = new Location(world, location.getBlockX() + radius, world.getMaxHeight(), location.getBlockZ() + radius);

        int minX = Math.min(maxLocation.getBlockX(), minLocation.getBlockX());
        int minZ = Math.min(maxLocation.getBlockZ(), minLocation.getBlockZ());

        int maxX = Math.max(maxLocation.getBlockX(), minLocation.getBlockX());
        int maxZ = Math.max(maxLocation.getBlockZ(), minLocation.getBlockZ());

        final List<Chunk> positions = Lists.newLinkedList();

        for (int x = minX; x < maxX + 16; x += 16) {
            for (int z = minZ; z < maxZ + 16; z += 16) {
                positions.add(world.getChunkAt(x >> 4, z >> 4));
            }
        }

        return positions;
    }
}
