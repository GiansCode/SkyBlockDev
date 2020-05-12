package dev.skyblock.event.challenge;

import dev.skyblock.challenge.Challenge;
import dev.skyblock.event.generic.IslanderEvent;
import dev.skyblock.islander.Islander;

public class ChallengeCompletedEvent extends IslanderEvent {

    private final Challenge challenge;

    public ChallengeCompletedEvent(Islander islander, Challenge challenge) {
        super(islander);

        this.challenge = challenge;
    }

    public Challenge getChallenge() {
        return this.challenge;
    }
}
