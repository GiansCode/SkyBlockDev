package dev.skyblock.ban;

import dev.skyblock.island.Island;

import java.util.Set;
import java.util.UUID;

public interface BanAPI {

    default boolean isBanned(Island island, UUID uuid) {
        return this.getBans(island).contains(uuid);
    }

    void addBan(Island island, UUID uuid);

    void revokeBan(Island island, UUID uuid);

    Set<UUID> getBans(Island island);
}
