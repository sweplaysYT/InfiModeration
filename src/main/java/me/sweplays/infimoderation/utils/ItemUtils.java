package me.sweplays.infimoderation.utils;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.util.List;

public class ItemUtils {

    public static ItemStack createItem(final Material material, final int stackSize, final String name, final List<String> lore, final ItemFlag... itemFlags) {
        final ItemStack item = new ItemStack(material, stackSize);
        final ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(itemFlags);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createItem(final Material material, final int stackSize, final String name, final ItemFlag... itemFlags) {
        final ItemStack item = new ItemStack(material, stackSize);
        final ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.addItemFlags(itemFlags);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createPlayerHead(final Material material, final int stackSize, final String name, Player owner, final List<String> lore) {
        final ItemStack item = new ItemStack(material, stackSize);
        final SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemMeta.setOwningPlayer(owner);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createPlayerHead(final Material material, final int stackSize, final String name, OfflinePlayer owner, final List<String> lore) {
        final ItemStack item = new ItemStack(material, stackSize);
        final SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemMeta.setOwningPlayer(owner);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createPlayerHead(final Material material, final int stackSize, final String name, Player owner) {
        final ItemStack item = new ItemStack(material, stackSize);
        final SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setOwningPlayer(owner);
        item.setItemMeta(itemMeta);
        return item;
    }
}
