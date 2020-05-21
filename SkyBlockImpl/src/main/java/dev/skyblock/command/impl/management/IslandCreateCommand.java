package dev.skyblock.command.impl.management;

import dev.skyblock.SkyBlockAPI;
import dev.skyblock.command.Command;
import dev.skyblock.command.CommandSource;
import dev.skyblock.island.Island;
import dev.skyblock.island.IslandAPI;
import dev.skyblock.islander.Islander;
import dev.skyblock.islander.IslanderImplementation;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IslandCreateCommand extends Command {

    /**
     * Represents a command.
     */
    public IslandCreateCommand() {
        super("create", "skyblock.island.create");

        this.setDescription("Creates a new island.");
        this.addAliases("c");
        this.removePermittedSources(
          CommandSource.COMMAND_BLOCK,
          CommandSource.CONSOLE
        );

        this.setMinArgs(0);
        this.setMaxArgs(0);
    }

    @Override
    protected void execute(CommandSender sender, String... args) throws Exception {
        IslandAPI api = SkyBlockAPI.get().getIslandAPI();
        IslanderImplementation islanderAPI = (IslanderImplementation) SkyBlockAPI.get().getIslanderAPI();

        Islander islander = islanderAPI.getIslander((Player) sender);
        Island island = api.createIsland(islander, SkyBlockAPI.get().getProviderAPI().getProvider().getTemplates().get(0));

        ((Player) sender).teleport(island.getSpawn().toLocation());
        sender.sendMessage(ChatColor.GREEN + "Island created.");
    }
}
