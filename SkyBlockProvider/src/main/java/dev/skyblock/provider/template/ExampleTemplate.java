package dev.skyblock.provider.template;

import com.google.common.collect.Lists;
import dev.skyblock.SkyBlockAPI;
import dev.skyblock.island.IslandTemplate;
import dev.skyblock.provider.DefaultProvider;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.List;

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

    @Override
    public List<ItemStack> getStartingItems() {
        return Lists.newArrayList(
          new ItemStack(Material.WOODEN_PICKAXE),
          new ItemStack(Material.WOODEN_AXE),
          new ItemStack(Material.WATER_BUCKET, 2),
          new ItemStack(Material.LAVA_BUCKET)
        );
    }
}
