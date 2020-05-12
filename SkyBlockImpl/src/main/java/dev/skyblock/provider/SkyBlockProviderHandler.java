package dev.skyblock.provider;

import com.google.common.collect.Lists;
import dev.skyblock.SkyBlock;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SkyBlockProviderHandler implements ProviderAPI {

    private static ProviderInfo info;
    private static SkyBlockProvider provider;

    public void loadProviders() {
        Path providerPath = SkyBlock.getInstance().getPath().resolve("providers");

        try {
            Files.walkFileTree(providerPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                    System.out.println(file.toFile());
                    SkyBlockProviderHandler.this.loadProvider(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadProvider(Path provider) {
        File file = provider.toFile();
        SkyBlockProviderLoader loader;

        try {
            loader = new SkyBlockProviderLoader(file);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Bukkit.shutdown();
            return;
        }

        List<String> names = Lists.newArrayList();
        try (JarFile jar = new JarFile(file)) {
            String mainClass = null;

            for (Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements();) {
                JarEntry entry = entries.nextElement();

                if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                    continue;
                }

                String className = entry.getName().replace(".class", "").replace("/", ".");
                names.add(className);

                try {
                    Class clazz = loader.loadClass(className);
                    ProviderInfo providerInfo = (ProviderInfo) clazz.getAnnotation(ProviderInfo.class);
                    if (providerInfo != null || clazz.getSuperclass().getName().equals(SkyBlockProvider.class.getName())) {
                        info = providerInfo;
                        mainClass = className;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            if (mainClass == null) {
                throw new IllegalStateException("Provider has no main class.");
            }

            SkyBlock.getInstance().getLogger().info("Attempting to load " + mainClass);
            Class<? extends SkyBlockProvider> providerClass = loader.loadClass(mainClass).asSubclass(SkyBlockProvider.class);

            SkyBlockProvider instance = providerClass.getConstructor().newInstance();
            if (instance == null) {
                throw new IllegalStateException("Could not instantiate main provider class.");
            }

            SkyBlockProviderHandler.provider = instance;

            instance.onStartup();
            SkyBlock.getInstance().getLogger().info("Loaded SkyBlock provider, " + info.name() + " by " + info.author() + " successfully!");
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SkyBlockProvider getProvider() {
        return provider;
    }

    @Override
    public ProviderInfo getProviderInfo() {
        return info;
    }
}
