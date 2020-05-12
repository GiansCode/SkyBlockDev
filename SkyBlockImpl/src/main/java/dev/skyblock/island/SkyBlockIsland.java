package dev.skyblock.island;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import dev.skyblock.util.LazyLocation;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class SkyBlockIsland implements Island {

    private static final AtomicInteger ISLAND_ID_COUNTER = new AtomicInteger(-1);

    private int id;

    private UUID owner;
    private Map<UUID, IslandRole> members;
    private Set<UUID> bannedPlayers;

    private int level;
    private double exp;

    private String worldName;
    private Vector maxPoint;
    private Vector minPoint;

    private LazyLocation homeLocation;
    private LazyLocation spawnLocation;

    private boolean locked;
    private String templateClass;

    private int gridX;
    private int gridZ;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public UUID getOwnerUuid() {
        return this.owner;
    }

    @Override
    public Map<UUID, IslandRole> getMembers() {
        return this.members;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public double getExp() {
        return this.exp;
    }

    @Override
    public void setExp(double exp) {
        this.exp = exp;
    }

    @Override
    public Vector getMaxPoint() {
        return this.maxPoint;
    }

    @Override
    public Vector getMinPoint() {
        return this.minPoint;
    }

    @Override
    public Region getRegion() {
        return new CuboidRegion(this.minPoint, this.maxPoint);
    }

    @Override
    public Set<UUID> getBannedPlayers() {
        return this.bannedPlayers;
    }

    @Override
    public LazyLocation getHome() {
        return this.homeLocation;
    }

    @Override
    public void setHome(LazyLocation location) {
        this.homeLocation = location;
    }

    @Override
    public LazyLocation getSpawn() {
        return this.spawnLocation;
    }

    @Override
    public void setSpawn(LazyLocation location) {
        this.spawnLocation = location;
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public IslandTemplate getTemplate() {
        return null;
    }

    @Override
    public World getWorld() {
        return Bukkit.getWorld(this.worldName);
    }

    @Override
    public int getGridX() {
        return this.gridX;
    }

    @Override
    public int getGridZ() {
        return this.gridZ;
    }
}
