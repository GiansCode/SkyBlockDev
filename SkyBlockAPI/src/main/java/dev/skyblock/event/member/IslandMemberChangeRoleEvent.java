package dev.skyblock.event.member;

import dev.skyblock.event.generic.IslanderEvent;
import dev.skyblock.island.IslandRole;
import dev.skyblock.islander.Islander;

public class IslandMemberChangeRoleEvent extends IslanderEvent {

    private final IslandRole from;
    private final IslandRole to;

    public IslandMemberChangeRoleEvent(Islander who, IslandRole from, IslandRole to) {
        super(who);

        this.from = from;
        this.to = to;
    }

    public IslandRole getFrom() {
        return this.from;
    }

    public IslandRole getTo() {
        return this.to;
    }
}
