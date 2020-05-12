package dev.skyblock.event.privacy;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import org.bukkit.event.Cancellable;

public class IslandBanEvent extends IslandEvent implements Cancellable {

    private final Island island;
    private final Islander banner;
    private final Islander target;

    private boolean cancelled;

    public IslandBanEvent(Island island, Islander banner, Islander target) {
        super(island);

        this.island = island;
        this.banner = banner;
        this.target = target;

        this.cancelled = false;
    }

    public Island getIsland() {
        return this.island;
    }

    public Islander getBanner() {
        return this.banner;
    }

    public Islander getTarget() {
        return this.target;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
