package dev.skyblock.challenge;

import dev.skyblock.islander.Islander;
import org.bukkit.Material;

public interface Challenge extends ChallengeBase {

    String getTitle();

    Material getIcon();

    ChallengeStage[] getStages();

    void onComplete(Islander islander);
}
