package dev.skyblock.provider.template;

import com.boydti.fawe.object.schematic.Schematic;
import com.sk89q.worldedit.extent.clipboard.ClipboardFormats;
import dev.skyblock.island.IslandTemplate;
import dev.skyblock.provider.DefaultProvider;

import java.io.File;
import java.io.IOException;

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
    public Schematic getSchematic() {
        Schematic schematic;
        try {
            File file = new File(DefaultProvider.class.getClassLoader().getResource("island.schematic").getFile());
            schematic = ClipboardFormats.findByFile(file).load(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return schematic;
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
