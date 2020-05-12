package dev.skyblock.event.privacy;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import org.bukkit.event.Cancellable;

public class IslandUnbanEvent extends IslandEvent implements Cancellable {

    private final Islander unbanner;
    private final Islander target;

    private boolean cancelled;

    public IslandUnbanEvent(Island island, Islander unbanner, Islander target) {
        super(island);

        this.unbanner = unbanner;
        this.target = target;

        this.cancelled = false;
    }

    public Islander getUnbanner() {
        return this.unbanner;
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
