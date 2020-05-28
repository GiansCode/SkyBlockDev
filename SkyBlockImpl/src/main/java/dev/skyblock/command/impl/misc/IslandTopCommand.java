package dev.skyblock.command.impl.misc;

import dev.skyblock.SkyBlockAPI;
import dev.skyblock.command.Command;
import dev.skyblock.command.CommandSource;
import dev.skyblock.island.island.IslandValueCalculator;
import org.bukkit.command.CommandSender;

public class IslandTopCommand extends Command {

    /**
     * Represents a command.
     */
    public IslandTopCommand() {
        super("top", "skyblock.island.top");

        this.setDescription("View the top islands.");
        this.removePermittedSources(
          CommandSource.COMMAND_BLOCK,
          CommandSource.CONSOLE
        );

        this.setMinArgs(0);
        this.setMaxArgs(0);
    }

    @Override
    protected void execute(CommandSender sender, String... args) throws Exception {
        IslandValueCalculator calculator = SkyBlockAPI.get().getProviderAPI().getProvider().getValueCalculator();

        calculator.getTopIslands(10);
    }
}
