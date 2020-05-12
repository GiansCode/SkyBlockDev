package dev.skyblock.event.misc;

import dev.skyblock.event.generic.IslandEvent;
import dev.skyblock.island.Island;
import org.bukkit.block.Biome;
import org.bukkit.event.Cancellable;

public class IslandBiomeChangeEvent extends IslandEvent implements Cancellable {

    private Biome biome;

    private boolean cancelled;

    public IslandBiomeChangeEvent(Island island, Biome biome) {
        super(island);

        this.biome = biome;

        this.cancelled = false;
    }

    public Biome getBiome() {
        return this.biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
