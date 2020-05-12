package dev.skyblock.world;

import dev.skyblock.island.Island;
import org.bukkit.WeatherType;
import org.bukkit.World;

public interface WorldAPI {

    World.Environment getEnvironment(Island island);

    void setEnvironment(Island island, World.Environment environment);

    WeatherType getWeather(Island island);

    void setWeather(Island island, WeatherType type);
}
