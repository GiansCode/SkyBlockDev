package dev.skyblock.util;

import dev.skyblock.SkyBlock;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class UtilConcurrency {

    public static BukkitTask runAsync(Runnable runnable) {
        return Bukkit.getScheduler().runTaskAsynchronously(SkyBlock.getInstance(), runnable);
    }

    public static BukkitTask runSync(Runnable runnable) {
        return Bukkit.getScheduler().runTask(SkyBlock.getInstance(), runnable);
    }

    public static BukkitTask runAsyncTaskLater(Runnable runnable, long delayMs) {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(SkyBlock.getInstance(), runnable, delayMs);
    }

    public static BukkitTask runSyncTaskLater(Runnable runnable, long delayMs) {
        return Bukkit.getScheduler().runTaskLater(SkyBlock.getInstance(), runnable, delayMs);
    }

    public static int runAsyncRepeatingTask(Runnable runnable, long delayBetweenMs) {
        return Bukkit.getScheduler().scheduleAsyncRepeatingTask(SkyBlock.getInstance(), runnable, 0L, delayBetweenMs);
    }

    public static int runSyncRepeatingTask(Runnable runnable, long delayBetweenMs) {
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBlock.getInstance(), runnable, 0L, delayBetweenMs);
    }
}
