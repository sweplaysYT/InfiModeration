package me.sweplays.infimoderation.inventories;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.sweplays.infimoderation.InfiModeration;
import me.sweplays.infimoderation.conversations.SetMoneyConversation;
import me.sweplays.infimoderation.conversations.TakeMoneyConversation;
import me.sweplays.infimoderation.conversations.GiveMoneyConversation;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;

import static me.sweplays.infimoderation.utils.ItemUtils.createItem;

public class OtherMenu{

    private static final InfiModeration plugin = InfiModeration.getPlugin();

    private static ChestGui inventory;
    private static final StaticPane pane = new StaticPane(0, 0, 9, 5);

    public static void createInventory(OfflinePlayer target) {
        inventory = new ChestGui(5, "InfiModeration Other");
        initItems(target);
    }

    public static void initItems(OfflinePlayer target) {

        boolean getVaultBool = plugin.getConfig().getBoolean("enable-vault");
        boolean getInvSeeBool = plugin.getConfig().getBoolean("enable-invsee");

        GuiItem giveMoneyItem;
        if (getVaultBool) {
            giveMoneyItem = new GuiItem(createItem(Material.PAPER, 1, "§aGive Money"), event -> {
                ConversationFactory conversationFactory = new ConversationFactory(plugin);
                Conversation conversation = conversationFactory.withFirstPrompt(new GiveMoneyConversation(target)).withLocalEcho(false).buildConversation((Conversable) event.getWhoClicked());
                conversation.begin();
                event.setCancelled(true);
            });
        } else {
            giveMoneyItem = new GuiItem(createItem(Material.BARRIER, 1, "§aGive Money §7(§cDisabled§7)"), event -> {
                event.setCancelled(true);
            });
        }

        GuiItem takeMoneyItem;
        if (getVaultBool) {
            takeMoneyItem = new GuiItem(createItem(Material.PAPER, 1, "§aTake Money"), event -> {
                ConversationFactory conversationFactory = new ConversationFactory(plugin);
                Conversation conversation = conversationFactory.withFirstPrompt(new TakeMoneyConversation(target)).withLocalEcho(false).buildConversation((Conversable) event.getWhoClicked());
                conversation.begin();
                event.setCancelled(true);
            });
        } else {
            takeMoneyItem = new GuiItem(createItem(Material.BARRIER, 1, "§aTake Money §7(§cDisabled§7)"), event -> {
                event.setCancelled(true);
            });
        }

        GuiItem setMoneyItem;
        if (getVaultBool) {
            setMoneyItem = new GuiItem(createItem(Material.PAPER, 1, "§aSet Money"), event -> {
                ConversationFactory conversationFactory = new ConversationFactory(plugin);
                Conversation conversation = conversationFactory.withFirstPrompt(new SetMoneyConversation(target)).withLocalEcho(false).buildConversation((Conversable) event.getWhoClicked());
                conversation.begin();
                event.setCancelled(true);
            });
        } else {
            setMoneyItem = new GuiItem(createItem(Material.BARRIER, 1, "§aSet Money §7(§cDisabled§7)"), event -> {
                event.setCancelled(true);
            });
        }

        GuiItem resetMoneyItem;
        if (getVaultBool) {
            resetMoneyItem = new GuiItem(createItem(Material.PAPER, 1, "§aReset Money"), event -> {
                InfiModeration.getEconomy().withdrawPlayer(target, InfiModeration.getEconomy().getBalance(target));
                event.getWhoClicked().sendMessage("§aYou have reset §7" + target.getName() + "'s §abalance to 0$");
                event.setCancelled(true);
            });
        } else {
            resetMoneyItem = new GuiItem(createItem(Material.BARRIER, 1, "§aReset Money §7(§cDisabled§7)"), event -> {
                event.setCancelled(true);
            });
        }

        GuiItem invSeeItem;
        if (getInvSeeBool) {
            invSeeItem = new GuiItem(createItem(Material.CHEST, 1, "§aInv See"), event -> {
                Player player = (Player) event.getWhoClicked();
                player.performCommand("invsee " + target.getName());
                event.setCancelled(true);
            });
        } else {
            invSeeItem = new GuiItem(createItem(Material.BARRIER, 1, "§aInv See §7(§cDisabled§7)"), event -> {
                event.setCancelled(true);
            });
        }

        GuiItem enderChestSeeItem;
        if (getInvSeeBool) {
            enderChestSeeItem = new GuiItem(createItem(Material.ENDER_CHEST, 1, "§aEnder Chest See"), event -> {
                Player player = (Player) event.getWhoClicked();
                player.performCommand("enderchestsee " + target.getName());
                event.setCancelled(true);
            });
        } else {
            enderChestSeeItem = new GuiItem(createItem(Material.BARRIER, 1, "§aEnder Chest See §7(§cDisabled§7)"), event -> {
                event.setCancelled(true);
            });
        }


        pane.addItem(giveMoneyItem, 2, 1);
        pane.addItem(takeMoneyItem, 4, 1);
        pane.addItem(setMoneyItem, 6, 1);
        pane.addItem(resetMoneyItem, 3, 2);
        pane.addItem(invSeeItem, 5, 2);
        pane.addItem(enderChestSeeItem, 4, 3);

        inventory.addPane(pane);
    }

    public static ChestGui getInventory() {
        return inventory;
    }
}
