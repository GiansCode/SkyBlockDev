package dev.skyblock.event.create;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;

public class IslandCreatedEvent extends IslandEvent {

    private final Islander creator;

    public IslandCreatedEvent(Island island, Islander creator) {
        super(island);

        this.creator = creator;
    }

    public Islander getCreator() {
        return this.creator;
    }
}
