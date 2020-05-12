package dev.skyblock.island;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.regions.Region;
import dev.skyblock.util.LazyLocation;
import org.bukkit.World;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface Island {

    int getId();

    UUID getOwnerUuid();

    Map<UUID, IslandRole> getMembers();

    int getLevel();

    void setLevel(int level);

    double getExp();

    void setExp(double exp);

    Vector getMaxPoint();

    Vector getMinPoint();

    Region getRegion();

    Set<UUID> getBannedPlayers();

    LazyLocation getHome();

    void setHome(LazyLocation location);

    LazyLocation getSpawn();

    void setSpawn(LazyLocation location);

    boolean isLocked();

    void setLocked(boolean locked);

    IslandTemplate getTemplate();

    World getWorld();

    int getGridX();

    int getGridZ();
}
