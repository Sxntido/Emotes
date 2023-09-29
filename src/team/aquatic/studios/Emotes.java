package team.aquatic.studios;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import team.aquatic.studios.events.Transform;
import team.aquatic.studios.files.Config;

public class Emotes extends JavaPlugin {

    public static Emotes instance;

    public static Emotes getInstance() {
        return instance;
    }

    private static Config emotes;

    public static Config GetEmotes() {
        return emotes;
    }

    private static Config config;

    public static Config GetConfig() {
        return config;
    }

    public void onEnable() {
        instance = this;
        emotes = new Config("emotes");
        config = new Config("config");

        registerEvents();

    }

    public void onDisable() {

    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents((Listener) new Transform(), (Plugin) this);
    }
}
