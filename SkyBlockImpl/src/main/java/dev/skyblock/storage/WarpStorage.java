package dev.skyblock.storage;

import dev.skyblock.SkyBlock;
import dev.skyblock.config.type.JsonConfig;
import dev.skyblock.warp.WarpData;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WarpStorage extends JsonConfig<WarpStorage> {

    private static final WarpStorage DEFAULT_STORAGE = new WarpStorage(new HashMap<>());

    private Map<Integer, Set<WarpData>> islandWarps;

    /**
     * Represents a configuration file.
     */
    public WarpStorage() {
        super(WarpStorage.class);
    }

    public WarpStorage(Map<Integer, Set<WarpData>> islandWarps) {
        super(WarpStorage.class);

        this.islandWarps = islandWarps;
    }

    public Map<Integer, Set<WarpData>> getIslandWarps() {
        return this.islandWarps;
    }

    @Override
    public Path getPath() {
        return SkyBlock.getInstance().getPath().resolve("storage").resolve("warps.json");
    }

    @Override
    public WarpStorage getDefaultConfig() {
        return DEFAULT_STORAGE;
    }
}
