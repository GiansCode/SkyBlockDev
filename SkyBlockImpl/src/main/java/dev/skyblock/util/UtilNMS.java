package dev.skyblock.util;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class UtilNMS {

    public static final String VERSION;

    private static final Class<?> CRAFT_PLAYER;
    private static final Class<?> CRAFT_ENTITY;
    private static final Class<?> CRAFT_WORLD;

    static {
        VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        try {
            CRAFT_PLAYER = Class.forName("org.bukkit.craftbukkit." + VERSION + ".entity.CraftPlayer");
            CRAFT_ENTITY = Class.forName("org.bukkit.craftbukkit." + VERSION + ".entity.CraftEntity");
            CRAFT_WORLD = Class.forName("org.bukkit.craftbukkit." + VERSION + ".CraftWorld");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static Object getNMSWorld(World world) {
        try {
            Object craftWorld = CRAFT_WORLD.cast(world);
            return craftWorld.getClass().getDeclaredMethod("getHandle").invoke(craftWorld);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object getNMSPlayer(Player player) {
        try {
            Object craftPlayer = CRAFT_PLAYER.cast(player);
            return craftPlayer.getClass().getDeclaredMethod("getHandle").invoke(craftPlayer);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object getNMSEntity(Entity entity) {
        try {
            Object craftEntity = CRAFT_ENTITY.cast(entity);
            return craftEntity.getClass().getDeclaredMethod("getHandle").invoke(craftEntity);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
