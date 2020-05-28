package dev.skyblock.provider.value;

import dev.skyblock.SkyBlockAPI;
import dev.skyblock.island.Island;
import dev.skyblock.island.island.IslandValueCalculator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultValueCalculator implements IslandValueCalculator {

    @Override
    public List<Island> getTopIslands(int amount) {
        return SkyBlockAPI.get().getIslandAPI().getAllIslands().stream()
          .sorted(Comparator.comparingDouble(this::getIslandValue))
          .limit(amount)
          .collect(Collectors.toList());
    }

    @Override
    public double getIslandValue(Island island) {
        return 0;
    }
}
