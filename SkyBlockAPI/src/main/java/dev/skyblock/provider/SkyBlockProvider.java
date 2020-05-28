package dev.skyblock.provider;

import dev.skyblock.challenge.Challenge;
import dev.skyblock.grid.GridHandler;
import dev.skyblock.island.IslandTemplate;
import dev.skyblock.island.island.IslandValueCalculator;

import java.util.List;

public abstract class SkyBlockProvider {

    public abstract void onStartup();

    public abstract void onShutdown();

    public abstract List<Challenge> getChallenges();

    public abstract List<IslandTemplate> getTemplates();

    public abstract IslandValueCalculator getValueCalculator();
}
