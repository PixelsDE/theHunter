package ChestItems;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import HuntingMain.Main;
import Util.Settings;
import Util.Var;
import methods.Messages;
import net.minecraft.server.v1_8_R3.PacketPlayOutCamera;

// Code by: PixelsDE /
// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /

public class Eye implements Listener {

	private Main plugin;

	public Eye(Main plugin) {
		this.plugin = plugin;
	}

	public static ItemStack theEye() {
		ItemStack item = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(
				ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix") + "§fHackerEye"));
		item.setItemMeta(im);
		item.setAmount(1);
		return item;

	}

	public static void removeItem(PlayerInventory inv, Material type, int amount) {
		for (ItemStack is : inv.getContents()) {
			if (is != null && is.getItemMeta().equals(Eye.theEye().getItemMeta())) {
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
	public void onClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (Var.playing.contains(p)) {

			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		         if (p.getItemInHand().hasItemMeta()) {
		          	 if (p.getInventory().containsAtLeast(Ammo.MinigunAmmo(), 1)) {
		                if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(theEye().getItemMeta().getDisplayName())) {
		                 	 if (p.getInventory().containsAtLeast(Ammo.MinigunAmmo(), 1)) {
		                
							int PlayerNr = new Random().nextInt(Bukkit.getOnlinePlayers().size());
							Player p1 = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[PlayerNr];
							PacketPlayOutCamera camera = new PacketPlayOutCamera();
					if (Var.playing.contains(p1)) {
						if (!p1.equals(p)) {
							e.setCancelled(true);
							camera.a = p1.getEntityId();
							p.setGameMode(GameMode.SURVIVAL);
							p1.setGameMode(GameMode.SURVIVAL);
							removeItem(p.getInventory(), Eye.theEye().getType(), 1);
							p.updateInventory();
							((CraftPlayer) p).getHandle().playerConnection.sendPacket(camera);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"))
									+ ChatColor.translateAlternateColorCodes('&',
											Messages.cfg.getString("theEye_Use").replace("%player%", p1.getName())));

							Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getPlugin(), new Runnable() {
								@Override
								public void run() {
									PacketPlayOutCamera camera = new PacketPlayOutCamera();
									camera.a = p.getEntityId();
									p.setGameMode(GameMode.SURVIVAL);
									p1.setGameMode(GameMode.SURVIVAL);
									((CraftPlayer) p).getHandle().playerConnection.sendPacket(camera);
									// TODO Auto-generated method stub

								}
							}, 20 * Settings.cfg.getInt("Eye_View"));

						}
					}
					}

				}
			}
			}
		}
	}
	}
}
