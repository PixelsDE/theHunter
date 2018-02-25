package me.bypixels.thehunter.chestitems;

import me.bypixels.thehunter.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.bypixels.thehunter.main.Main;


// Code by: PixelsDE /
// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /

public class EnergieDrink implements Listener {

    private static Main plugin;

    public EnergieDrink(Main plugin) {
        this.plugin = plugin;
    }

    public static ItemStack EnergieDrink() {
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(
                ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix") + "§fEnergie-Drink"));
        item.setItemMeta(im);
        item.setAmount(1);
        return item;

    }

    public static void removeItem(PlayerInventory inv, Material type, int amount) {
        for (ItemStack is : inv.getContents()) {
            if (is != null && is.getItemMeta().equals(EnergieDrink().getItemMeta())) {
                int newamount = is.getAmount() - amount;
                if (newamount > 0) {
                    is.setAmount(newamount);
                    break;
                } else {
                    inv.remove(is);
                    amount = -newamount;
                    if (amount == 0)
                        break;
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public static void onDrink(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            if (p.getItemInHand().equals(EnergieDrink())) {
                e.setCancelled(true);
                removeItem(p.getInventory(), EnergieDrink().getType(), 1);
                p.updateInventory();
                p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30 ,1));
                p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30 ,1));
                p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30 ,1));

            }
        }
    }

}
