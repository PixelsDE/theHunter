package ChestItems;

// Code by: PixelsDE /
// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /


import HuntingMain.Main;
import Util.Settings;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class Knife implements Listener{

    private Main plugin;
    public Knife(Main plugin) {
        this.plugin = plugin;
    }

    public static ItemStack theKnife() {
        ItemStack item = new ItemStack(Material.WOOD_SWORD);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix") + "§fKnife"));
        item.setItemMeta(im);
        item.setAmount(1);

        return item;

    }


    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if (e.getDamager()instanceof Player){
        	if (e.getEntity() instanceof Player) {
				
			
            Player p = (Player) e.getDamager();
            Player t = (Player) e.getEntity();
            ItemStack i = p.getItemInHand();
            if (p.getItemInHand().equals(Knife.theKnife())){
                e.setDamage(1.5);
                i.setDurability((short) (i.getDurability() + 1));

            }else {
            	e.setCancelled(false);
            }
        }
    }

    }


}
