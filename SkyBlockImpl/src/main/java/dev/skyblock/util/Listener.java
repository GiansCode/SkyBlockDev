package dev.skyblock.util;

import dev.skyblock.SkyBlock;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

public interface Listener extends org.bukkit.event.Listener {

    default void startListening() {
        Bukkit.getPluginManager().registerEvents(this, SkyBlock.getInstance());
    }

    default void stopListening() {
        HandlerList.unregisterAll(this);
    }
}
