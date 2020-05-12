package dev.skyblock.storage;

import dev.skyblock.SkyBlock;
import dev.skyblock.config.type.JsonConfig;

import java.nio.file.Path;

public class GridStorage extends JsonConfig<GridStorage> {

    private static final GridStorage DEFAULT_STORAGE = new GridStorage(0, 0);

    private int currentX;
    private int currentZ;

    /**
     * Represents a configuration file.
     */
    public GridStorage() {
        super(GridStorage.class);
    }

    /**
     * Represents a configuration file.
     */
    public GridStorage(int currentX, int currentZ) {
        super(GridStorage.class);

        this.currentX = currentX;
        this.currentZ = currentZ;
    }

    public int getCurrentX() {
        return this.currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentZ() {
        return this.currentZ;
    }

    public void setCurrentZ(int currentZ) {
        this.currentZ = currentZ;
    }

    @Override
    public Path getPath() {
        return SkyBlock.getInstance().getPath().resolve("storage").resolve("grid.json");
    }

    @Override
    public GridStorage getDefaultConfig() {
        return DEFAULT_STORAGE;
    }
}
