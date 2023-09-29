package team.aquatic.studios;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import team.aquatic.studios.commands.EmoteCMD;
import team.aquatic.studios.events.Transform;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import team.aquatic.studios.files.Config;
import team.aquatic.studios.listeners.ChatListener;
import team.aquatic.studios.tools.Utils;

public class Emotes extends JavaPlugin {

    public static Emotes instance;
    public static boolean Vault = false;

    public static Permission permissions = null;

    public static Permission getPermission() {
        return permissions;
    }

    private static boolean PapiHook = false;

    public static boolean IsPapiHooked() {
        return PapiHook;
    }
    public static Economy economy = null;

    public static boolean isPapiHook;

    public static Chat chat = null;

    public static Chat getChatVault() {
        return chat;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(Chat.class);
        if (chatProvider != null)
            chat = (Chat)chatProvider.getProvider();
        return (chat != null);
    }

    public static boolean IsVaultHooked() {
        return Vault;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(Permission.class);
        if (permissionProvider != null)
            permissions = (Permission)permissionProvider.getProvider();
        return (permissions != null);
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null)
            economy = (Economy)economyProvider.getProvider();
        return (economy != null);
    }

    public static boolean getPapiHook() {
        return isPapiHook;
    }

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
        GetHooks();
        registerCommands();

    }

    public void onDisable() {

    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents((Listener) new Transform(), (Plugin) this);
        pm.registerEvents((Listener) new ChatListener(), (Plugin) this);
    }

    public void registerCommands() {
        getCommand("emotes").setExecutor(new EmoteCMD());
    }

    public void GetHooks() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                PapiHook = true;
                Utils.getLoggs("&fPlugin &ePlaceholderAPI &fHooked successfully", true);
            } else {
                Utils.getLoggs("&fPlugin &ePlaceholderAPI &fis not hooked due to is disabled", true);
            }
        } else {
            Utils.getLoggs("&fPlugin &ePlaceholderAPI &fis not hooked due to not found", true);
        }
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            if (Bukkit.getPluginManager().isPluginEnabled("Vault")) {
                Vault = true;
                setupEconomy();
                setupPermissions();
                setupChat();
                Utils.getLoggs("&fPlugin &eVault &fHooked successfully", true);
            } else {
                Utils.getLoggs("&fPlugin &eVault &fis not hooked due to is disabled", true);
            }
        } else {
            Utils.getLoggs("&fPlugin &eVault &fis not hooked due to not found", true);
        }
    }
}
