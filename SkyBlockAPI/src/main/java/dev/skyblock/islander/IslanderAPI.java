package dev.skyblock.islander;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface IslanderAPI {

    default Islander getIslander(Player player) {
        return this.getIslander(player.getUniqueId());
    }

    Islander getIslander(UUID uuid);
}
