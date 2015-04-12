package nl.galaxias.youtubemc;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * Created by wdele on 10-04-15.
 */
public class YouTubeMC extends JavaPlugin {
    private static Plugin plugin;
    FileConfiguration config = getConfig();
    static HashMap<String, Boolean> recording = new HashMap<String, Boolean>();
    static HashMap<String, String> livestreaming = new HashMap<String, String>();

    public void onEnable() {
        plugin = this;

        initializeConfig();

        if (!(config.getBoolean("general.enable-plugin"))) {
            disablePlugin("The plugin is not enabled in the config.");
        }

        registerEvents(this, new YouTubersGui());

        getCommand("youtubemc").setExecutor(new YouTubeMCCommand());
        getCommand("yt").setExecutor(new YtCommand());
        getCommand("record").setExecutor(new RecordCommand());
        getCommand("livestream").setExecutor(new LivestreamCommand());
        getCommand("newvideo").setExecutor(new NewVideoCommand());
    }

    public void onDisable() {
        plugin = null;
    }

    public void disablePlugin(String reason) {
        getLogger().info("Disabling... reason: " + reason);
        Bukkit.getPluginManager().disablePlugin(plugin);
    }

    public void initializeConfig() {
        config.addDefault("general.enable-plugin", true);
        config.addDefault("messages.no-permission", "&cYou do not have permission to execute this command!");
        config.addDefault("messages.start-recording", "&c!youtuber! just started recording! Video name: !videoname!");
        config.addDefault("messages.stop-recording", "&c!youtuber! just stopped recording.");
        config.addDefault("messages.start-livestream", "&c!youtuber! just started a livestream! URL: !url!");
        config.addDefault("messages.stop-livestream", "&c!youtuber! just stopped the livestream! URL: !url!");
        config.addDefault("messages.new-video", "&c!youtuber! just uploaded a new video! Video name: !videoname!, URL: !url!");
        config.addDefault("messages.already-recording", "&c!youtuber!, you are already recording!");
        config.addDefault("messages.not-recording", "&c!youtuber!, you aren't even recording!");

        config.options().copyDefaults(true);
        saveConfig();
    }

    public static double getVersion() {
        return 1.0;
    }

    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}