package team.aquatic.studios.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.aquatic.studios.Emotes;
import team.aquatic.studios.tools.Sounds;

import java.util.ArrayList;

public class EmoteCMD implements CommandExecutor {

    private Emotes plugin = (Emotes) Emotes.getPlugin(Emotes.class);

    public boolean onCommand(CommandSender sender, Command command, String Label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', ("&b&lEmotes &7» &cError this command cannot be executed in console.")));
            return true;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lEmotes &fversion 1.1 Powered by Aquatic Studios"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            return true;

        }

        if (args[0].equalsIgnoreCase("help")) {
            if (p.hasPermission("emotes.help") || p.hasPermission("emotes.admin")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lEmotes &fv1.1 &7| &fCommands"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/emotes reload &7- &fCommand to reload the plugin"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/emotes list &7- &fCommand to view the list of emotes"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/emotes off &7- &fDisable chat emotes with this command"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/emotes on &7- &fActivate chat emotes with this command"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                return true;
            }
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("messages.no_permission").replace("%prefix%", Emotes.GetConfig().getString("modules.prefix"))));
        }

        if (args[0].equalsIgnoreCase("list")) {
            if (p.performCommand("emotelist")) {
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("on")) {
            if (p.hasPermission("emotes.switch") || p.hasPermission("emotes.admin")) {
                if (this.plugin.status = true) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("messages.enabled").replace("%prefix%", Emotes.GetConfig().getString("modules.prefix"))));
                    if (Emotes.GetConfig().getBoolean("display.enabled.switch")) {
                        p.sendTitle(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("display.enabled.title")), ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("display.enabled.subtitle")));
                        Sounds.APISounds(p, Emotes.GetConfig().getString("display.enabled.sound"));
                        return true;
                    }
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("messages.rank_permission").replace("%prefix%", Emotes.GetConfig().getString("modules.prefix"))));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("off")) {
            if (p.hasPermission("emotes.switch") || p.hasPermission("emotes.admin")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("messages.disabled").replace("%prefix%", Emotes.GetConfig().getString("modules.prefix"))));
                if (Emotes.GetConfig().getBoolean("display.disabled.switch")) {
                    p.sendTitle(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("display.disabled.title")), ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("display.disabled.subtitle")));
                    Sounds.APISounds(p, Emotes.GetConfig().getString("display.disabled.sound"));
                    this.plugin.status = false;
                    return true;
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("messages.rank_permission").replace("%prefix%", Emotes.GetConfig().getString("modules.prefix"))));
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (p.hasPermission("emotes.reload") || p.hasPermission("emotes.admin")) {
                Emotes.GetConfig().Reload();
                Emotes.GetEmotes().Reload();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("messages.reload").replace("%prefix%", Emotes.GetConfig().getString("modules.prefix"))));
                return true;
            }
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Emotes.GetConfig().getString("messages.no_permission").replace("%prefix%", Emotes.GetConfig().getString("modules.prefix"))));
        }
        return false;
    }
}