package dev.skyblock.provider;

import com.google.common.collect.Lists;
import dev.skyblock.SkyBlockAPI;
import dev.skyblock.challenge.Challenge;
import dev.skyblock.island.IslandTemplate;
import dev.skyblock.provider.challenge.ExampleChallenge;
import dev.skyblock.provider.template.ExampleTemplate;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@ProviderInfo(name = "Default Provider", version = "1.0", author = "Obadiah Crowe")
public class DefaultProvider extends SkyBlockProvider {

    @Override
    public void onStartup() {
        //
    }

    @Override
    public void onShutdown() {
        //
    }

    @Override
    public List<Challenge> getChallenges() {
        return Lists.newArrayList(
          new ExampleChallenge()
        );
    }

    @Override
    public List<IslandTemplate> getTemplates() {
        return Lists.newArrayList(
          new ExampleTemplate()
        );
    }
}
