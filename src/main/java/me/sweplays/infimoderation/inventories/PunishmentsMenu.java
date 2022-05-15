package me.sweplays.infimoderation.inventories;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.sweplays.infimoderation.InfiModeration;
import me.sweplays.infimoderation.conversations.KickReasonConversation;
import me.sweplays.infimoderation.conversations.MuteReasonConversation;
import me.sweplays.infimoderation.conversations.SetMoneyConversation;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;


import java.util.Arrays;

import static me.sweplays.infimoderation.utils.ItemUtils.createItem;
import static me.sweplays.infimoderation.utils.ItemUtils.createPlayerHead;

public class PunishmentsMenu {

    private static final InfiModeration plugin = InfiModeration.getPlugin();

    private static ChestGui inventory;
    private static final StaticPane pane = new StaticPane(0, 0, 9, 5);

    public static void createInventory(OfflinePlayer target) {
        inventory = new ChestGui(5, "InfiModeration Punishments");
        initItems(target);
    }

    //Bukkit.getBanList(BanList.Type.NAME).addBan(String.valueOf(target.getUniqueId()), BanReasonConversation.getReason(), new Date(), event.getWhoClicked().getName());

    public static void initItems(OfflinePlayer target) {

        final boolean getAdvancedBansBool = plugin.getConfig().getBoolean("enable-advancedbans");

        GuiItem playerHead = new GuiItem(createPlayerHead(Material.PLAYER_HEAD, 1, "§b" + target.getName(), target, Arrays.asList(" ", "§7You are moderating this player.", " ")), event -> {
            event.setCancelled(true);
        });

        GuiItem banCategoryItem;
        GuiItem unbanItem;
        GuiItem kickItem;
        GuiItem muteItem;
        GuiItem unMuteItem;
        GuiItem warnItem;
        GuiItem unWarnItem;
        if (getAdvancedBansBool) {
            banCategoryItem = new GuiItem(createItem(Material.ANVIL, 1, "§cBan"), event -> {
                Player player = (Player) event.getWhoClicked();
                SetBanLengthMenu.createInventory(target);
                SetBanLengthMenu.getInventory().show(player);
                event.setCancelled(true);
            });

            unbanItem = new GuiItem(createItem(Material.ANVIL, 1, "§aUnban"), event -> {
                Player player = (Player) event.getWhoClicked();
                player.getPlayer().performCommand("unban " + target.getName());
                event.setCancelled(true);
            });

            kickItem = new GuiItem(createItem(Material.LEATHER_BOOTS, 1, "§aKick"), event -> {
                Player player = (Player) event.getWhoClicked();
                ConversationFactory conversationFactory = new ConversationFactory(plugin);
                Conversation conversation = conversationFactory.withFirstPrompt(new KickReasonConversation(target)).withLocalEcho(false).buildConversation((Conversable) event.getWhoClicked());
                conversation.begin();
                event.setCancelled(true);
            });

            muteItem = new GuiItem(createItem(Material.LEAD, 1, "§aMute"), event -> {
                Player player = (Player) event.getWhoClicked();
                SetMuteLengthInventory.createInventory(target);
                SetMuteLengthInventory.getInventory().show(player);
                event.setCancelled(true);
            });

            unMuteItem = new GuiItem(createItem(Material.LEAD, 1, "§aUnmute"), event -> {
                Player player = (Player) event.getWhoClicked();
                player.getPlayer().performCommand("unmute " + target.getName());
                event.setCancelled(true);
            });

            warnItem = new GuiItem(createItem(Material.BARRIER, 1, "§cWarn"), event -> {
                Player player = (Player) event.getWhoClicked();
                SetWarnLengthInventory.createInventory(target);
                SetWarnLengthInventory.getInventory().show(player);
                event.setCancelled(true);
            });

            unWarnItem = new GuiItem(createItem(Material.BARRIER, 1, "§cClear Warns"), event -> {
                Player player = (Player) event.getWhoClicked();
                player.getPlayer().performCommand("unwarn clear " + target.getName());
                event.setCancelled(true);
            });
        } else {
            banCategoryItem = new GuiItem(createItem(Material.ANVIL, 1, "§cBan §7(Standard)"), event -> {
                SetBanLengthMenu.createInventory(target);
                SetBanLengthMenu.getInventory().show(event.getWhoClicked());
                event.setCancelled(true);
            });

            unbanItem = new GuiItem(createItem(Material.ANVIL, 1, "§aUnban §7(Standard)"), event -> {
                Player player = (Player) event.getWhoClicked();
                plugin.getServer().getBannedPlayers().remove(target);
                event.setCancelled(true);
            });

            kickItem = new GuiItem(createItem(Material.LEATHER_BOOTS, 1, "§aKick §7(Standard)"), event -> {
                Player player = (Player) event.getWhoClicked();
                ConversationFactory conversationFactory = new ConversationFactory(plugin);
                Conversation conversation = conversationFactory.withFirstPrompt(new KickReasonConversation(target)).withLocalEcho(false).buildConversation((Conversable) event.getWhoClicked());
                conversation.begin();
                event.setCancelled(true);
            });

            muteItem = new GuiItem(createItem(Material.LEAD, 1, "§aMute §7(Disabled)", Arrays.asList(" ", "§7Coming Soon", " ")), event -> {
                event.setCancelled(true);
            });

            unMuteItem = new GuiItem(createItem(Material.LEAD, 1, "§aUnmute §7(Disabled)", Arrays.asList(" ", "§7Coming Soon", " ")), event -> {
                event.setCancelled(true);
            });

            warnItem = new GuiItem(createItem(Material.LEAD, 1, "§aUnmute §7(Disabled)", Arrays.asList(" ", "§7Coming Soon", " ")), event -> {
                event.setCancelled(true);
            });

            unWarnItem = new GuiItem(createItem(Material.BARRIER, 1, "§aClear Warns §7(Disabled)", Arrays.asList(" ", "§7Coming Soon", " ")), event -> {
                event.setCancelled(true);
            });
        }

        pane.addItem(playerHead, 4, 1);
        pane.addItem(banCategoryItem, 3, 3);
        pane.addItem(unbanItem, 5, 3);
        pane.addItem(kickItem, 1, 3);
        pane.addItem(muteItem, 7, 3);
        pane.addItem(unMuteItem, 2, 4);
        pane.addItem(warnItem, 4, 4);
        pane.addItem(unWarnItem, 6, 4);

        inventory.addPane(pane);
    }

    public static ChestGui getInventory() {
        return inventory;
    }
}
