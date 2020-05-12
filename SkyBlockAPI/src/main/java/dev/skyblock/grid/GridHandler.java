package dev.skyblock.grid;

public interface GridHandler {

    String getGridWorld();

    int getGridWidth();

    int getGridLength();

    GridLocation getNextLocation();
}
