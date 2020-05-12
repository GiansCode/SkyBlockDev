package dev.skyblock.event.privacy;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import org.bukkit.event.Cancellable;

public class IslandLockEvent extends IslandEvent implements Cancellable {

    private final Islander lockedBy;

    private boolean cancelled;

    public IslandLockEvent(Island island, Islander lockedBy) {
        super(island);

        this.lockedBy = lockedBy;

        this.cancelled = false;
    }

    public Islander getLockedBy() {
        return this.lockedBy;
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
