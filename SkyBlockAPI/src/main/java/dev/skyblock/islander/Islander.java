package dev.skyblock.islander;

import dev.skyblock.island.Island;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface Islander {

    UUID getUuid();

    Island getIsland();

    default Player asBukkitPlayer() {
        return Bukkit.getPlayer(this.getUuid());
    }
}
