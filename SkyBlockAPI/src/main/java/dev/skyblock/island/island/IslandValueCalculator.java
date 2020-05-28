package dev.skyblock.island.island;

import dev.skyblock.island.Island;

import java.util.List;

public interface IslandValueCalculator {

    List<Island> getTopIslands(int amount);

    double getIslandValue(Island island);
}
