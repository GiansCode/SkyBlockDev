package dev.skyblock.event.privacy;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import org.bukkit.event.Cancellable;

public class IslandUnlockEvent extends IslandEvent implements Cancellable {

    private final Islander unlockedBy;

    private boolean cancelled;

    public IslandUnlockEvent(Island island, Islander unlockedBy) {
        super(island);

        this.unlockedBy = unlockedBy;

        this.cancelled = false;
    }

    public Islander getUnlockedBy() {
        return this.unlockedBy;
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
