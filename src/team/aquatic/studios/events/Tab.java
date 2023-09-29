package team.aquatic.studios.events;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Tab implements TabCompleter {

    List<String> arguments = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Player p = (Player) sender;
        if (p.hasPermission("emotes.admin")) {
            if (args.length == 1) {
                if (this.arguments.isEmpty()) {
                    this.arguments.add("help");
                    this.arguments.add("reload");
                    this.arguments.add("list");
                }
                List<String> result = new ArrayList<>();
                if (args.length == 1) {
                    for (String a : this.arguments) {
                        if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                            result.add(a);
                    }
                    return result;
                }
                return null;
            }
        }
        return null;
    }
}