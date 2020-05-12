package dev.skyblock.islander;

import dev.skyblock.storage.IslanderStorage;

import java.util.UUID;

public class IslanderImplementation implements IslanderAPI {

    private final IslanderStorage storage;

    public IslanderImplementation(IslanderStorage storage) {
        this.storage = storage;
    }

    @Override
    public Islander getIslander(UUID uuid) {
        return this.storage.getIslanders().compute(uuid, (u, i) -> {
            if (i == null) {
                return new SkyBlockIslander(uuid);
            }

            return i;
        });
    }
}
