package listeners;



// Code by: PixelsDE /

// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /

import util.Scoreboard;

import util.Settings;
import util.StatsSystem;
import util.special.Var;
import gamestates.GameState;
import util.special.LocationCreator;
import util.Messages;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import HuntingMain.Main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class EVENTdeath implements Listener{
	private Main plugin;

	public EVENTdeath(Main plugin) {
		this.plugin = plugin;
	}

	public static String winner;
	public static HashMap<Block, Inventory> DeathChest = new HashMap<>();
	public static void setTag(Player p, String tag, Player[] toSet) {	
	}
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {	
		Player p = (Player) e.getEntity();

		Player killer = e.getEntity().getKiller();
		for(Player all : Bukkit.getOnlinePlayers()) {
			if (Var.playing.contains(all)) {
				all.hidePlayer(p);
				p.showPlayer(all);
				}else {
					all.showPlayer(p);
					p.showPlayer(all);
				}
		}
		if (Var.playing.contains(p)) {
			File file1 = StatsSystem.file;
			YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);

	
			if (e.getEntity().getKiller() instanceof Player) {
				int kills = cfg1.getInt(p.getName()+".Kills");
				int death = cfg1.getInt(p.getName()+".Deaths") +1;
				int points = cfg1.getInt(p.getName()+".Points") -5;
			
				int kills1 = cfg1.getInt(killer.getName()+".Kills") +1;
				int points1 = cfg1.getInt(killer.getName()+".Points") +10;
				int death1 = cfg1.getInt(killer.getName()+".Deaths");
				
				
				
				
				cfg1.set(killer.getName()+".Kills", kills1);
				cfg1.set(killer.getName()+".Points", points1);
				cfg1.set(killer.getName()+".Deaths", death1);
				cfg1.set(p.getName()+".Points", points);
				cfg1.set(p.getName()+".Deaths", death);
				cfg1.set(p.getName()+".Kills", kills);
				try {
					cfg1.save(file1);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		
			

			int kills = cfg1.getInt(p.getName()+".Kills");
			int death = cfg1.getInt(p.getName()+".Deaths") +1;
			int points = cfg1.getInt(p.getName()+".Points") -5;

			
			cfg1.set(p.getName()+".Points", points);
			cfg1.set(p.getName()+".Deaths", death);
			cfg1.set(p.getName()+".Kills", kills);
			try {
				cfg1.save(file1);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		
			Block blockchest = p.getWorld().getBlockAt(p.getLocation().add(0, 0.5 , 0));
			blockchest.setType(Material.CHEST);
	
			Inventory inv = Bukkit.createInventory(null, 36, ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§6DeathChest");
			inv.setContents(p.getInventory().getContents());
			DeathChest.put(blockchest, inv);
			e.getDrops().clear();
			p.getInventory().clear();
			Var.playing.remove(p);
			Var.spectating.add(p);
			p.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7"
					+ p.getName() + "§f");
			p.setCustomName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7"
					+ p.getName() + "§f");
			p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7"
					+ p.getName() + "§f");
			p.setCustomNameVisible(true);

			p.setGameMode(GameMode.SURVIVAL);
			p.setAllowFlight(true);
			p.getInventory().clear();
			((CraftPlayer) p).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
			p.teleport(LocationCreator.getConfigLocation("Spawn.Spectator", Var.cfg));
			Scoreboard.updateScoreboard(p);
			for (Player all : Bukkit.getOnlinePlayers())
				Scoreboard.updateScoreboard(all);
			if (p.getKiller() != null) {
				GameState.checkWinning();
			if (Var.playing.size() == 1) {
				winner = p.getKiller().getDisplayName();
			}
			GameState.checkWinning();
			}
			if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
				if (e.getEntity().getKiller() instanceof Player) {
					if (Var.playing.contains(p)) {
						File file1 = StatsSystem.file;
						YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);
					
						if (e.getEntity().getKiller() instanceof Player) {
							int kills = cfg1.getInt(p.getName()+".Kills");
							int death = cfg1.getInt(p.getName()+".Deaths") +1;
							int points = cfg1.getInt(p.getName()+".Points") -5;
						
							int kills1 = cfg1.getInt(killer.getName()+".Kills") +1;
							int points1 = cfg1.getInt(killer.getName()+".Points") +10;
							int death1 = cfg1.getInt(killer.getName()+".Deaths");
							
							
							
							
							cfg1.set(killer.getName()+".Kills", kills1);
							cfg1.set(killer.getName()+".Points", points1);
							cfg1.set(killer.getName()+".Deaths", death1);
							cfg1.set(p.getName()+".Points", points);
							cfg1.set(p.getName()+".Deaths", death);
							cfg1.set(p.getName()+".Kills", kills);
							try {
								cfg1.save(file1);
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}
						int kills = cfg1.getInt(p.getName()+".Kills");
						int death = cfg1.getInt(p.getName()+".Deaths") +1;
						int points = cfg1.getInt(p.getName()+".Points") -5;

						cfg1.set(p.getName()+".Points", points);
						cfg1.set(p.getName()+".Deaths", death);
						cfg1.set(p.getName()+".Kills", kills);
						try {
							cfg1.save(file1);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
				
					GameState.checkWinning();
					YamlConfiguration cfg = Messages.cfg;
					String msg = ChatColor.translateAlternateColorCodes('&',
							cfg.getString("Death_Message").replace("%player%", p.getName()).replace("%killer%",
									"§6" + e.getEntity().getKiller().getName()));
					String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
					p.sendMessage(prefix + msg);
				} else {
					YamlConfiguration cfg = Messages.cfg;
					String msg = ChatColor.translateAlternateColorCodes('&',
							cfg.getString("Deathmessage").replace("%player%", "§6" + p.getName()));
					String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
					p.sendMessage(prefix + msg);
				}
			} else {
				YamlConfiguration cfg = Messages.cfg;
				String msg = ChatColor.translateAlternateColorCodes('&',
						cfg.getString("Deathmessage").replace("%player%", "§6" + p.getName()));
				String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
				p.sendMessage(prefix + msg);
			}
		}
	

	@EventHandler
	public void onOpen(PlayerInteractEvent e) {
		if (Var.playing.contains(e.getPlayer())) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (e.getClickedBlock().getType() == Material.CHEST) {
					Block block = e.getClickedBlock();
					for (Block blocks : DeathChest.keySet()) {
						if (blocks.getLocation().equals(block.getLocation())) {
							e.setCancelled(true);
							e.getPlayer().openInventory(DeathChest.get(blocks));
						}
					}
				}
			}
		}
	}

}
