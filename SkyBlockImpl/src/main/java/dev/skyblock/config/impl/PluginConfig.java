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
    @SuppressWarnings("all")
    public PluginConfig() throws NullPointerException {
        super(PluginConfig.class);

        this.displayName = this.config.getString("general.display_name");
        this.allowNether = this.config.getBoolean("general.allow_nether");
        this.maxResets = this.config.getInt("general.max_resets");
        this.enableCommands = this.config.getBoolean("general.enable_default_commands");
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

    @Override
    public void writeValues(YamlConfiguration config) {
        config.set("general.display_name", this.displayName);
        config.set("general.allow_nether", this.allowNether);
        config.set("general.max_resets", this.maxResets);
        config.set("general.enable_default_commands", this.enableCommands);
    }
}
