package dev.skyblock.event.misc;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import org.bukkit.event.Cancellable;

public class IslandKickEvent extends IslandEvent implements Cancellable {

    private final Islander kicker;
    private final Islander kickee;

    private boolean cancelled;

    public IslandKickEvent(Island island, Islander kicker, Islander kickee) {
        super(island);

        this.kicker = kicker;
        this.kickee = kickee;

        this.cancelled = false;
    }

    public Islander getKicker() {
        return this.kicker;
    }

    public Islander getKickee() {
        return this.kickee;
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
