package de.bypixels.thehunter.chestitems;

import de.bypixels.thehunter.util.Settings;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class Food {
	
	public static ItemStack Food() {
		ItemStack item = new ItemStack(Material.MUSHROOM_STEW);
			ItemMeta im = item.getItemMeta();
			im.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix") + "§fFood"));
			item.setAmount(1);
			item.setItemMeta(im);
	
			return item;
		
	}
	

}
