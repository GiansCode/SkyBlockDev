package dev.skyblock.event.delete;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import org.bukkit.event.Cancellable;

public class IslandPreDeleteEvent extends IslandEvent implements Cancellable {

    private final Islander deleter;

    private boolean cancelled;

    public IslandPreDeleteEvent(Island island, Islander deleter) {
        super(island);

        this.deleter = deleter;
        this.cancelled = false;
    }

    public Islander getDeleter() {
        return this.deleter;
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
