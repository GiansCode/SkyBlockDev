package dev.skyblock;

import dev.skyblock.ban.BanAPI;
import dev.skyblock.biome.BiomeAPI;
import dev.skyblock.challenge.ChallengeAPI;
import dev.skyblock.invitation.InvitationAPI;
import dev.skyblock.island.IslandAPI;
import dev.skyblock.grid.GridAPI;
import dev.skyblock.island.TemplateAPI;
import dev.skyblock.islander.IslanderAPI;
import dev.skyblock.provider.ProviderAPI;
import dev.skyblock.warp.WarpAPI;
import dev.skyblock.world.WorldAPI;

import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;

public interface SkyBlockAPI {

    static SkyBlockAPI get() {
        return Implementation.IMPLEMENTATION.get();
    }

    String getVersion();

    Path getPath();

    BanAPI getBanAPI();

    BiomeAPI getBiomeAPI();

    ChallengeAPI getChallengeAPI();

    InvitationAPI getInvitationAPI();

    GridAPI getGridAPI();

    IslandAPI getIslandAPI();

    IslanderAPI getIslanderAPI();

    ProviderAPI getProviderAPI();

    TemplateAPI getTemplateAPI();

    WarpAPI getWarpAPI();

    WorldAPI getWorldAPI();

    class Implementation {
        static final AtomicReference<SkyBlockAPI> IMPLEMENTATION = new AtomicReference<>(null);
    }
}
