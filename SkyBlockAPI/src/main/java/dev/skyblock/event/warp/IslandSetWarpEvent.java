package dev.skyblock.event.warp;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import dev.skyblock.util.LazyLocation;
import org.bukkit.event.Cancellable;

public class IslandSetWarpEvent extends IslandEvent implements Cancellable {

    private final Islander who;
    private final LazyLocation location;

    private boolean cancelled;

    public IslandSetWarpEvent(Island island, Islander who, LazyLocation location) {
        super(island);

        this.who = who;
        this.location = location;

        this.cancelled = false;
    }

    public Islander getWho() {
        return this.who;
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
