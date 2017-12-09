package ChestItems;

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
import org.bukkit.potion.PotionEffectType;

import HuntingMain.Main;
import Util.Settings;

// Code by: PixelsDE /
// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /

public class Energie implements Listener {

	private static Main plugin;

	public Energie(Main plugin) {
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
				Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin.getPlugin(), new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						p.getPlayer().addPotionEffect(PotionEffectType.SPEED.createEffect(30, 30));
						p.getPlayer().addPotionEffect(PotionEffectType.JUMP.createEffect(30, 30));
						p.getPlayer().addPotionEffect(PotionEffectType.REGENERATION.createEffect(30, 30));
						
						removeItem(p.getInventory(), EnergieDrink().getType(), 1);
						e.setCancelled(true);
						p.updateInventory();
					}
				}, 20*30);

			}
		}
	}

}
