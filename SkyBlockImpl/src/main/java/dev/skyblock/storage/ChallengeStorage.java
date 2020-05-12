package dev.skyblock.storage;

import dev.skyblock.SkyBlock;
import dev.skyblock.challenge.Challenge;
import dev.skyblock.config.type.JsonConfig;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ChallengeStorage extends JsonConfig<ChallengeStorage> {

    private static final ChallengeStorage DEFAULT_STORAGE = new ChallengeStorage(new HashMap<>());

    private Map<UUID, List<Challenge>> challenges;

    /**
     * Represents a configuration file.
     */
    public ChallengeStorage() {
        super(ChallengeStorage.class);
    }

    /**
     * Represents a configuration file.
     */
    public ChallengeStorage(Map<UUID, List<Challenge>> challenges) {
        super(ChallengeStorage.class);

        this.challenges = challenges;
    }

    public Map<UUID, List<Challenge>> getChallenges() {
        return this.challenges;
    }

    @Override
    public Path getPath() {
        return SkyBlock.getInstance().getPath().resolve("storage").resolve("challenges.json");
    }

    @Override
    public ChallengeStorage getDefaultConfig() {
        return DEFAULT_STORAGE;
    }
}
