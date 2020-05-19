package dev.skyblock.island;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.object.schematic.Schematic;
import com.sk89q.worldedit.Vector;
import dev.skyblock.SkyBlock;
import dev.skyblock.grid.GridLocation;
import dev.skyblock.storage.IslandStorage;
import dev.skyblock.util.LazyLocation;
import dev.skyblock.util.UtilConcurrency;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class IslandImplementation implements IslandAPI {

    private static final AtomicReference<IslandAPI> INSTANCE = new AtomicReference<>(null);

    private final IslandStorage storage;

    public IslandImplementation(IslandStorage storage) {
        if (INSTANCE.get() != null) {
            throw new IllegalStateException("Two island manager implementations cannot be loaded at the same time.");
        }

        this.storage = storage;
        INSTANCE.set(this);
    }

    @Override
    public Optional<Island> getByOwner(UUID uuid) {
        return Optional.ofNullable(this.storage.getIslands().values().stream()
          .filter(skyBlockIsland -> skyBlockIsland.getOwnerUuid().equals(uuid))
          .findFirst()
          .orElse(null)
        );
    }

    @Override
    public Optional<Island> getById(int id) {
        return Optional.ofNullable(this.storage.getIslands().getOrDefault(id, null));
    }

    @Override
    public Optional<Island> getByLocation(LazyLocation location) {
        return Optional.ofNullable(this.storage.getIslands().values().stream()
          .filter(island -> island.getSpawn().equals(location)).findFirst().orElse(null));
    }

    @Override
    public Optional<Island> getByLocation(Location location) {
        return this.getByLocation(new LazyLocation(location));
    }

    @Override
    public Optional<Island> getByGridLocation(GridLocation location) {
        return Optional.ofNullable(this.storage.getIslands().values().stream()
          .filter(island -> island.getGridX() == location.getX() && island.getGridZ() == location.getZ()).findFirst().orElse(null));
    }

    @Override
    public double getIslandValue(Island island) {
        return island.getLevel();
    }

    @Override
    public void generateIsland(Island island, IslandTemplate template) {
        Schematic schematic = template.getSchematic();
        GridLocation location = new GridLocation(island.getGridX(), island.getGridZ());


        schematic.paste(FaweAPI.getWorld(island.getWorld().getName()),
          new Vector(location.getX() * SkyBlock.getInstance().getGridConfig().getLength(), 64, location.getZ() * SkyBlock.getInstance().getGridConfig().getWidth()));
    }

    @Override
    public void resetIsland(Island island) {
        UtilConcurrency.runAsync(() -> {
            int maxX = (int) Math.max(island.getMinPoint().getX(), island.getMaxPoint().getX());
            int maxY = (int) Math.max(island.getMinPoint().getY(), island.getMaxPoint().getY());
            int maxZ = (int) Math.max(island.getMinPoint().getZ(), island.getMaxPoint().getZ());

            int minX = (int) Math.min(island.getMinPoint().getX(), island.getMaxPoint().getX());
            int minY = (int) Math.min(island.getMinPoint().getY(), island.getMaxPoint().getY());
            int minZ = (int) Math.min(island.getMinPoint().getZ(), island.getMaxPoint().getZ());

            for (int x = minX; x < maxX; x++) {
                for (int y = minY; y < maxY; y++) {
                    for (int z = minZ; z < maxZ; z++) {
                        island.getWorld().getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }
            }

            this.generateIsland(island, island.getTemplate());
        });
    }
}
