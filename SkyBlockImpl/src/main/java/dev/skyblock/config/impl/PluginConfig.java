package dev.skyblock.config.impl;

import dev.skyblock.SkyBlock;
import dev.skyblock.config.type.YamlConfig;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.nio.file.Path;

public class PluginConfig extends YamlConfig<PluginConfig> {

    private static final PluginConfig DEFAULT_CONFIG = new PluginConfig(ChatColor.RED + "SkyBlock", false, 2, true);

    // General settings
    private String displayName;
    private boolean allowNether;
    private int maxResets;
    private boolean enableCommands;

    /**
     * Represents a configuration file.
     */
    public PluginConfig() {
        super(PluginConfig.class);
    }

    public PluginConfig(String displayName, boolean allowNether, int maxResets, boolean enableCommands) {
        super(PluginConfig.class);

        this.displayName = displayName;
        this.allowNether = allowNether;
        this.maxResets = maxResets;
        this.enableCommands = enableCommands;
    }

    public String getDisplayName() {
        return ChatColor.translateAlternateColorCodes('&', this.displayName);
    }

    public boolean allowNether() {
        return this.allowNether;
    }

    public int getMaxResets() {
        return this.maxResets;
    }

    public boolean enableDefaultCommands() {
        return this.enableCommands;
    }

    @Override
    public Path getPath() {
        return SkyBlock.getInstance().getPath().resolve("config.yml");
    }

    @Override
    public PluginConfig getDefaultConfig() {
        return DEFAULT_CONFIG;
    }
}
