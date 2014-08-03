package nz.co.noirland.effectcommands.config;

import nz.co.noirland.effectcommands.EffectType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class PluginConfig extends Config {

    private static PluginConfig inst;

    private PluginConfig() {
        super("config.yml");
    }

    public static PluginConfig inst() {
        if(inst == null) {
            inst = new PluginConfig();
        }

        return inst;
    }

    public boolean getDebug()         { return config.getBoolean("debug",       false);}

    public List<EffectType> getEffects() {

        ArrayList<EffectType> effects = new ArrayList<EffectType>();
        ConfigurationSection section = config.getConfigurationSection("effects");

        for(String name : section.getKeys(false)) {
            String effect = section.getString(name + ".effect");
            int level = section.getInt(name + ".level", 0);
            PotionEffectType type = PotionEffectType.getByName(effect);

            effects.add(new EffectType(type, name, level));
        }

        return effects;
    }

    public void reload() {
        loadFile();
    }
}
