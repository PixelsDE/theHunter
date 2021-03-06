package de.bypixels.thehunter.chestitems;

import de.bypixels.thehunter.main.theHunterMain;
import de.bypixels.thehunter.util.special.Variables;
import de.bypixels.thehunter.util.Messages;
import de.bypixels.thehunter.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class Swapper implements Listener {


    private theHunterMain plugin;

    public Swapper(theHunterMain plugin) {
        this.plugin = plugin;
    }

    public static ItemStack SwapperItem() {
        ItemStack item = new ItemStack(Material.TNT);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix") + "§FSwapper"));
        item.setAmount(1);
        item.setItemMeta(im);
        return item;
    }

    private void removeItem(PlayerInventory inv, Material type, int amount) {
        for (ItemStack is : inv.getContents()) {
            if (is != null && is.getItemMeta().equals(Swapper.SwapperItem().getItemMeta())) {
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
    public void onUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (Variables.playing.contains(p)) {


                int PlayerNr = new Random().nextInt(Bukkit.getOnlinePlayers().size());
                Player p1 = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[PlayerNr];

                if (p.getItemInHand().hasItemMeta()) {
                    if (p.getItemInHand().getType().equals(Material.TNT)) {
                        e.setCancelled(true);
                        if (p1.getPlayer() != p.getPlayer()) {
                            if (Variables.playing.contains(p1)) {
                                Location loc = p.getLocation();
                                Location loc1 = p1.getLocation();
                                p.teleport(loc1);
                                p1.teleport(loc);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', Messages.cfg.getString("Swapper_Use").replace("%player%", p1.getName())));
                                p1.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', Messages.cfg.getString("Swapper_Use").replace("%player%", p.getName())));
                                this.removeItem(p.getInventory(), Swapper.SwapperItem().getType(), 1);
                            }
                        } else {
                            e.setCancelled(true);
                        }
                    }
                }

            }

        }
    }

}
