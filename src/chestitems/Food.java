package chestitems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import util.Settings;

public class Food {
	
	public static ItemStack Food() {
		ItemStack item = new ItemStack(Material.MUSHROOM_SOUP); 
			ItemMeta im = item.getItemMeta();
			im.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix") + "§fFood"));
			item.setAmount(1);
			item.setItemMeta(im);
	
			return item;
		
	}
	

}
