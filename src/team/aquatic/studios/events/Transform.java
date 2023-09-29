package team.aquatic.studios.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import team.aquatic.studios.Emotes;
import team.aquatic.studios.manager.Builder;

public class Transform implements Listener {

    @EventHandler
    public void Emotes(AsyncPlayerChatEvent event) {
        String msg = event.getMessage();
        Player player = event.getPlayer();
        if (Emotes.GetConfig().getBoolean("modules.emotes")) {
            ConfigurationSection msgCfg = Emotes.GetEmotes().getConfigurationSection("emotes");
            for (String key : msgCfg.getKeys(false)) {
                String trigger = Emotes.GetEmotes().getString("emotes." + key + ".trigger");
                String emote = Emotes.GetEmotes().getString("emotes." + key + ".emote");
                String permission = Emotes.GetEmotes().getString("emotes." + key + ".permission");
                if (msg.contains(trigger)) {
                    if (player.hasPermission(permission)) {
                        msg = msg.replace(trigger, emote);
                        event.setMessage(ChatColor.translateAlternateColorCodes('&', msg));
                        continue;
                    }
                    event.setCancelled(true);
                }
            }
        }
    }
}
