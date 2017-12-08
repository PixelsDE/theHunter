package chestitems;

// Code by: PixelsDE /
// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /


import util.Settings;
import util.special.Var;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Armor implements Listener {


    public static ItemStack i1(){
        ItemStack i1 = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta im1 = i1.getItemMeta();
        im1.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fBoots");
        i1.setItemMeta(im1);
        return i1;
    }

    public static ItemStack i2(){

        ItemStack i2 = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta im2 = i2.getItemMeta();
        im2.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fLeggs");
        i2.setItemMeta(im2);
        return i2;
    }

    public static ItemStack i3(){

        ItemStack i3 = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta im3 = i3.getItemMeta();
        im3.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fChestplate");
        i3.setItemMeta(im3);
        return i3;
    }

    public static ItemStack i4(){

        ItemStack i4 = new ItemStack(Material.LEATHER_HELMET);
        ItemMeta im4 = i4.getItemMeta();
        im4.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fHelmet");
        i4.setItemMeta(im4);
        return i4;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (Var.playing.contains(p)) {


            if (e.getCurrentItem().equals(i1().getItemMeta()) || e.getCurrentItem().equals(i2().getItemMeta()) || e.getCurrentItem().equals(i3().getItemMeta()) || e.getCurrentItem().equals(i4().getItemMeta()
            )){
                e.setCancelled(true);
                p.updateInventory();
            }

        }

    }


}
