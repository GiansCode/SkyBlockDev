package dev.skyblock.event.generic;

import dev.skyblock.island.Island;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IslandEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Island island;

    public IslandEvent(Island island) {
        this.island = island;
    }

    public Island getIsland() {
        return this.island;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
