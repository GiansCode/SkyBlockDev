package dev.skyblock.warp;

import dev.skyblock.SkyBlockAPI;
import dev.skyblock.island.Island;
import dev.skyblock.util.LazyLocation;

import java.util.Objects;

public class WarpData {

    private final int islandId;

    private final String name;
    private final LazyLocation location;

    public WarpData(Island island, String name, LazyLocation location) {
        this.islandId = island.getId();

        this.name = name;
        this.location = location;
    }

    public Island getIsland() {
        return SkyBlockAPI.get().getIslandAPI().getById(this.islandId).orElse(null);

    }

    public String getName() {
        return this.name;
    }

    public LazyLocation getLocation() {
        return this.location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarpData warpData = (WarpData) o;
        return islandId == warpData.islandId &&
          Objects.equals(name, warpData.name) &&
          Objects.equals(location, warpData.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(islandId, name, location);
    }

    @Override
    public String toString() {
        return "WarpData{" +
          "islandId=" + islandId +
          ", name='" + name + '\'' +
          ", location=" + location +
          '}';
    }
}
