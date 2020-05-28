package dev.skyblock.island;

import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.List;

public interface IslandTemplate {

    String getName();

    String[] getDesc();

    File getSchematic();

    int getStartingSize();

    int getMaxSize();

    List<ItemStack> getStartingItems();
}
