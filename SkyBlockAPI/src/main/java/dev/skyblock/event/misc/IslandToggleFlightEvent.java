package dev.skyblock.event.misc;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;

public class IslandToggleFlightEvent extends IslandEvent {

    private boolean allowFlight;

    public IslandToggleFlightEvent(Island island, boolean allowFlight) {
        super(island);

        this.allowFlight = allowFlight;
    }

    public boolean allowFlight() {
        return this.allowFlight;
    }

    public void setAllowFlight(boolean allowFlight) {
        this.allowFlight = allowFlight;
    }
}
