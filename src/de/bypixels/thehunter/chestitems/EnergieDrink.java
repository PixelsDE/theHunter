package de.bypixels.thehunter.chestitems;

import de.bypixels.thehunter.util.Settings;
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

import de.bypixels.thehunter.main.Main;
import org.bukkit.scheduler.BukkitRunnable;


// Code by: PixelsDE /
// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /

public class EnergieDrink extends BukkitRunnable implements Listener {

    private static Main plugin;
Player p;
    public EnergieDrink(Main plugin, Player p) {
        this.plugin = plugin;

        this.p = p;
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



    @Override
    public void run() {
        p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30*20 ,1));
        p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30*20 ,1));
        p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 30*20 ,1));
    }

    private void removeItem(PlayerInventory inv, Material type, int amount) {
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
    public void onDrink(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            if (p.getItemInHand().equals(EnergieDrink())) {
                e.setCancelled(true);
                removeItem(p.getInventory(), EnergieDrink().getType(), 1);
                p.updateInventory();
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new EnergieDrink(Main.getPlugin(), p));

            }
        }
    }

}
