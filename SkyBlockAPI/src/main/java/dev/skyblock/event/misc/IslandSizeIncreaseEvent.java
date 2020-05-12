package dev.skyblock.event.misc;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;

public class IslandSizeIncreaseEvent extends IslandEvent {

    private final int amount;

    public IslandSizeIncreaseEvent(Island island, int amount) {
        super(island);

        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }
}
