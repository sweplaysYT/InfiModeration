package me.sweplays.infimoderation.inventories;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.sweplays.infimoderation.InfiModeration;
import me.sweplays.infimoderation.conversations.BanReasonConversation;
import me.sweplays.infimoderation.conversations.GiveMoneyConversation;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;

import java.util.*;

import static me.sweplays.infimoderation.utils.ItemUtils.createItem;

public class SetBanLengthMenu {

    private static final InfiModeration plugin = InfiModeration.getPlugin();

    private static boolean permanent;
    private static String lengthString;

    private static ChestGui inventory;
    private static final StaticPane pane = new StaticPane(0, 0, 9, 3);

    public static void createInventory(OfflinePlayer target) {
        inventory = new ChestGui(3, "InfiModeration Punishments");
        initItems(target);
    }

    private static final Date banLength = new Date();

    public static void initItems(OfflinePlayer target) {

        final boolean getAdvancedBansBool = plugin.getConfig().getBoolean("enable-advancedbans");

        GuiItem lengthOne = new GuiItem(createItem(Material.PAPER, 1, "§c7d"), event -> {
            getBanLength().setTime(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
            event.getWhoClicked().closeInventory();
            ConversationFactory conversationFactory = new ConversationFactory(plugin);
            Conversation conversation = conversationFactory.withFirstPrompt(new BanReasonConversation(target)).withLocalEcho(false).buildConversation((Conversable) event.getWhoClicked());
            conversation.begin();
            permanent = false;
            lengthString = "7d";
            event.setCancelled(true);
        });

        GuiItem lengthTwo = new GuiItem(createItem(Material.PAPER, 1, "§c15d"), event -> {
            getBanLength().setTime(System.currentTimeMillis() + 15 * 24 * 60 * 60 * 1000);
            event.getWhoClicked().closeInventory();
            ConversationFactory conversationFactory = new ConversationFactory(plugin);
            Conversation conversation = conversationFactory.withFirstPrompt(new BanReasonConversation(target)).withLocalEcho(false).buildConversation((Conversable) event.getWhoClicked());
            conversation.begin();
            permanent = false;
            lengthString = "15d";
            event.setCancelled(true);
        });

        GuiItem lengthThree = new GuiItem(createItem(Material.PAPER, 1, "§c30d"), event -> {
            getBanLength().setTime(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000);
            event.getWhoClicked().closeInventory();
            ConversationFactory conversationFactory = new ConversationFactory(plugin);
            Conversation conversation = conversationFactory.withFirstPrompt(new BanReasonConversation(target)).withLocalEcho(false).buildConversation((Conversable) event.getWhoClicked());
            conversation.begin();
            permanent = false;
            lengthString = "30d";
            event.setCancelled(true);
        });

        GuiItem lengthFour = new GuiItem(createItem(Material.PAPER, 1, "§c2mo"), event -> {
            getBanLength().setTime(System.currentTimeMillis() + 60L * 24 * 60 * 60 * 1000);
            event.getWhoClicked().closeInventory();
            ConversationFactory conversationFactory = new ConversationFactory(plugin);
            Conversation conversation = conversationFactory.withFirstPrompt(new BanReasonConversation(target)).withLocalEcho(false).buildConversation((Conversable) event.getWhoClicked());
            conversation.begin();
            permanent = false;
            lengthString = "2mo";
            event.setCancelled(true);
        });


        GuiItem lengthFive = new GuiItem(createItem(Material.PAPER, 1, "§c4mo"), event -> {
            getBanLength().setTime(System.currentTimeMillis() + 120L * 24 * 60 * 60 * 1000);
            event.getWhoClicked().closeInventory();
            ConversationFactory conversationFactory = new ConversationFactory(plugin);
            Conversation conversation = conversationFactory.withFirstPrompt(new BanReasonConversation(target)).withLocalEcho(false).buildConversation((Conversable) event.getWhoClicked());
            conversation.begin();
            permanent = false;
            lengthString = "4mo";
            event.setCancelled(true);
        });

        GuiItem lengthSix = new GuiItem(createItem(Material.PAPER, 1, "§c1y"), event -> {
            getBanLength().setTime(System.currentTimeMillis() + 360L * 24 * 60 * 60 * 1000);
            event.getWhoClicked().closeInventory();
            ConversationFactory conversationFactory = new ConversationFactory(plugin);
            Conversation conversation = conversationFactory.withFirstPrompt(new BanReasonConversation(target)).withLocalEcho(false).buildConversation((Conversable) event.getWhoClicked());
            conversation.begin();
            permanent = false;
            lengthString = "1y";
            event.setCancelled(true);
        });

        GuiItem lengthSeven = new GuiItem(createItem(Material.PAPER, 1, "§cPermanent"), event -> {
            event.getWhoClicked().closeInventory();
            ConversationFactory conversationFactory = new ConversationFactory(plugin);
            Conversation conversation = conversationFactory.withFirstPrompt(new BanReasonConversation(target)).withLocalEcho(false).buildConversation((Conversable) event.getWhoClicked());
            conversation.begin();
            permanent = true;
            event.setCancelled(true);
        });

        pane.addItem(lengthOne, 1, 1);
        pane.addItem(lengthTwo, 2, 1);
        pane.addItem(lengthThree, 3, 1);
        pane.addItem(lengthFour, 4, 1);
        pane.addItem(lengthFive, 5, 1);
        pane.addItem(lengthSix, 6, 1);
        pane.addItem(lengthSeven, 7, 1);

        inventory.addPane(pane);
    }

    public static ChestGui getInventory() {
        return inventory;
    }

    public static Date getBanLength() {
        return banLength;
    }

    public static Boolean getPermanentBoolean() {
        return permanent;
    }

    public static String getLengthString() {
        return lengthString;
    }
}
