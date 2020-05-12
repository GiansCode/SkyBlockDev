package dev.skyblock.event.member;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import org.bukkit.event.Cancellable;

public class IslandMemberAddEvent extends IslandEvent implements Cancellable {

    private final Islander receiver;

    private boolean cancelled;

    public IslandMemberAddEvent(Island island, Islander receiver) {
        super(island);

        this.receiver = receiver;
    }

    public Islander getReceiver() {
        return this.receiver;
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
