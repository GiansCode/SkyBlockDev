package dev.skyblock.event.misc;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;

public class IslandLevelChangeEvent extends IslandEvent {

    private final int from;
    private final int to;

    public IslandLevelChangeEvent(Island island, int from, int to) {
        super(island);

        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return this.from;
    }

    public int getTo() {
        return this.to;
    }
}
