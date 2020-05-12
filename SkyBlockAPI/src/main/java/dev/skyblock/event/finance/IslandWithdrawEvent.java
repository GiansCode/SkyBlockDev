package dev.skyblock.event.finance;

import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IslandWithdrawEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Island island;
    private final Islander withdrawer;
    private final double amount;

    private boolean cancelled;

    public IslandWithdrawEvent(Island island, Islander withdrawer, double amount) {
        this.island = island;
        this.withdrawer = withdrawer;
        this.amount = amount;

        this.cancelled = false;
    }

    public Island getIsland() {
        return this.island;
    }

    public Islander getWithdrawer() {
        return this.withdrawer;
    }

    public double getAmount() {
        return this.amount;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
