package team.aquatic.studios.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.aquatic.studios.Emotes;
import team.aquatic.studios.tools.Utils;

import java.util.ArrayList;
import java.util.List;

public class EmoteCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String Label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ("&b&lEmotes &8» &cError this command cannot be executed in console.")));
            return true;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lEmotes &fversion 1.0 Powered by Aquatic Studios"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            return true;

        }

        if (args[0].equalsIgnoreCase("help")) {
            if (p.hasPermission("emotes.help") || p.hasPermission("emotes.admin")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lEmotes &fv1.0 &7| &fCommands"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/emotes reload &7- &fCommand to reload the plugin"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/emotes list &7- &fCommand to view the list of emotes"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                return true;
            }
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("messages.nopermission").replace("%prefix%", Emotes.GetConfig().getString("modules.prefix"))));
        }

        if (args[0].equalsIgnoreCase("list")) {
            if (p.hasPermission("emotes.list") || p.hasPermission("emotes.admin")) {
                p.performCommand("emotelist");
            }
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("messages.rank_permission").replace("%prefix%", Emotes.GetConfig().getString("modules.prefix"))));
            return false;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (p.hasPermission("emotes.reload") || p.hasPermission("emotes.admin")) {
                Emotes.GetConfig().Reload();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("messages.reload").replace("%prefix%", Emotes.GetConfig().getString("modules.prefix"))));
                return true;
            }

            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("messages.nopermission").replace("%prefix%", Emotes.GetConfig().getString("modules.prefix"))));

        } else {

            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("messages.error").replace("%prefix%", Emotes.GetConfig().getString("modules.prefix"))));
        }
        return true;
    }
}