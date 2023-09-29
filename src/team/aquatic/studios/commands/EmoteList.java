package team.aquatic.studios.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.aquatic.studios.Emotes;

import java.util.ArrayList;
import java.util.List;

public class EmoteList implements CommandExecutor {

    public List<String> colorize(List<String> input) {
        List<String> result = new ArrayList<>();
        for (String string : input) {
            result.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        return result;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ("&b&lEmotes &7» &cError this command cannot be executed in console.")));
            return true;
        }
        Player p = (Player) sender;
        if (p.hasPermission("emotes.list") || p.hasPermission("emotes.admin")) {
            List<String> msg = colorize(Emotes.GetConfig().getStringList("emotes.list"));
            for (String string : msg) p.sendMessage(string);
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("messages.rank_permission").replace("%prefix%", Emotes.GetConfig().getString("modules.prefix"))));
            return true;
        }
        return true;
    }
}
