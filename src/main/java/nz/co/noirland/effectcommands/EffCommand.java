package nz.co.noirland.effectcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EffCommand implements CommandExecutor {

    private EffectCommands plugin = EffectCommands.inst();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {

        if(args.length == 0) {

            StringBuilder builder = new StringBuilder(ChatColor.GOLD + "Effects: " + ChatColor.RESET);

            String delim = "";
            for(EffectType effect : plugin.getEffects()) {
                builder.append(delim);

                if(sender.hasPermission(plugin.getPermission(effect.getName()))) {
                    builder.append(ChatColor.GREEN);
                }else{
                    builder.append(ChatColor.RED);
                }
                builder.append(effect.getName());

                delim = ChatColor.RESET + ", ";
            }
            sender.sendMessage(builder.toString());
            return true;
        }

        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players may use effects.");
            return true;
        }

        Player player = (Player) sender;

        EffectType type = plugin.getEffect(args[0]);

        if(type == null) {
            player.sendMessage(ChatColor.DARK_RED + "Effect does not exist.");
            return true;
        }

        if(!sender.hasPermission(plugin.getPermission(type.getName()))) {
            player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use that effect.");
            return true;
        }

        if(player.hasPotionEffect(type.getType())) {
            player.removePotionEffect(type.getType());
            EffectCommands.sendMessage(player, "Removed " + type.getName() + " effect.");
            return true;
        }

        player.addPotionEffect(type.getPotionEffect(), true);
        EffectCommands.sendMessage(player, "Added effect " + type.getName());
        return true;
    }
}
