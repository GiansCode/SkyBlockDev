package dev.skyblock.event.finance;

import dev.skyblock.island.Island;
import dev.skyblock.islander.Islander;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class IslandDepositEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Island island;
    private final Islander depositor;
    private final double amount;

    private boolean cancelled;

    public IslandDepositEvent(Island island, Islander depositor, double amount) {
        this.island = island;
        this.depositor = depositor;
        this.amount = amount;

        this.cancelled = false;
    }

    public Island getIsland() {
        return this.island;
    }

    public Islander getDepositor() {
        return this.depositor;
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
