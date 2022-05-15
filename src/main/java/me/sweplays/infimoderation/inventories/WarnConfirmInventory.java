package me.sweplays.infimoderation.inventories;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.sweplays.infimoderation.InfiModeration;
import me.sweplays.infimoderation.conversations.WarnReasonConversation;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Date;

import static me.sweplays.infimoderation.utils.ItemUtils.createItem;

public class WarnConfirmInventory {

    private static final InfiModeration plugin = InfiModeration.getPlugin();

    private static ChestGui inventory;
    private static final StaticPane pane = new StaticPane(0, 0, 9, 3);

    public static void createInventory(OfflinePlayer target) {
        inventory = new ChestGui(3, "InfiModeration Punishments");
        initItems(target);
    }

    public static void initItems(OfflinePlayer target) {

        final boolean getAdvancedBansBool = plugin.getConfig().getBoolean("enable-advancedbans");

        GuiItem confirmWarn = new GuiItem(createItem(Material.GREEN_STAINED_GLASS, 1, "§aConfirm Warn", Arrays.asList("§aReason: " + WarnReasonConversation.getReason(),
                "§aWarned Until: " + SetWarnLengthInventory.getWarnLength().toString())), event -> {
            if (plugin.getConfig().getBoolean("enable-advancedbans")) {
                event.setCancelled(true);
                Player player = (Player) event.getWhoClicked();
                if (SetWarnLengthInventory.getPermanentBoolean()) {
                    player.getPlayer().performCommand("warn " + target.getName() + " " + WarnReasonConversation.getReason());
                } else {
                    player.getPlayer().performCommand("tempwarn " + target.getName() + " " + SetWarnLengthInventory.getLengthString() + " " + WarnReasonConversation.getReason());
                }
            }
        });

        pane.addItem(confirmWarn, 4, 1 );

        inventory.addPane(pane);
    }

    public static ChestGui getInventory() {
        return inventory;
    }
}
