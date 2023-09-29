package team.aquatic.studios.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import team.aquatic.studios.Emotes;
import team.aquatic.studios.manager.Builder;

public class Transform implements Listener {

    @EventHandler
    public void PlayerAsyncChat(AsyncPlayerChatEvent e) {
        if (e.getPlayer().hasPermission("emotes.all")) {
            String name = Emotes.GetEmotes().toString();
            String message = e.getMessage();
            message = message.replace(Emotes.GetEmotes().getString("emotes."+ name +"text"), ChatColor.translateAlternateColorCodes('&', Emotes.GetEmotes().getString("emotes."+ name +".emote")));
            e.setMessage(message);
        }
        String name = Emotes.GetEmotes().toString();
        if (e.getPlayer().hasPermission(Emotes.GetEmotes().getString("emotes."+ name +"permission"))) {
            String message = e.getMessage();
            message = message.replace(Emotes.GetEmotes().getString("emotes."+ name +".text"), ChatColor.translateAlternateColorCodes('&', Emotes.GetEmotes().getString("emotes."+ name +".emote")));
            e.setMessage(message);
        }
    }
}
