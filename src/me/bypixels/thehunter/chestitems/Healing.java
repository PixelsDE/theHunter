package me.bypixels.thehunter.chestitems;

import me.bypixels.thehunter.util.Settings;
import me.bypixels.thehunter.util.special.Variables;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;



// Code by: PixelsDE /
// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /

public class Healing implements Listener {

	public static ItemStack MediPack() {
		ItemStack item = new ItemStack(Material.GOLD_RECORD);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(
				ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix") + "§fMedi-Pack"));
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
	public static void onUse(PlayerInteractEvent e) {
		Player p = (Player) e.getPlayer();

		if (Variables.playing.contains(p)) {
			if (p.getItemInHand().equals(Healing.MediPack())) {
				e.setCancelled(true);
				removeItem(p.getInventory(), MediPack().getType(), 1);
				p.updateInventory();
				try {
					p.setHealth(p.getHealth() + Settings.cfg.getInt("Healing_Heal"));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				}


			}

		}
	}

}
