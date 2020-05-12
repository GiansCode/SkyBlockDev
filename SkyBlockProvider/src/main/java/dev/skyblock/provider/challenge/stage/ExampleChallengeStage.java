package dev.skyblock.provider.challenge.stage;

import dev.skyblock.challenge.ChallengeStage;
import dev.skyblock.islander.Islander;
import org.bukkit.ChatColor;

public class ExampleChallengeStage extends ChallengeStage {

    public ExampleChallengeStage() {
        super("Example Stage", "");
    }

    @Override
    public boolean meetsCriteria(Islander islander) {
        return true;
    }

    @Override
    public void onComplete(Islander islander) {
        islander.asBukkitPlayer().sendMessage(ChatColor.GREEN + "You have completed the example stage.");
    }
}
