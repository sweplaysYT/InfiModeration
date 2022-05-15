package me.sweplays.infimoderation.inventories;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.Arrays;

import static me.sweplays.infimoderation.utils.ItemUtils.createItem;
import static me.sweplays.infimoderation.utils.ItemUtils.createPlayerHead;

public class MainPlayerMenu {

    private static ChestGui inventory;
    private static StaticPane pane = new StaticPane(0, 0, 9, 5);

    public static void createInventory(Player player, OfflinePlayer target) {
        Player targetPlayer = target.getPlayer();
        inventory = new ChestGui(5, target.getName() + "'s Moderation");
        initItems(player, target);
    }

    private static void initItems(Player player, OfflinePlayer target) {

        GuiItem playerHead = new GuiItem(createPlayerHead(Material.PLAYER_HEAD, 1, "§b" + target.getName(), target, Arrays.asList(" ", "§7You are moderating this player.", " ")), event -> {
            event.setCancelled(true);
        });

        GuiItem punishmentsItem = new GuiItem(createItem(Material.DIAMOND_SWORD, 1, "§cPunishments", ItemFlag.HIDE_ATTRIBUTES), event -> {
            PunishmentsMenu.createInventory(target);
            PunishmentsMenu.getInventory().show((HumanEntity) player);
            event.setCancelled(true);
        });

        GuiItem otherItem = new GuiItem(createItem(Material.PAPER, 1, "§dOther", ItemFlag.HIDE_ATTRIBUTES), event -> {
            OtherMenu.createInventory(target);
            OtherMenu.getInventory().show((HumanEntity) player);
            event.setCancelled(true);
        });


        pane.addItem(playerHead, 4, 1);
        pane.addItem(punishmentsItem, 3, 3);
        pane.addItem(otherItem, 5, 3);

        inventory.addPane(pane);
    }

    public static ChestGui getInventory() {
        return inventory;
    }
}
