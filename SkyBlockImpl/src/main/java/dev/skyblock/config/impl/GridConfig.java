package dev.skyblock.config.impl;

import dev.skyblock.SkyBlock;
import dev.skyblock.config.type.YamlConfig;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.nio.file.Path;

public class GridConfig extends YamlConfig<GridConfig> {

    private static final GridConfig DEFAULT_CONFIG = new GridConfig("world", 256, 256);

    private String world;
    private int width;
    private int length;

    /**
     * Represents a configuration file.
     */
    public GridConfig() {
        super(GridConfig.class);
    }

    private GridConfig(String world, int width, int length) {
        super(GridConfig.class);

        this.world = world;
        this.width = width;
        this.length = length;
    }

    public String getWorldName() {
        return this.world;
    }

    public World getWorld() {
        return Bukkit.getWorld(this.world);
    }

    public int getWidth() {
        return this.width;
    }

    public int getLength() {
        return this.length;
    }

    @Override
    public Path getPath() {
        return SkyBlock.getInstance().getPath().resolve("grid.yml");
    }

    @Override
    public GridConfig getDefaultConfig() {
        return DEFAULT_CONFIG;
    }
}
