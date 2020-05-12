package dev.skyblock.provider;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import dev.skyblock.SkyBlock;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.Set;

public class SkyBlockProviderLoader extends URLClassLoader {

    private static final Map<String, Class<?>> CLASS_MAP = Maps.newConcurrentMap();
    private final Set<Class<?>> classes = Sets.newConcurrentHashSet();

    public SkyBlockProviderLoader(File file) throws MalformedURLException {
        super(new URL[] { file.toURI().toURL() }, SkyBlock.class.getClassLoader());
    }

    public void addUrl(URL url) {
        super.addURL(url);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> clazz = CLASS_MAP.get(name);
        if (clazz != null) {
            return clazz;
        }

        clazz = super.findClass(name);
        this.classes.add(clazz);
        CLASS_MAP.put(name, clazz);

        return clazz;
    }

    public Set<Class<?>> getClasses() {
        return this.classes;
    }

    @Override
    public void close() throws IOException {
        super.close();

        this.classes.forEach(clazz -> {
            if (CLASS_MAP.remove(clazz.getName()) == null) {
                throw new RuntimeException("Failed to issue class map cleanup!");
            }
        });

        this.classes.clear();
    }
}
