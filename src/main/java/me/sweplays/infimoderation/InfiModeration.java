package me.sweplays.infimoderation;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class InfiModeration extends JavaPlugin {

    //private static Logger logger = Bukkit.getLogger();
    private static final String version = "1.0.0";
    private static InfiModeration plugin;

    @Override
    public void onEnable() {
        logger().info(String.format("""
                
                --------------------------------------------------
                                    ______  ___                  
                                   /  _/  |/  /                    
                                   / // /|_/ /                     
                                 _/ // /  / /
                                /___/_/  /_/
                                 Version: %s
                              Author: SweplaysYT
                           Plugin has been enabled!
                --------------------------------------------------
                """, getPluginVersion()));

        plugin = this;
    }

    @Override
    public void onDisable() {
        logger().info(String.format("""
                
                --------------------------------------------------
                                    ______  ___                
                                   /  _/  |/  /                    
                                   / // /|_/ /                     
                                 _/ // /  / /
                                /___/_/  /_/
                                 Version: %s
                              Author: SweplaysYT
                           Plugin has been disabled!
                --------------------------------------------------
                """, getPluginVersion()));
    }

    public static Logger logger() {
        return Bukkit.getLogger();
    }

    public static InfiModeration getPlugin() {
        return plugin;
    }

    public static String getPluginVersion() {
        return version;
    }
}
