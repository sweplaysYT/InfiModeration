package me.sweplays.infimoderation.inventories;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.component.ToggleButton;
import me.sweplays.infimoderation.InfiModeration;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

import static me.sweplays.infimoderation.utils.ItemUtils.createItem;

public class SettingsMenu {

    private static final InfiModeration plugin = InfiModeration.getPlugin();

    private static ChestGui inventory;
    private static final StaticPane pane = new StaticPane(0, 0, 9, 3);

    public static void createInventory() {
        inventory = new ChestGui(3, "InfiModeration Settings");
        initItems();
    }

    public static void initItems() {

        final boolean[] getVaultBool = { plugin.getConfig().getBoolean("enable-vault") };
        final boolean[] getInvSeeBool = { plugin.getConfig().getBoolean("enable-invsee") };
        final boolean[] getAdvancedBansBool = { plugin.getConfig().getBoolean("enable-advancedbans") };

        GuiItem toggleVaultItem = new GuiItem(createItem(Material.CHEST, 1, "§dToggle Vault Support", List.of("§7Toggled: " + (getVaultBool[0] ? "§atrue" : "§cfalse"))), event -> {
            plugin.getConfig().set("enable-vault", !getVaultBool[0]);
            plugin.saveConfig();
            event.setCancelled(true);
            createInventory();
            inventory.show(event.getWhoClicked());
        });

        GuiItem toggleInvSeePlusPlusItem = new GuiItem(createItem(Material.CHEST, 1, "§dToggle InvSee++ Support", List.of("§7Toggled: " + (getInvSeeBool[0] ? "§atrue" : "§cfalse"))), event -> {
            plugin.getConfig().set("enable-invsee", !getInvSeeBool[0]);
            plugin.saveConfig();
            event.setCancelled(true);
            createInventory();
            inventory.show(event.getWhoClicked());
        });

        GuiItem toggleInvAdvancedbansItem = new GuiItem(createItem(Material.ANVIL, 1, "§dToggle AdvancedBans Support", List.of("§7Toggled: " + (getAdvancedBansBool[0] ? "§atrue" : "§cfalse"))), event -> {
            plugin.getConfig().set("enable-advancedbans", !getAdvancedBansBool[0]);
            plugin.saveConfig();
            event.setCancelled(true);
            createInventory();
            inventory.show(event.getWhoClicked());
        });

        pane.addItem(toggleVaultItem, 1, 1);
        pane.addItem(toggleInvSeePlusPlusItem , 4, 1);
        pane.addItem(toggleInvAdvancedbansItem , 7, 1);

        inventory.addPane(pane);
    }

    public static ChestGui getInventory() {
        return inventory;
    }
}
