package dev.skyblock.template;

import com.google.common.collect.Maps;
import dev.skyblock.island.IslandTemplate;
import dev.skyblock.island.TemplateAPI;
import dev.skyblock.provider.SkyBlockProvider;

import java.util.Map;

public class TemplateHandler implements TemplateAPI {

    private static final Map<String, IslandTemplate> NAMED_TEMPLATES = Maps.newConcurrentMap();

    private final SkyBlockProvider provider;

    public TemplateHandler(SkyBlockProvider provider) {
        this.provider = provider;

        this.provider.getTemplates().forEach(this::registerTemplate);
    }

    public void registerTemplate(IslandTemplate template) {
        NAMED_TEMPLATES.put(template.getName().toLowerCase(), template);
    }

    @Override
    public IslandTemplate getByName(String name) {
        return NAMED_TEMPLATES.getOrDefault(name.toLowerCase(), null);
    }
}
