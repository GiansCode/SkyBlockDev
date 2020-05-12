package dev.skyblock.challenge;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import dev.skyblock.storage.ChallengeStorage;
import dev.skyblock.util.UtilConcurrency;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

public class ChallengeImplementation implements ChallengeAPI {

    private static final Map<Class<? extends Challenge>, Challenge> INSTANCE_MAPPINGS = Maps.newConcurrentMap();

    private final ChallengeStorage storage;

    public ChallengeImplementation(ChallengeStorage storage) {
        this.storage = storage;
    }

    @Override
    public void registerChallenge(Class<? extends Challenge> challenge) {
        UtilConcurrency.runAsync(() -> {
            Challenge instance = null;
            try {
                instance = challenge.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                return;
            }

            INSTANCE_MAPPINGS.put(challenge, instance);
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Challenge> T getChallenge(Class<T> challengeClass) {
        return (T) INSTANCE_MAPPINGS.getOrDefault(challengeClass, null);
    }

    @Override
    public void registerChallengeStage(Class<? extends Challenge> challenge, ChallengeStage stage) {
        // TODO: 13/05/2020  
    }

    @Override
    public Set<ChallengeStage> getStages(Challenge challenge) {
        return Sets.newHashSet(challenge.getStages());
    }
}
