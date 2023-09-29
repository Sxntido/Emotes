package team.aquatic.studios.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import team.aquatic.studios.Emotes;
import team.aquatic.studios.tools.Utils;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String g = Emotes.getChatVault().getPrimaryGroup(p);
        if (Emotes.GetConfig().getBoolean("modules.chat-format")) {
            if (p.hasPermission("emotes.chat.color"))
                e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
            String format = "";
            if (Emotes.GetConfig().getConfigurationSection("chat.groups." + g) != null) {
                format = Emotes.GetConfig().getString("chat.groups." + g);
            } else {
                format = Emotes.GetConfig().getString("chat.groups.default");
            }
            format = format.replace("{name}", p.getName());
            format = format.replace("{displayname}", "%s");
            format = replacePlaceholderAPI(p, format);
            format = replaceVault(p, format);
            format = ChatColor.translateAlternateColorCodes('&', format);
            format = format.replace("%", "%%");
            format = format.replace("{message}", "%2$s");
            e.setFormat(format);
        }
    }

    public String replaceVault(Player p, String message) {
        String holders = message;
        String rank = null;
        String prefix = null;
        String suffix = null;
        prefix = Emotes.getChatVault().getPlayerPrefix(p);
        suffix = Emotes.getChatVault().getPlayerSuffix(p);
        rank = Emotes.getChatVault().getPrimaryGroup(p);
        holders = holders.replace("{prefix}", prefix);
        holders = holders.replace("{suffix}", suffix);
        holders = holders.replace("{rank}", rank);
        return holders;
    }

    public String replacePlaceholderAPI(Player p, String message) {
        String holders = message;
        if (Emotes.GetConfig().getBoolean("modules.chat-format") && PlaceholderAPI.containsPlaceholders(holders))
            holders = PlaceholderAPI.setPlaceholders(p, holders);
        return holders;
    }
}