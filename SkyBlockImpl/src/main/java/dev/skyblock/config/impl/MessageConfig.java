package dev.skyblock.config.impl;

import dev.skyblock.SkyBlock;
import dev.skyblock.config.type.YamlConfig;
import org.bukkit.configuration.file.YamlConfiguration;

import java.nio.file.Path;

public class MessageConfig extends YamlConfig<MessageConfig> {

    private static final MessageConfig DEFAULT_CONFIG = new MessageConfig();

    private String islandCreate;
    private String islandDelete;
    private String islandReset;

    /**
     * Represents a configuration file.
     */
    public MessageConfig() throws NullPointerException {
        super(MessageConfig.class);

        this.islandCreate = this.config.getString("general.island_create");
        this.islandDelete = this.config.getString("general.island_delete");
        this.islandReset = this.config.getString("general.island_reset");
    }

    /**
     * Represents a configuration file.
     */
    public MessageConfig(String islandCreate, String islandDelete, String islandReset) throws NullPointerException {
        super(MessageConfig.class);

        this.islandCreate = islandCreate;
        this.islandDelete = islandDelete;
        this.islandReset = islandReset;
    }

    public String getIslandCreate() {
        return this.islandCreate;
    }

    public String getIslandDelete() {
        return this.islandDelete;
    }

    public String getIslandReset() {
        return this.islandReset;
    }

    @Override
    public Path getPath() {
        return SkyBlock.getInstance().getPath().resolve("messages.yml");
    }

    @Override
    public MessageConfig getDefaultConfig() {
        return DEFAULT_CONFIG;
    }

    @Override
    public void writeValues(YamlConfiguration config) {
        config.set("general.island_create", this.islandCreate);
        config.set("general.island_delete", this.islandDelete);
        config.set("general.island_reset", this.islandReset);
    }
}
