package me.sweplays.infimoderation.inventories;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.sweplays.infimoderation.InfiModeration;
import me.sweplays.infimoderation.conversations.BanReasonConversation;
import me.sweplays.infimoderation.conversations.MuteReasonConversation;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Date;

import static me.sweplays.infimoderation.utils.ItemUtils.createItem;

public class MuteConfirmInventory {

    private static final InfiModeration plugin = InfiModeration.getPlugin();

    private static ChestGui inventory;
    private static final StaticPane pane = new StaticPane(0, 0, 9, 3);

    public static void createInventory(OfflinePlayer target) {
        inventory = new ChestGui(3, "InfiModeration Punishments");
        initItems(target);
    }

    public static void initItems(OfflinePlayer target) {

        GuiItem confirmMute = new GuiItem(createItem(Material.GREEN_STAINED_GLASS, 1, "§aConfirm Mute", Arrays.asList("§aReason: " + MuteReasonConversation.getReason(),
                "§aMuted Until: " + SetMuteLengthInventory.getMuteLength().toString())), event -> {
            if (plugin.getConfig().getBoolean("enable-advancedbans")) {
                event.setCancelled(true);
                Player player = (Player) event.getWhoClicked();
                if (SetMuteLengthInventory.getPermanentBoolean()) {
                    player.getPlayer().performCommand("mute " + target.getName() + " " + MuteReasonConversation.getReason());
                } else {
                    player.getPlayer().performCommand("tempmute " + target.getName() + " " + SetMuteLengthInventory.getLengthString() + " " + MuteReasonConversation.getReason());
                }
            }
        });

        pane.addItem(confirmMute, 4, 1 );

        inventory.addPane(pane);
    }

    public static ChestGui getInventory() {
        return inventory;
    }
}
