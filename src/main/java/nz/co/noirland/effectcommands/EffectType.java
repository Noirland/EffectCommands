package nz.co.noirland.effectcommands;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EffectType {

    private PotionEffectType type;
    private String name;
    private int level;

    public EffectType(PotionEffectType type, String name, int level) {
        this.type = type;
        this.name = name;
        this.level = level;
    }

    public PotionEffectType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public PotionEffect getPotionEffect() {
        return new PotionEffect(type, Integer.MAX_VALUE, level);
    }
}
