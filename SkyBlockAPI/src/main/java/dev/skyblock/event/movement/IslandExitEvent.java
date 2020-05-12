package dev.skyblock.event.movement;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import org.bukkit.event.Cancellable;

public class IslandExitEvent extends IslandEvent implements Cancellable {

    private final Islander islander;

    private boolean cancelled;

    public IslandExitEvent(Island island, Islander islander) {
        super(island);

        this.islander = islander;

        this.cancelled = false;
    }

    public Islander getIslander() {
        return this.islander;
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
