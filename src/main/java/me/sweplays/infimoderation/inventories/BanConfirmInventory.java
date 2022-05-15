package me.sweplays.infimoderation.inventories;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.sweplays.infimoderation.InfiModeration;
import me.sweplays.infimoderation.conversations.BanReasonConversation;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Date;

import static me.sweplays.infimoderation.utils.ItemUtils.createItem;

public class BanConfirmInventory {

    private static final InfiModeration plugin = InfiModeration.getPlugin();

    private static ChestGui inventory;
    private static final StaticPane pane = new StaticPane(0, 0, 9, 3);

    public static void createInventory(OfflinePlayer target) {
        inventory = new ChestGui(3, "InfiModeration Punishments");
        initItems(target);
    }

    private static final Date banLength = new Date();

    public static void initItems(OfflinePlayer target) {

        final boolean getAdvancedBansBool = plugin.getConfig().getBoolean("enable-advancedbans");

        GuiItem confirmBan = new GuiItem(createItem(Material.GREEN_STAINED_GLASS, 1, "§aConfirm Ban", Arrays.asList("§aReason: " + BanReasonConversation.getReason(),
                "§aBanned Until: " + SetBanLengthMenu.getBanLength().toString())), event -> {
            if (plugin.getConfig().getBoolean("enable-advancedbans")) {
                event.setCancelled(true);
                Player player = (Player) event.getWhoClicked();
                if (SetBanLengthMenu.getPermanentBoolean()) {
                    player.getPlayer().performCommand("ban " + target.getName() + " " + BanReasonConversation.getReason());
                } else {
                    player.getPlayer().performCommand("tempban " + target.getName() + " " + SetBanLengthMenu.getLengthString() + " " + BanReasonConversation.getReason());
                }
            } else {
                Bukkit.getBanList(BanList.Type.NAME).addBan(String.valueOf(target.getUniqueId()), BanReasonConversation.getReason(), SetBanLengthMenu.getBanLength(), event.getWhoClicked().getName());
                if (target.isOnline()) target.getPlayer().kickPlayer("You are been banned on this server until " + SetBanLengthMenu.getBanLength() + ". Reason: " + BanReasonConversation.getReason());
                event.setCancelled(true);
            }
        });

        pane.addItem(confirmBan, 4, 1 );

        inventory.addPane(pane);
    }

    public static ChestGui getInventory() {
        return inventory;
    }
}
