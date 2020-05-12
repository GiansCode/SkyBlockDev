package dev.skyblock.event.generic;

import dev.skyblock.islander.Islander;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IslanderEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Islander islander;

    public IslanderEvent(Islander islander) {
        this.islander = islander;
    }

    public Islander getIslander() {
        return this.islander;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
