package dev.skyblock.warp;

import com.google.common.collect.Sets;
import dev.skyblock.island.Island;
import dev.skyblock.storage.WarpStorage;
import dev.skyblock.util.LazyLocation;

import java.util.Set;

public class WarpImplementation implements WarpAPI {

    private final WarpStorage storage;

    public WarpImplementation(WarpStorage storage) {
        this.storage = storage;
    }

    @Override
    public Set<WarpData> getWarps(Island island) {
        return this.storage.getIslandWarps().getOrDefault(island.getId(), Sets.newHashSet());
    }

    @Override
    public WarpData getWarp(Island island, String name) {
        return this.storage.getIslandWarps().getOrDefault(island.getId(), Sets.newHashSet()).stream()
          .filter(data -> data.getName().equalsIgnoreCase(name))
          .findFirst()
          .orElse(null);
    }

    @Override
    public WarpData createWarp(Island island, String name, LazyLocation location) {
        WarpData data = new WarpData(island, name, location);

        this.storage.getIslandWarps().compute(island.getId(), (id, set) -> {
            if (set == null || set.size() == 0) {
                return Sets.newHashSet(data);
            }

            set.add(data);
            return set;
        });

        return data;
    }

    @Override
    public void deleteWarp(Island island, String name) {
        this.storage.getIslandWarps().compute(island.getId(), (id, set) -> {
            if (set == null || set.size() == 0) {
                return Sets.newHashSet();
            }

            set.removeIf(next -> next.getName().equalsIgnoreCase(name));

            return set;
        });
    }

    @Override
    public boolean hasWarp(Island island, String name) {
        return this.storage.getIslandWarps().getOrDefault(island.getId(), Sets.newHashSet()).stream()
          .anyMatch(data -> data.getName().equalsIgnoreCase(name));
    }
}
