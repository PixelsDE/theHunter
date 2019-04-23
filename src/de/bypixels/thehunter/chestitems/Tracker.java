package de.bypixels.thehunter.chestitems;

import de.bypixels.thehunter.main.theHunterMain;
import de.bypixels.thehunter.util.special.Variables;
import de.bypixels.thehunter.util.Settings;
import org.bukkit.ChatColor;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import de.bypixels.thehunter.util.Messages;

// Code by: PixelsDE /
// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /

public class Tracker implements Listener {

	public static ItemStack Tracker() {
		ItemStack item = new ItemStack(Material.COMPASS);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix") + "§fTracker"));
		item.setAmount(1);
		item.setItemMeta(im);
		return item;
	}

    /*     */ private org.bukkit.craftbukkit.Main plugin;
    public Tracker (theHunterMain pluign)
        /*     */ {
        /*  31 */
        this.plugin = plugin;
        /*     */
    }


    @SuppressWarnings("deprecation")
	@EventHandler
	public void onTrack(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {


			if (p.getItemInHand().hasItemMeta()) {
				if (p.getItemInHand().getType().equals(Material.COMPASS)) {
				    e.setCancelled(true);
					for (Entity ent : p.getNearbyEntities(1000D, 200D, 1000D)) {
						if (ent instanceof Player) {
						    if (Variables.playing.contains(ent)){
							Player near = (Player) ent;
							p.setCompassTarget(near.getLocation());
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"))
									+ ChatColor.translateAlternateColorCodes('&',
									Messages.cfg.getString("Player_Tacker").replace("%player%", near.getName())
											.replace("%distance%",
													(int) p.getLocation().distance(near.getLocation()) + "")));
							p.updateInventory();
						} else {

                            }
						}else{

                        }
					}

					p.updateInventory();
				} else {

				}
			}
		}
	}

}
