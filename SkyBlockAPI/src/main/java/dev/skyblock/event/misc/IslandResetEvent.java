package dev.skyblock.event.misc;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import org.bukkit.event.Cancellable;

public class IslandResetEvent extends IslandEvent implements Cancellable {

    private final Islander resetter;

    private boolean cancelled;

    public IslandResetEvent(Island island, Islander resetter) {
        super(island);

        this.resetter = resetter;

        this.cancelled = false;
    }

    public Islander getResetter() {
        return this.resetter;
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
