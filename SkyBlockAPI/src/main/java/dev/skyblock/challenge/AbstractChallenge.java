package dev.skyblock.challenge;

import org.bukkit.Material;

public abstract class AbstractChallenge implements Challenge {

    private final String name;
    private final Material icon;

    public AbstractChallenge(String name, Material icon) {
        this.name = name;
        this.icon = icon;
    }

    @Override
    public String getTitle() {
        return this.name;
    }

    @Override
    public Material getIcon() {
        return this.icon;
    }
}
