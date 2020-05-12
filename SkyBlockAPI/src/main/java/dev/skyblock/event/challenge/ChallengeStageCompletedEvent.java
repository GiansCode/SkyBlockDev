package dev.skyblock.event.challenge;

import dev.skyblock.challenge.Challenge;
import dev.skyblock.challenge.ChallengeStage;
import dev.skyblock.event.generic.IslanderEvent;
import dev.skyblock.islander.Islander;

public class ChallengeStageCompletedEvent extends IslanderEvent {

    private final Challenge challenge;
    private final ChallengeStage stage;

    public ChallengeStageCompletedEvent(Islander islander, Challenge challenge, ChallengeStage stage) {
        super(islander);

        this.challenge = challenge;
        this.stage = stage;
    }

    public Challenge getChallenge() {
        return this.challenge;
    }

    public ChallengeStage getStage() {
        return this.stage;
    }
}
