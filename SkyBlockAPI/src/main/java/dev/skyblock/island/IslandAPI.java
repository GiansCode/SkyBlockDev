package dev.skyblock.island;

import dev.skyblock.grid.GridLocation;
import dev.skyblock.islander.Islander;
import dev.skyblock.util.LazyLocation;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface IslandAPI {

    Optional<Island> getByOwner(UUID uuid);

    Optional<Island> getById(int id);

    default Optional<Island> getByOwner(Player player) {
        return this.getByOwner(player.getUniqueId());
    }

    default Optional<Island> getByOwner(OfflinePlayer player) {
        return this.getByOwner(player.getUniqueId());
    }

    default Optional<Island> getByOwner(Islander islander) {
        return this.getByOwner(islander.getUuid());
    }

    Optional<Island> getByLocation(LazyLocation location);

    Optional<Island> getByLocation(Location location);

    Optional<Island> getByGridLocation(GridLocation location);

    double getIslandValue(Island island);

    void generateIsland(Island island, IslandTemplate template);

    void resetIsland(Island island);
}
