package dev.skyblock.challenge;

import dev.skyblock.islander.Islander;

import java.util.Arrays;
import java.util.List;

public abstract class ChallengeStage implements ChallengeBase {

    private final String name;
    private final List<String> lore;

    public ChallengeStage(String name, String... lore) {
        this.name = name;
        this.lore = Arrays.asList(lore);
    }

    @Override
    public String getTitle() {
        return this.name;
    }

    @Override
    public List<String> getLore() {
        return this.lore;
    }

    public abstract boolean meetsCriteria(Islander islander);

    public abstract void onComplete(Islander islander);
}
