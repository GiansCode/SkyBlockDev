package dev.skyblock.grid;

import dev.skyblock.SkyBlock;
import dev.skyblock.config.impl.GridConfig;
import dev.skyblock.storage.GridStorage;

import java.util.concurrent.ThreadLocalRandom;

public class SkyBlockGridHandler implements GridHandler {

    private final GridConfig config;
    private final GridStorage storage;

    public SkyBlockGridHandler(GridStorage storage) {
        this.config = SkyBlock.getInstance().getGridConfig();
        this.storage = storage;
    }

    @Override
    public String getGridWorld() {
        return this.config.getWorldName();
    }

    @Override
    public int getGridWidth() {
        return this.config.getWidth();
    }

    @Override
    public int getGridLength() {
        return this.config.getLength();
    }

    @Override
    public GridLocation getNextLocation() {
        boolean incrementWidth = ThreadLocalRandom.current().nextBoolean();

        int x = this.storage.getCurrentX();
        int z = this.storage.getCurrentZ();

        if (incrementWidth) {
            x++;
        } else {
            z++;
        }

        this.storage.setCurrentX(x);
        this.storage.setCurrentZ(z);

        return new GridLocation(x, z);
    }
}
