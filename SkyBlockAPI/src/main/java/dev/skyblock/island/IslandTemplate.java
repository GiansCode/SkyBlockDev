package dev.skyblock.island;

import java.io.File;

public interface IslandTemplate {

    String getName();

    String[] getDesc();

    File getSchematic();

    int getStartingSize();

    int getMaxSize();
}
