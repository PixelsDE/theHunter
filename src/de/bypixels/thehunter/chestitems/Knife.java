package de.bypixels.thehunter.chestitems;

// Code by: PixelsDE /
// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /


import de.bypixels.thehunter.main.theHunterMain;
import de.bypixels.thehunter.enums.Weapons;

import de.bypixels.thehunter.util.Settings;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Knife implements Listener {

    private theHunterMain plugin;

    public Knife(theHunterMain plugin) {
        this.plugin = plugin;
    }

    public static ItemStack Knife() {
        ArrayList<String> lore = new ArrayList<>();
        ItemStack item = new ItemStack(Weapons.KNIFE.getammomaterial());
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', Weapons.KNIFE.getName()));
        lore.add(ChatColor.translateAlternateColorCodes('&', theHunterMain.prefix + "§fThe classic Game-Knife"));
        im.setLore(lore);

        item.setItemMeta(im);
        item.setAmount(1);

        return item;

    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getDamager();
                Player entity = (Player) e.getEntity();

                if (p.getItemInHand().equals(Knife.Knife())) {
                    e.setDamage(Settings.cfg.getInt("Damage_Knife") * 2);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    entity.playSound(e.getEntity().getLocation(), Sound.ENTITY_PLAYER_HURT, 1, 1);

                }
            }
        }

    }


}
