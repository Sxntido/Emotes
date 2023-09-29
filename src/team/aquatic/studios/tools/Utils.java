package team.aquatic.studios.tools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import team.aquatic.studios.Emotes;

public class Utils {

    public static String Color(String Text){
        return ChatColor.translateAlternateColorCodes('&', Text);
    }

    public static void sendMessage(Player p, String s, boolean prefix) {
        if(prefix) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("modules.prefix")+s));
        } else if(!prefix) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',s));
        }
    }

    public static void getLoggs(String s, boolean prefix) {
        if (prefix) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lEmotes &8» " +s));
        } else if (!prefix) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
        }
    }

}
