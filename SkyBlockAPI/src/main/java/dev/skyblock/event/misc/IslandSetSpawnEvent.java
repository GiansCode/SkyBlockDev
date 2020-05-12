package dev.skyblock.event.misc;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import dev.skyblock.util.LazyLocation;
import org.bukkit.event.Cancellable;

public class IslandSetSpawnEvent extends IslandEvent implements Cancellable {

    private final LazyLocation location;

    private boolean cancelled;

    public IslandSetSpawnEvent(Island island, LazyLocation location) {
        super(island);

        this.location = location;

        this.cancelled = false;
    }

    public LazyLocation getLocation() {
        return this.location;
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
