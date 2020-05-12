package dev.skyblock.storage;

import dev.skyblock.SkyBlock;
import dev.skyblock.config.type.JsonConfig;
import dev.skyblock.island.SkyBlockIsland;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class IslandStorage extends JsonConfig<IslandStorage> {

    private static final IslandStorage DEFAULT_STORAGE = new IslandStorage(new HashMap<>());

    private Map<Integer, SkyBlockIsland> islands;

    /**
     * Represents a configuration file.
     */
    public IslandStorage() {
        super(IslandStorage.class);
    }

    /**
     * Represents a configuration file.
     */
    public IslandStorage(Map<Integer, SkyBlockIsland> islands) {
        super(IslandStorage.class);

        this.islands = islands;
    }

    public Map<Integer, SkyBlockIsland> getIslands() {
        return this.islands;
    }

    @Override
    public Path getPath() {
        return SkyBlock.getInstance().getPath().resolve("storage").resolve("islands.json");
    }

    @Override
    public IslandStorage getDefaultConfig() {
        return DEFAULT_STORAGE;
    }
}
