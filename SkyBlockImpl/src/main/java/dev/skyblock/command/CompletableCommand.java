package dev.skyblock.command;

import com.mojang.brigadier.tree.LiteralCommandNode;

import javax.annotation.concurrent.Immutable;

@Immutable
public interface CompletableCommand {

    /**
     * @return All completions for your command.
     */
    LiteralCommandNode<?> getCompletions();
}
