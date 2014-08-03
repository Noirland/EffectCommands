package nz.co.noirland.effectcommands;

import nz.co.noirland.effectcommands.config.PluginConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class EffectCommands extends JavaPlugin {

    private static EffectCommands inst;
    private List<EffectType> effects;

    public static EffectCommands inst() {
        return inst;
    }

    @Override
    public void onEnable() {
        inst = this;
        PluginConfig conf = PluginConfig.inst();

        getServer().getPluginCommand("eff").setExecutor(new EffCommand());
        effects = conf.getEffects();
    }

    public List<EffectType> getEffects() {
        return effects;
    }

    public EffectType getEffect(String name) {
        for(EffectType type : effects) {
            if(type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    public Permission getPermission(String name) {
        return new Permission("effectcommands.effect." + name, PermissionDefault.OP);
    }

    public static void sendMessage(CommandSender to, String msg) {
        to.sendMessage(ChatColor.RED + "[EffectCommands] " + ChatColor.RESET + msg);
    }
}
