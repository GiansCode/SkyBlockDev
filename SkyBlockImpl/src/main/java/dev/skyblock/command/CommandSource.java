package dev.skyblock.command;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import javax.annotation.concurrent.Immutable;
import java.util.stream.Stream;

@Immutable
public enum CommandSource {

    /**
     * A player on the server.
     */
    PLAYER(Player.class),

    /**
     * The console or via code.
     */
    CONSOLE(ConsoleCommandSender.class),

    /**
     * Any command block (if enabled).
     */
    COMMAND_BLOCK(BlockCommandSender.class);

    private final Class<?> senderClass;

    CommandSource(Class<?> senderClass) {
        this.senderClass = senderClass;
    }

    public Class<?> getSenderClass() {
        return this.senderClass;
    }

    /**
     * Obtains the corresponding command source depending based on whether the sender can be assigned from their class.
     *
     * @param sender Sender to check.
     *
     * @return Corresponding CommandSource.
     */
    public static CommandSource fromSender(CommandSender sender) {
        return Stream.of(CommandSource.values())
          .filter(source -> source.getSenderClass().isAssignableFrom(sender.getClass()))
          .findFirst()
          .orElse(null);
    }
}
