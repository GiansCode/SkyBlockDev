package dev.skyblock.challenge;

import java.util.Set;

public interface ChallengeAPI {

    void registerChallenge(Class<? extends Challenge> challenge);

    <T extends Challenge> T getChallenge(Class<T> challengeClass);

    void registerChallengeStage(Class<? extends Challenge> challenge, ChallengeStage stage);

    Set<ChallengeStage> getStages(Challenge challenge);
}
