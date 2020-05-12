package dev.skyblock.event.create;

import dev.skyblock.event.generic.IslanderEvent;
import dev.skyblock.island.IslandTemplate;
import dev.skyblock.islander.Islander;
import org.bukkit.event.Cancellable;

public class IslandPreCreateEvent extends IslanderEvent implements Cancellable {

    private final IslandTemplate type;

    private boolean cancelled;

    public IslandPreCreateEvent(Islander islander, IslandTemplate type) {
        super(islander);

        this.type = type;
        this.cancelled = false;
    }

    public IslandTemplate getType() {
        return this.type;
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
