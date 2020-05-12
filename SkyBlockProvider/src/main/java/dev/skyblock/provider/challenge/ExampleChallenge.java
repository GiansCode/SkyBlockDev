package dev.skyblock.provider.challenge;

import com.google.common.collect.Lists;
import dev.skyblock.challenge.AbstractChallenge;
import dev.skyblock.challenge.ChallengeStage;
import dev.skyblock.islander.Islander;
import dev.skyblock.provider.challenge.stage.ExampleChallengeStage;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.List;

public class ExampleChallenge extends AbstractChallenge {

    private final String[] lore;

    public ExampleChallenge() {
        super("Example", Material.DIAMOND);

        this.lore = new String[] {
          "This is an example challenge,",
          "provided by the default",
          "provider."
        };
    }

    @Override
    public ChallengeStage[] getStages() {
        return new ChallengeStage[] {
          new ExampleChallengeStage()
        };
    }

    @Override
    public void onComplete(Islander islander) {
        islander.asBukkitPlayer().sendMessage(ChatColor.GREEN + "You have completed the example challenge.");
    }

    @Override
    public List<String> getLore() {
        return Lists.newArrayList(this.lore);
    }
}
