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
        String message = e.getMessage();
        String name = Emotes.getInstance().getName();
        if (e.getPlayer().hasPermission(Emotes.GetEmotes().getString("emotes."+name+".permission"))) {
            message = message.replace(Emotes.GetEmotes().getString("emotes."+name+".text"), ChatColor.translateAlternateColorCodes('&', Emotes.GetEmotes().getString("emotes."+name+".emote")));
            e.setMessage(message);
        }
    }
}
