package dev.skyblock.event.member;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import org.bukkit.event.Cancellable;

public class IslandMemberInviteEvent extends IslandEvent implements Cancellable {

    private final Islander inviter;
    private final Islander invitee;

    private boolean cancelled;

    public IslandMemberInviteEvent(Island island, Islander inviter, Islander invitee) {
        super(island);

        this.inviter = inviter;
        this.invitee = invitee;

        this.cancelled = false;
    }

    public Islander getInviter() {
        return this.inviter;
    }

    public Islander getInvitee() {
        return this.invitee;
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
