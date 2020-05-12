package dev.skyblock.event.delete;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;

public class IslandDeletedEvent extends IslandEvent {

    public IslandDeletedEvent(Island island) {
        super(island);
    }
}
