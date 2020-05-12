package dev.skyblock.storage;

import dev.skyblock.SkyBlock;
import dev.skyblock.config.type.JsonConfig;
import dev.skyblock.islander.SkyBlockIslander;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IslanderStorage extends JsonConfig<IslanderStorage> {

    private static final IslanderStorage DEFAULT_STORAGE = new IslanderStorage(new HashMap<>());

    private Map<UUID, SkyBlockIslander> islanders;

    /**
     * Represents a configuration file.
     */
    public IslanderStorage() {
        super(IslanderStorage.class);
    }

    /**
     * Represents a configuration file.
     */
    public IslanderStorage(Map<UUID, SkyBlockIslander> islanders) {
        super(IslanderStorage.class);

        this.islanders = islanders;
    }

    public Map<UUID, SkyBlockIslander> getIslanders() {
        return this.islanders;
    }

    @Override
    public Path getPath() {
        return SkyBlock.getInstance().getPath().resolve("storage").resolve("islanders.json");
    }

    @Override
    public IslanderStorage getDefaultConfig() {
        return DEFAULT_STORAGE;
    }
}
