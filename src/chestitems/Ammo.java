package ChestItems;

// Code by: PixelsDE /

// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /

import Guns.AK;
import Util.Settings;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Ammo {

    public static ItemStack MGAmmo() {

        ArrayList<String> lore = new ArrayList<>();
        /* 71 */
        ItemStack item = new ItemStack(Material.MAGMA_CREAM);
        /* 72 */
        ItemMeta imeta = item.getItemMeta();
        /* 73 */
        imeta.setDisplayName(
                ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fAK47-Ammo");

        lore.add(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fThis is the Ammo for an AK47");
        imeta.setLore(lore);

        item.setAmount(5);
        item.setItemMeta(imeta);

        return item;

        /* 75 */

    }

    public static ItemStack MinigunAmmo() {
        /* 71 */
        ItemStack item = new ItemStack(Material.SNOW_BALL);
        /* 72 */
        ItemMeta imeta = item.getItemMeta();
        /* 73 */
        imeta.setDisplayName(
                ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fMinigun-Ammo");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fThis is the Ammo for a Minigun");
        imeta.setLore(lore);
        /* 74 */
        item.setAmount(5);
        item.setItemMeta(imeta);
        /* 75 */
        return item;
    }

    public static ItemStack PistolAmmo() {
        /* 71 */
        ItemStack item = new ItemStack(Material.FIREBALL);
        /* 72 */
        ItemMeta imeta = item.getItemMeta();
        /* 73 */        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fThis is the Ammo for a Pistol");
        imeta.setLore(lore);
        imeta.setDisplayName(
                ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fPistol-Ammo");
        item.setAmount(5);
        item.setItemMeta(imeta);
        /* 75 */
        return item;
    }

    public static ItemStack SniperAmmo() {
        /* 71 */
        ItemStack item = new ItemStack(Material.ENDER_PEARL);
        /* 72 */
        ItemMeta imeta = item.getItemMeta();
        /* 73 */        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fThis is the Ammo for a Sniper");
        imeta.setLore(lore);
        imeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fSniper-Ammo");
        item.setAmount(5);

        item.setItemMeta(imeta);
        /* 75 */
        return item;
    }
}
