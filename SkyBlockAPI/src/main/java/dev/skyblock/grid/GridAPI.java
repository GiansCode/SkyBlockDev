package dev.skyblock.grid;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import org.bukkit.Location;

public interface GridAPI {

    GridHandler getGridHandler();

    GridLocation getNextLocation();

    Location getCenter(GridLocation location);

    default int[][] getRegionBounds(GridLocation location) {
        return new int[][] {
          new int[] {
            (int) (this.getCenter(location).getX() + this.getGridHandler().getGridLength()),
            (int) (this.getCenter(location).getZ() + this.getGridHandler().getGridWidth())
          },

          new int[] {
            (int) (this.getCenter(location).getX() - this.getGridHandler().getGridLength()),
            (int) (this.getCenter(location).getZ() - this.getGridHandler().getGridWidth())
          }
        };
    }

    default Region getRegion(GridLocation location) {
        int[][] bounds = this.getRegionBounds(location);

        return new CuboidRegion(new Vector(bounds[0][0], 0, bounds[0][1]), new Vector(bounds[1][0], 256, bounds[1][1]));
    }
}
