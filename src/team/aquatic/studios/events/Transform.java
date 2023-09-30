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
    private Emotes plugin = (Emotes)Emotes.getPlugin(Emotes.class);
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        if (this.plugin.status) {
            ConfigurationSection msgconfig = Emotes.GetEmotes().getConfigurationSection("emotes");
            for (String aquatic : msgconfig.getKeys(false)) {
                String trigger = Emotes.GetEmotes().getString("emotes." + aquatic + ".trigger");
                String emote = Emotes.GetEmotes().getString("emotes." + aquatic + ".emote");
                String permission = Emotes.GetEmotes().getString("emotes." + aquatic + ".permission");
                if (message.contains(trigger)) {
                    if (player.hasPermission(permission)) {
                        message = message.replace(trigger, emote);
                        event.setMessage(ChatColor.translateAlternateColorCodes('&', message));
                        continue;
                    }
                    if (message.contains(trigger)) {
                        event.setMessage(trigger);
                    }
                }
            }
        }
    }
}