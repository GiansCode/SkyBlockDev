package dev.skyblock.grid;

import dev.skyblock.SkyBlock;
import dev.skyblock.provider.SkyBlockProvider;
import dev.skyblock.storage.GridStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class GridImplementation implements GridAPI {

    private final SkyBlockProvider provider;
    private final GridStorage storage;
    private final GridHandler handler;

    public GridImplementation(SkyBlockProvider provider, GridStorage storage) {
        this.provider = provider;
        this.storage = storage;
        this.handler = new SkyBlockGridHandler(storage);
    }

    @Override
    public GridHandler getGridHandler() {
        return this.handler;
    }

    @Override
    public GridLocation getNextLocation() {
        return this.getGridHandler().getNextLocation();
    }

    @Override
    public Location getCenter(GridLocation location) {
        return new Location(
          Bukkit.getWorld(this.getGridHandler().getGridWorld()),
          location.getX() + SkyBlock.getInstance().getGridConfig().getLength() >> 2,
          64,
          location.getZ() + SkyBlock.getInstance().getGridConfig().getWidth() >> 2
        );
    }
}
