package me.sweplays.infimoderation.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.sweplays.infimoderation.InfiModeration;
import me.sweplays.infimoderation.inventories.MainPlayerMenu;
import me.sweplays.infimoderation.inventories.SettingsMenu;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@CommandAlias("im|imoderation|infimod|imod|infimoderation")
public class MainCommand extends BaseCommand {

    private final InfiModeration plugin = InfiModeration.getPlugin();

    @Default
    @Syntax("/<command> moderate <player>")
    @CommandPermission("infimoderation.admin.info")
    @Description("Displays what cmmands that are available in the plugin")
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("""
                                        
                    §8§m————————§dInfi§fModeration§8§m————————
                                        
                    §f/im moderate <player>
                                    
                    §8§m————————————————————————
                    """);
        }
    }

    @Syntax("/<command> moderate <player>")
    @CommandPermission("infimoderation.admin.moderate")
    @Subcommand("moderate|mod")
    @CommandCompletion("@players")
    public void onModerate(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("§cUsage: /<command> moderate <player>");
        } else if (args.length == 1) {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            MainPlayerMenu.createInventory(player, target);
            MainPlayerMenu.getInventory().show(player);
        } else if (args.length > 2) {
            player.sendMessage("Too many arguments");
        }
    }

    @Syntax("/<command> settings")
    @CommandPermission("infimoderation.admin.settings")
    @Subcommand("settings")
    public void onSettings(Player player, String[] args) {
        if (args.length == 0) {
            SettingsMenu.createInventory();
            SettingsMenu.getInventory().show(player);
        } else if (args.length > 1) {
            player.sendMessage("Too many arguments");
        }
    }

    @Syntax("/<command> reload")
    @CommandPermission("infimoderation.admin.reload")
    @Subcommand("reload")
    public void onReload(Player player, String[] args) {
        if (args.length == 0) {
            plugin.reloadConfig();
            player.sendMessage("§aConfig reloaded!");
        } else if (args.length > 1) {
            player.sendMessage("Too many arguments");
        }
    }
}
