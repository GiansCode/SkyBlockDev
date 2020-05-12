package dev.skyblock.island;

import com.boydti.fawe.object.schematic.Schematic;

public interface IslandTemplate {

    String getName();

    String[] getDesc();

    Schematic getSchematic();

    int getStartingSize();

    int getMaxSize();
}
