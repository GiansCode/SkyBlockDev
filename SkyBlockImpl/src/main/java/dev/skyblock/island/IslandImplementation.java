package dev.skyblock.island;

import com.boydti.fawe.FaweAPI;
import com.boydti.fawe.object.clipboard.MultiClipboardHolder;
import com.sk89q.jnbt.NBTInputStream;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.math.BlockVector3;
import dev.skyblock.SkyBlock;
import dev.skyblock.grid.GridLocation;
import dev.skyblock.islander.Islander;
import dev.skyblock.storage.IslandStorage;
import dev.skyblock.util.LazyLocation;
import dev.skyblock.util.UtilConcurrency;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

        SkyBlockIsland.ISLAND_ID_COUNTER.set(this.storage.getIslands().keySet().stream()
          .sorted(Integer::compareTo)
          .limit(1)
          .findFirst()
          .orElse(0));
    }

    @Override
    public Island createIsland(Islander islander, IslandTemplate template) {
        if (this.getByOwner(islander.getUuid()).isPresent()) {
            throw new IllegalStateException("An island already exists for this player.");
        }

        SkyBlockIsland island = new SkyBlockIsland(islander, template);

        this.storage.getIslands().compute(island.getId(), (id, i) -> island);

        this.generateIsland(island, template);

        return island;
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
        if (template == null) {
            template = SkyBlock.getInstance().getProviderAPI().getProvider().getTemplates().get(0);
        }

        File file = template.getSchematic();
        GridLocation location = new GridLocation(island.getGridX(), island.getGridZ());

        SkyBlock.getInstance().getLogger().info("Attempting to load schematic: " + file.getAbsolutePath());
        try {
            BlockVector3 pasteLocation = BlockVector3.at(location.getX() * SkyBlock.getInstance().getGridConfig().getLength(), 110,
              location.getZ() * SkyBlock.getInstance().getGridConfig().getWidth());

            FaweAPI.load(file).paste(FaweAPI.getWorld(island.getWorldName()), pasteLocation);
            SkyBlock.getInstance().getLogger().info("Pasting at: " + pasteLocation.toString());

            island.setSpawn(new LazyLocation(island.getWorldName(), pasteLocation.getBlockX(), pasteLocation.getBlockY(), pasteLocation.getBlockZ()));
            island.setHome(island.getSpawn());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
