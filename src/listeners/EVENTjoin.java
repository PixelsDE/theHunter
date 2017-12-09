package listeners;

import HuntingMain.Main;
import Util.Scoreboard;
import Util.Settings;
import Util.StatsSystem;
import Util.Var;
import gamestates.GameStateHandler;
import gamestates.LobbyState;
import methods.LocationCreator;
import methods.Messages;
import methods.countdowns.EndCountdown;
import methods.countdowns.LobbyCountdown;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.potion.PotionEffectType;

public class EVENTjoin implements Listener {

	private static Main plugin;

	public EVENTjoin(Main plugin) {
		this.plugin = plugin;
	}

	File file = StatsSystem.file;
	YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
			e.setJoinMessage(null);
			Player p = e.getPlayer();

			Var.plays.add(p);



			for (Player a : Bukkit.getOnlinePlayers())
				Util.Scoreboard.updateScoreboard(a);
			p.removePotionEffect(PotionEffectType.SLOW);
			p.setLevel(0);
			p.resetPlayerWeather();
			p.setPlayerTime(6000, true);
			p.updateInventory();

			Var.spectating.remove(p);
			p.setGameMode(GameMode.SURVIVAL);
			p.getInventory().clear();
			if (GameStateHandler.getCurrentState() instanceof LobbyState) {

				LobbyState ls = (LobbyState) GameStateHandler.getCurrentState();

				p.removePotionEffect(PotionEffectType.SLOW);
				p.resetPlayerWeather();
				p.setPlayerTime(6000, true);
				p.updateInventory();

				p.setGameMode(GameMode.SURVIVAL);
				p.getInventory().clear();
				if (LobbyState.MAX_PLAYERS < Bukkit.getServer().getOnlinePlayers().size()) {

					p.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7" + p.getName() + "§f");
					p.setCustomName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7" + p.getName() + "§f");
					p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7" + p.getName() + "§f");
					p.setCustomNameVisible(true);
					Var.playing.remove(p);
					Var.plays.remove(p);
					Var.spectating.add(p);
					p.teleport(LocationCreator.getConfigLocation("Spawn.Spectator", Var.cfg));
					p.setAllowFlight(true);
					for (Player all : Bukkit.getOnlinePlayers()) {
						if (Var.spectating.contains(all)) {
							all.showPlayer(p);
						} else {
							all.showPlayer(p);
						}
						p.showPlayer(all);
					}
				} else {
					for (Player all : Bukkit.getOnlinePlayers()) {
						if (Var.spectating.contains(all)) {
							p.hidePlayer(all);
							all.showPlayer(p);
						} else {
							p.showPlayer(all);
							all.showPlayer(p);
						}

					}
					int games = cfg.getInt(p.getName() +(".Games"))+ 1;
					cfg.set(p.getName() + ".Games", games);
			        try {
			            cfg.save(file);
			        } catch (IOException e1) {
			            e1.printStackTrace();
			        }
					Var.playing.add(p);
					Var.plays.add(p);
					
					try {
						p.teleport(LocationCreator.getConfigLocation("Spawn.Lobby", Var.cfg));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
					
					}
					p.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§f" + p.getName() + "§f");
					p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§f" + p.getName() + "§f");
					p.setCustomName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§f" + p.getName() + "§f");
					p.setCustomNameVisible(true);

				}

				for (Entity en : Bukkit.getWorld(p.getWorld().getName()).getEntities()) {
					if (en instanceof Item)
						en.remove();
				}

				for (Player a : Bukkit.getOnlinePlayers())
					Util.Scoreboard.updateScoreboard(a);

				try {

				} catch (Exception e2) {
					YamlConfiguration cfg = Messages.cfg;
					String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Game_Notdefined"));
					String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
					p.sendMessage(prefix + msg);
					Scoreboard.updateScoreboard(p);
				}

				if (Var.playing.size() >= LobbyState.MIN_PLAYERS) {
					if (ls.getCountdown().isRunning == false) {

						ls.getCountdown().stopIdle();
						ls.getCountdown().start();

					}
				}
				if (Var.playing.size() < LobbyState.MIN_PLAYERS) {
					if (ls.getCountdown().isIdling == false) {
						ls.getCountdown().idle();
						Scoreboard.updateScoreboard(p);
					} else {

					}
				} else {

				}

			} else {
				p.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7" + p.getName() + "§f");
				p.setCustomName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7" + p.getName() + "§f");
				p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7" + p.getName() + "§f");
				p.setCustomNameVisible(true);
				Var.playing.remove(p);
				Var.plays.remove(p);
				p.setAllowFlight(true);
				Var.spectating.add(p);
				p.teleport(LocationCreator.getConfigLocation("Spawn.Spectator", Var.cfg));
			}

		} else {
			LobbyCountdown.isRunning = false;
			LobbyCountdown.isIdling = false;
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
			Player p = e.getPlayer();
			if (Var.playing.contains(p)) {
				if (Var.plays.contains(p)) {
					YamlConfiguration cfg = Messages.cfg;
					String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Game_Notbuild"));
					String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
					p.sendMessage(prefix + msg);
					e.setCancelled(true);

				} else {

				}
			} else {

			}

		} else {
			LobbyCountdown.isRunning = false;
			LobbyCountdown.isIdling = false;
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
			Player p = e.getPlayer();
			if (Var.playing.contains(p)) {
				if (Var.plays.contains(p)) {
					if (!p.hasPermission("hunter.build")) {
						YamlConfiguration cfg = Messages.cfg;
						String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Game_Notbuild"));
						String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
						p.sendMessage(prefix + msg);

						e.setCancelled(true);

					} else {

					}
				} else {

				}

			} else {

			}
		} else {
			LobbyCountdown.isRunning = false;
			LobbyCountdown.isIdling = false;
		}
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
			if (e.getDamager() instanceof Player) {
				if (e.getEntity() instanceof Player) {
				
				Player damager = (Player) e.getDamager();
				Entity p = e.getEntity();
				if (Var.playing.contains(p)) {
					
				
					if (Var.plays.contains(p)) {
						if (Var.plays.contains(damager)) {
							
						
						e.setCancelled(true);
						e.setDamage(0);
						YamlConfiguration cfg = Messages.cfg;
						String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Game_Nothit"));
						String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
						damager.sendMessage(prefix + msg);
						e.setCancelled(true);
					} else {

					}
					}else {
						
					}
			}
				}

			} else {

			}
		} else {
			LobbyCountdown.isRunning = false;
			LobbyCountdown.isIdling = false;
		}
	}



	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
			Player p = e.getPlayer();
			if (Var.playing.contains(p)) {
				if (Var.plays.contains(p)) {
					e.setCancelled(true);
				} else {

				}
			} else {

			}
		} else {

		}
	}

	@EventHandler
	public void onpickup(PlayerPickupItemEvent e) {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
			Player p = e.getPlayer();
			if (Var.playing.contains(p)) {
				if (Var.plays.contains(p)) {
					e.setCancelled(true);
				} else {

				}
			} else {

			}
		} else {

		}
	}

	@EventHandler
	public void onblock(BlockBreakEvent e) {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
			if (EndCountdown.nobuild == true) {
				e.setCancelled(true);
			}
		} else {

		}
	}

	@EventHandler
	public void onblock2(BlockPlaceEvent e) {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
			if (EndCountdown.nobuild == true) {
				e.setCancelled(true);
			}
		} else {

		}
	}

}
