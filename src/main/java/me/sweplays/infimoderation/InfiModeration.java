package me.sweplays.infimoderation;

import co.aikar.commands.BukkitCommandManager;
import me.sweplays.infimoderation.commands.MainCommand;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class InfiModeration extends JavaPlugin {

    private static Economy econ = null;
    private static final String version = "1.0.0";
    private static InfiModeration plugin;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();

        if (getConfig().getBoolean("enable-vault")) {
            if (!setupEconomy() ) {
                logger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
                getServer().getPluginManager().disablePlugin(this);
                return;
            }
        }

        if (getConfig().getBoolean("enable-invsee")) {
            if (getServer().getPluginManager().getPlugin("InvSeePlusPlus") == null) {
                logger().severe(String.format("[%s] - Disabled plugin since you do not have InvSee++ installed!", getDescription().getName()));
                getServer().getPluginManager().disablePlugin(this);
            }
        }

        if (getConfig().getBoolean("enable-advanced-ban")) {
            if (getServer().getPluginManager().getPlugin("AdvancedBan") == null) {
                logger().severe(String.format("[%s] - Disabled plugin since you do not have AdvancedBan installed!", getDescription().getName()));
                getServer().getPluginManager().disablePlugin(this);
            }
        }

        registerCommands();

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
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();

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

    private void registerCommands() {
        BukkitCommandManager manager = new BukkitCommandManager(this);

        manager.registerCommand(new MainCommand());
    }

    private void initConfig() {
        getConfig().addDefault("vault-enabled", false);
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

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
