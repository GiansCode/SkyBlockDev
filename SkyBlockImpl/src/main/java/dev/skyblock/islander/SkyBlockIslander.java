package dev.skyblock.islander;

import dev.skyblock.SkyBlock;
import dev.skyblock.island.Island;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SkyBlockIslander implements Islander {

    private UUID uuid;

    public SkyBlockIslander() {}

    public SkyBlockIslander(UUID uuid) {
        this.uuid = uuid;
    }

    public SkyBlockIslander(Player player) {
        this.uuid = player.getUniqueId();
    }

    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public Island getIsland() {
        return SkyBlock.getInstance().getIslandAPI().getByOwner(this.uuid).orElse(null);
    }
}
