package dev.skyblock.util;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class LazyLocation {

    private final String world;

    private final double x;
    private final double y;
    private final double z;

    private final int chunkX;
    private final int chunkZ;

    public LazyLocation(World world, int x, int y, int z) {
        this.world = world.getName();

        this.x = x;
        this.y = y;
        this.z = z;

        this.chunkX = x >> 4;
        this.chunkZ = z >> 4;
    }

    public LazyLocation(World world, double x, double y, double z) {
        this.world = world.getName();

        this.x = x;
        this.y = y;
        this.z = z;

        this.chunkX = (int) x >> 4;
        this.chunkZ = (int) z >> 4;
    }

    public LazyLocation(World world, Vector vector) {
        this.world = world.getName();

        this.x = vector.getX();
        this.y = vector.getY();
        this.z = vector.getZ();

        this.chunkX = vector.getBlockX() >> 4;
        this.chunkZ = vector.getBlockZ() >> 4;
    }

    public LazyLocation(Location location) {
        this.world = location.getWorld().getName();

        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();

        this.chunkX = location.getBlockX() >> 4;
        this.chunkZ = location.getBlockZ() >> 4;
    }

    public LazyLocation(String world, int x, int y, int z) {
        this.world = world;

        this.x = x;
        this.y = y;
        this.z = z;

        this.chunkX = x >> 4;
        this.chunkZ = z >> 4;
    }

    public String getWorldName() {
        return this.world;
    }

    public World getWorld() {
        return Bukkit.getWorld(this.world);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public int getChunkX() {
        return this.chunkX;
    }

    public int getChunkZ() {
        return this.chunkZ;
    }

    public Chunk getChunk() {
        return this.getWorld().getChunkAt(this.chunkX, this.chunkZ);
    }

    public Location toLocation() {
        return new Location(this.getWorld(), this.x, this.y, this.z);
    }

    @Override
    public String toString() {
        return "LazyLocation[" + this.world + "," + this.x + "," + y + "," + z + "]";
    }

    public static LazyLocation fromString(String string) {
        if (!string.startsWith("LazyLocation")) {
            throw new IllegalStateException("String is not a LazyLocation string.");
        }

        String[] parts = string.replace("LazyLocation[", "").replace("]", "").split(",");

        return new LazyLocation(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
    }
}
