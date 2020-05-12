package dev.skyblock.ban;

import dev.skyblock.island.Island;

import java.util.Set;
import java.util.UUID;

public class BanImplementation implements BanAPI {

    @Override
    public void addBan(Island island, UUID uuid) {
        island.getBannedPlayers().add(uuid);
    }

    @Override
    public void revokeBan(Island island, UUID uuid) {
        island.getBannedPlayers().remove(uuid);
    }

    @Override
    public Set<UUID> getBans(Island island) {
        return island.getBannedPlayers();
    }
}
