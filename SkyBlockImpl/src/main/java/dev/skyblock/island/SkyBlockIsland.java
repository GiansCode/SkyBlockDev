package dev.skyblock.island;

import com.google.common.collect.Sets;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import dev.skyblock.SkyBlock;
import dev.skyblock.grid.GridLocation;
import dev.skyblock.islander.Islander;
import dev.skyblock.util.LazyLocation;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SkyBlockIsland implements Island {

    public static final AtomicInteger ISLAND_ID_COUNTER = new AtomicInteger(0);

    private int id;

    private UUID owner;
    private Map<UUID, IslandRole> members;
    private Set<UUID> bannedPlayers;

    private int level;
    private double exp;

    private String worldName;
    private BlockVector3 maxPoint;
    private BlockVector3 minPoint;

    private LazyLocation homeLocation;
    private LazyLocation spawnLocation;

    private boolean locked;
    private String templateClass;

    private int gridX;
    private int gridZ;

    @Deprecated
    public SkyBlockIsland() {}

    public SkyBlockIsland(Islander islander, IslandTemplate template) {
        this.id = ISLAND_ID_COUNTER.getAndIncrement();

        this.owner = islander.getUuid();
        this.members = new HashMap<UUID, IslandRole>() {{
            put(islander.getUuid(), IslandRole.OWNER);
        }};
        this.bannedPlayers = Sets.newHashSet();

        this.level = 1;
        this.exp = 0.0D;

        this.worldName = SkyBlock.getInstance().getGridConfig().getWorldName();

        GridLocation location = SkyBlock.getInstance().getGridAPI().getNextLocation();
        this.maxPoint = BlockVector3.at(location.getX() * SkyBlock.getInstance().getGridConfig().getLength(), 256, location.getZ() * SkyBlock.getInstance().getGridConfig().getWidth());
        this.minPoint = BlockVector3.at(location.getX() / SkyBlock.getInstance().getGridConfig().getLength(), 0, location.getZ() / SkyBlock.getInstance().getGridConfig().getWidth());

        Region region = this.getRegion();
        this.homeLocation = new LazyLocation(this.getWorld(), region.getCenter().getX(), 64, region.getCenter().getZ());
        this.spawnLocation = this.homeLocation;

        this.locked = false;
        this.templateClass = template.getClass().getName();

        this.gridX = location.getX();
        this.gridZ = location.getZ();
    }

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
    public BlockVector3 getMaxPoint() {
        return this.maxPoint;
    }

    @Override
    public BlockVector3 getMinPoint() {
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
    public String getWorldName() {
        return this.worldName;
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
