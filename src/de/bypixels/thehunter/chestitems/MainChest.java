package de.bypixels.thehunter.chestitems;

// Code by: PixelsDE /

// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /

import de.bypixels.thehunter.main.Main;
import de.bypixels.thehunter.util.special.Variables;

import de.bypixels.thehunter.util.Messages;

import de.bypixels.thehunter.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainChest implements Listener {

	private Main plugin;

	public static boolean auffullen = false;
	private int seconds = Settings.cfg.getInt("Chests_Items");

	public MainChest(Main plugin) {
		this.plugin = plugin;
	}

	public static HashMap<Location, Inventory> chests = new HashMap<>();
	private static YamlConfiguration cfg = Messages.cfg;

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (Variables.playing.contains(e.getPlayer())) {
			if (e.getClickedBlock() != null) {
				if (e.getClickedBlock().getType() == Material.CHEST) {
					if (auffullen == true) {

						Player p = e.getPlayer();
						Location loc = e.getClickedBlock().getLocation();
						if (chests.containsKey(loc)) {
							e.setCancelled(true);
							p.openInventory(chests.get(loc));
							return;

						} else {
							Random r = new Random();
							int l = 1;
							l = r.nextInt(Settings.cfg.getInt("Chests_Items"));
							Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST);
							List<ItemStack> items = new ArrayList<>();
							items.add(Ammo.MinigunAmmo());
							items.add(Ammo.AKAmmo());
							items.add(Ammo.PistolAmmo());
							items.add(Ammo.SniperAmmo());
							items.add(HackerEye.theEye());
							items.add(Food.Food());
							items.add(Healing.MediPack());
							items.add(Swapper.SwapperItem());
							items.add(EnergieDrink.EnergieDrink());
							items.add(Tracker.Tracker());

							while (l != 0) {
								l--;
								Random r2 = new Random();
								Random r3 = new Random();

								int n2 = r2.nextInt(27);
								int n3 = r3.nextInt(items.size());
								inv.setItem(n2, items.get(n3));
							}

							chests.put(loc, inv);
							Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getPlugin(), new Runnable() {
								@Override
								public void run() {
									seconds--;
									// TODO Auto-generated method stub

									String msg = ChatColor.translateAlternateColorCodes('&',
											Messages.cfg.getString("Chests_Refill").replace("%seconds%",
													Integer.toString(seconds)));
									String prefix = ChatColor.translateAlternateColorCodes('&',
											Settings.cfg.getString("Prefix"));
									switch (seconds) {
									case 6000:

										Bukkit.broadcastMessage(prefix + msg);
										break;

									case 3600:
										Bukkit.broadcastMessage(prefix + msg);
										break;
									case 10:
										Bukkit.broadcastMessage(prefix + msg);
										break;
									case 5:
										Bukkit.broadcastMessage(prefix + msg);
										break;
									case 4:
										Bukkit.broadcastMessage(prefix + msg);
										break;
									case 3:
										Bukkit.broadcastMessage(prefix + msg);
										break;
									case 2:
										Bukkit.broadcastMessage(prefix + msg);
										break;
									case 1:
										Bukkit.broadcastMessage(prefix + msg);
										break;
									case 0:
										chests.remove(loc, inv);
										String msg1 = ChatColor.translateAlternateColorCodes('&',
												cfg.getString("Chests_Refilled"));
										String prefix1 = ChatColor.translateAlternateColorCodes('&',
												Settings.cfg.getString("Prefix"));
										Bukkit.broadcastMessage(prefix1 + msg1);
										break;
									default:
										break;
									}
								}
							}, 20 * Settings.cfg.getInt("Chests_Refill"), 20 * Settings.cfg.getInt("Chests_Refill"));
						}
                        e.setCancelled(true);
						p.openInventory(chests.get(loc));

						return;

					} else {

					}
				}
			}
		}
	}

}
