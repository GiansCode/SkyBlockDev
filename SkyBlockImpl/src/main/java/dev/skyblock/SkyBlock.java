package dev.skyblock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.skyblock.ban.BanAPI;
import dev.skyblock.ban.BanImplementation;
import dev.skyblock.biome.BiomeAPI;
import dev.skyblock.biome.BiomeImplementation;
import dev.skyblock.challenge.ChallengeAPI;
import dev.skyblock.challenge.ChallengeImplementation;
import dev.skyblock.command.CommandAPI;
import dev.skyblock.command.impl.IslandCommand;
import dev.skyblock.config.LoadableConfig;
import dev.skyblock.config.impl.GridConfig;
import dev.skyblock.config.impl.PluginConfig;
import dev.skyblock.grid.GridImplementation;
import dev.skyblock.invitation.InvitationAPI;
import dev.skyblock.invitation.InvitationImplementation;
import dev.skyblock.island.IslandAPI;
import dev.skyblock.grid.GridAPI;
import dev.skyblock.island.IslandImplementation;
import dev.skyblock.island.TemplateAPI;
import dev.skyblock.islander.IslanderAPI;
import dev.skyblock.islander.IslanderImplementation;
import dev.skyblock.provider.ProviderAPI;
import dev.skyblock.provider.SkyBlockProvider;
import dev.skyblock.provider.SkyBlockProviderHandler;
import dev.skyblock.storage.*;
import dev.skyblock.task.ScheduledSaveTask;
import dev.skyblock.template.TemplateHandler;
import dev.skyblock.warp.WarpAPI;
import dev.skyblock.warp.WarpImplementation;
import dev.skyblock.world.WorldAPI;
import dev.skyblock.world.WorldImplementation;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;

public class SkyBlock extends JavaPlugin implements SkyBlockAPI {

    private static final Gson GSON_INSTANCE = new GsonBuilder()
      .serializeNulls()
      .disableHtmlEscaping()
      .setLenient()
      .setPrettyPrinting()
      .create();

    private static SkyBlock instance;
    private static Path path;

    private static PluginConfig pluginConfig;
    private static GridConfig gridConfig;
    private static SkyBlockProviderHandler providerHandler;

    private ChallengeStorage challengeStorage;
    private GridStorage gridStorage;
    private InvitationStorage invitationStorage;
    private IslanderStorage islanderStorage;
    private IslandStorage islandStorage;
    private WarpStorage warpStorage;

    private BanImplementation banImplementation;
    private BiomeImplementation biomeImplementation;
    private ChallengeImplementation challengeImplementation;
    private GridImplementation gridImplementation;
    private InvitationImplementation invitationImplementation;
    private IslandImplementation islandImplementation;
    private IslanderImplementation islanderImplementation;
    private TemplateHandler templateHandler;
    private WarpImplementation warpImplementation;
    private WorldImplementation worldImplementation;

    @Override
    public void onEnable() {
        instance = this;
        path = this.getDataFolder().toPath();

        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Path providerPath = path.resolve("providers");
        if (!Files.exists(providerPath)) {
            try {
                Files.createDirectory(providerPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Path storagePath = path.resolve("storage");
        if (!Files.exists(storagePath)) {
            try {
                Files.createDirectory(storagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Path schematicsPath = path.resolve("schematics");
        if (!Files.exists(schematicsPath)) {
            try {
                Files.createDirectory(schematicsPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        pluginConfig = LoadableConfig.getByClass(PluginConfig.class).load();
        gridConfig = LoadableConfig.getByClass(GridConfig.class).load();

        Implementation.IMPLEMENTATION.set(this);

        providerHandler = new SkyBlockProviderHandler();
        providerHandler.loadProviders();

        SkyBlockProvider provider = providerHandler.getProvider();
        if (provider == null) {
            this.getLogger().log(Level.SEVERE, "No SkyBlock provider found. Shutting down..");
            Bukkit.shutdown();
        }

        this.challengeStorage = LoadableConfig.getByClass(ChallengeStorage.class).load();
        this.gridStorage = LoadableConfig.getByClass(GridStorage.class).load();
        this.invitationStorage = LoadableConfig.getByClass(InvitationStorage.class).load();
        this.islanderStorage = LoadableConfig.getByClass(IslanderStorage.class).load();
        this.islandStorage = LoadableConfig.getByClass(IslandStorage.class).load();
        this.warpStorage = LoadableConfig.getByClass(WarpStorage.class).load();

        this.banImplementation = new BanImplementation();
        this.biomeImplementation = new BiomeImplementation();
        this.challengeImplementation = new ChallengeImplementation(this.challengeStorage);
        this.gridImplementation = new GridImplementation(provider, this.gridStorage);
        this.invitationImplementation = new InvitationImplementation(this.invitationStorage);
        this.islandImplementation = new IslandImplementation(this.islandStorage);
        this.islanderImplementation = new IslanderImplementation(this.islanderStorage);
        this.templateHandler = new TemplateHandler(provider);
        this.warpImplementation = new WarpImplementation(this.warpStorage);
        this.worldImplementation = new WorldImplementation();

        if (pluginConfig.enableDefaultCommands()) {
            CommandAPI.registerCommand(
              new IslandCommand()
            );
        }

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new ScheduledSaveTask(this), 18000L, 0L); // 15m
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Shutting down provider..");
        providerHandler.getProvider().onShutdown();

        this.getLogger().info("Saving data..");
        this.challengeStorage.save();
        this.gridStorage.save();;
        this.invitationStorage.save();
        this.islanderStorage.save();
        this.islandStorage.save();
        this.warpStorage.save();

        this.getLogger().info("Saving configs..");
        pluginConfig.save();
        gridConfig.save();
    }

    public ChallengeStorage getChallengeStorage() {
        return this.challengeStorage;
    }

    public GridStorage getGridStorage() {
        return this.gridStorage;
    }

    public InvitationStorage getInvitationStorage() {
        return this.invitationStorage;
    }

    public IslanderStorage getIslanderStorage() {
        return this.islanderStorage;
    }

    public IslandStorage getIslandStorage() {
        return this.islandStorage;
    }

    public WarpStorage getWarpStorage() {
        return this.warpStorage;
    }

    public Gson getGson() {
        return GSON_INSTANCE;
    }

    @Override
    public Path getPath() {
        return path;
    }

    public GridConfig getGridConfig() {
        return gridConfig;
    }

    @Override
    public String getVersion() {
        return this.getDescription().getVersion();
    }

    @Override
    public BanAPI getBanAPI() {
        return this.banImplementation;
    }

    @Override
    public BiomeAPI getBiomeAPI() {
        return this.biomeImplementation;
    }

    @Override
    public ChallengeAPI getChallengeAPI() {
        return this.challengeImplementation;
    }

    @Override
    public InvitationAPI getInvitationAPI() {
        return this.invitationImplementation;
    }

    @Override
    public GridAPI getGridAPI() {
        return this.gridImplementation;
    }

    @Override
    public IslandAPI getIslandAPI() {
        return this.islandImplementation;
    }

    @Override
    public IslanderAPI getIslanderAPI() {
        return this.islanderImplementation;
    }

    @Override
    public ProviderAPI getProviderAPI() {
        return providerHandler;
    }

    @Override
    public TemplateAPI getTemplateAPI() {
        return this.templateHandler;
    }

    @Override
    public WarpAPI getWarpAPI() {
        return this.warpImplementation;
    }

    @Override
    public WorldAPI getWorldAPI() {
        return this.worldImplementation;
    }

    public static SkyBlock getInstance() {
        return instance;
    }
}
