package chestitems;

import org.bukkit.ChatColor;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import util.Settings;
import util.Messages;

// Code by: PixelsDE /
// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /

public class Tracker implements Listener {

	public static ItemStack Tracker() {
		ItemStack item = new ItemStack(Material.GOLD_RECORD);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix") + "§fTracker"));
		item.setAmount(1);
		item.setItemMeta(im);
		return item;
	}

	public static void removeItem(PlayerInventory inv, Material type, int amount) {
		for (ItemStack is : inv.getContents()) {
			if (is != null && is.getItemMeta().equals(Healing.MediPack().getItemMeta())) {
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

	@EventHandler
	public void onTrack(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			try {
				if (p.getItemInHand().equals(Tracker())) {
					for (Entity ent : p.getNearbyEntities(1000D, 200D, 1000D)) {
						if (ent instanceof Player) {
							Player near = (Player) ent;
							p.setCompassTarget(near.getLocation());
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"))
									+ ChatColor.translateAlternateColorCodes('&',
											Messages.cfg.getString("Player_Tacker").replace("%player%", near.getName())
													.replace("%distance%",
															(int) p.getLocation().distance(near.getLocation()) + "")));
						} else {

						}
					}

					removeItem(p.getInventory(), Tracker().getType(), 1);
					e.setCancelled(true);
					p.updateInventory();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block

			}

		}
	}

}