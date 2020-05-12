package dev.skyblock.grid;

import java.util.Objects;

public class GridLocation {

    private final int x;
    private final int z;

    public GridLocation(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridLocation that = (GridLocation) o;
        return x == that.x &&
          z == that.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }

    @Override
    public String toString() {
        return "GridLocation{" +
          "x=" + x +
          ", z=" + z +
          '}';
    }
}
