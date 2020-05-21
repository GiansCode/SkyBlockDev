package dev.skyblock.provider.template;

import dev.skyblock.SkyBlockAPI;
import dev.skyblock.island.IslandTemplate;
import dev.skyblock.provider.DefaultProvider;

import java.io.File;

public class ExampleTemplate implements IslandTemplate {

    @Override
    public String getName() {
        return "Example Island";
    }

    @Override
    public String[] getDesc() {
        return new String[] {
          "The basic, but maybe not the best."
        };
    }

    @Override
    public File getSchematic() {
        return SkyBlockAPI.get().getPath().resolve("schematics").resolve("island.schematic").toFile();
    }

    @Override
    public int getStartingSize() {
        return 50;
    }

    @Override
    public int getMaxSize() {
        return 500;
    }
}
