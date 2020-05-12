package dev.skyblock.world;

import dev.skyblock.island.Island;
import dev.skyblock.util.UtilNMS;
import org.bukkit.WeatherType;
import org.bukkit.World;

import java.lang.reflect.Field;

public class WorldImplementation implements WorldAPI {

    @Override
    public World.Environment getEnvironment(Island island) {
        return island.getWorld().getEnvironment();
    }

    @Override
    public void setEnvironment(Island island, World.Environment environment) {
        try {
            Object nmsWorld = UtilNMS.getNMSWorld(island.getWorld());
            Field field = nmsWorld.getClass().getDeclaredField("environment");
            field.setAccessible(true);

            field.set(island.getWorld(), environment);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WeatherType getWeather(Island island) {
        if (island.getWorld().hasStorm() || island.getWorld().isThundering()) {
            return WeatherType.DOWNFALL;
        }

        return WeatherType.CLEAR;
    }

    @Override
    public void setWeather(Island island, WeatherType type) {
        island.getWorld().setStorm(type == WeatherType.DOWNFALL);
    }
}
