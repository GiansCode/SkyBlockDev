package dev.skyblock.warp;

import dev.skyblock.island.Island;
import dev.skyblock.util.LazyLocation;

import java.util.Set;

public interface WarpAPI {

    Set<WarpData> getWarps(Island island);

    WarpData getWarp(Island island, String name);

    WarpData createWarp(Island island, String name, LazyLocation location);

    void deleteWarp(Island island, String name);

    boolean hasWarp(Island island, String name);
}
