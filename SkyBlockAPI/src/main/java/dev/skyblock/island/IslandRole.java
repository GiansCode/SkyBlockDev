package dev.skyblock.island;

import org.bukkit.ChatColor;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum IslandRole {

    /**
     * The owner of the island.
     */
    OWNER(ChatColor.DARK_RED + "Owner", true, true),

    /**
     * Island members, they have permission to build and break blocks, but not to manage the island.
     */
    MEMBER(ChatColor.GOLD + "Member", false, true),

    /**
     * Players who cannot interact with the island whatsoever, but can simply view it.
     */
    VISITOR(ChatColor.YELLOW + "Guest", false, false);

    private final String name;
    private final boolean canManage;
    private final boolean canBreak;

    IslandRole(String name, boolean canManage, boolean canBreak) {
        this.name = name;
        this.canManage = canManage;
        this.canBreak = canBreak;
    }

    public String getName() {
        return this.name;
    }

    public boolean canManage() {
        return this.canManage;
    }

    public boolean canBreak() {
        return this.canBreak;
    }

    @Override
    public String toString() {
        return "IslandRole{" +
          "canManage=" + canManage +
          ", canBreak=" + canBreak +
          '}';
    }
}
