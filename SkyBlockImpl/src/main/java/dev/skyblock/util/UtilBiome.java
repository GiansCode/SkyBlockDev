package dev.skyblock.util;

import dev.skyblock.island.Island;
import net.minecraft.server.v1_16_R2.PacketPlayOutMapChunk;
import net.minecraft.server.v1_16_R2.PacketPlayOutUnloadChunk;
import net.minecraft.server.v1_16_R2.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_16_R2.CraftChunk;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import javax.annotation.concurrent.Immutable;

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
                    UtilConcurrency.runSync(() -> {
                        world.setBiome(finalX, finalZ, biome);

                        Chunk chunk = world.getChunkAt(finalX >> 4, finalZ >> 4);

                        PacketPlayOutUnloadChunk unload = new PacketPlayOutUnloadChunk(chunk.getX(), chunk.getZ());
                        PacketPlayOutMapChunk packet = new PacketPlayOutMapChunk(((CraftChunk) chunk).getHandle(), 65535);

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
                            connection.sendPacket(unload);
                            connection.sendPacket(packet);
                        }
                    });
                }
            }
        });
    }
}
