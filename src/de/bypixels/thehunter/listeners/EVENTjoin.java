package de.bypixels.thehunter.listeners;

import de.bypixels.thehunter.countdowns.EndCountdown;
import de.bypixels.thehunter.countdowns.LobbyCountdown;
import de.bypixels.thehunter.gamestates.GameStateHandler;
import de.bypixels.thehunter.gamestates.LobbyState;
import de.bypixels.thehunter.main.theHunterMain;
import de.bypixels.thehunter.util.Messages;
import de.bypixels.thehunter.util.Scoreboard;
import de.bypixels.thehunter.util.Settings;
import de.bypixels.thehunter.util.StatsSystem;
import de.bypixels.thehunter.util.special.LocationCreator;
import de.bypixels.thehunter.util.special.Variables;


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

	private static theHunterMain plugin;
    @SuppressWarnings("deprecation")
	public EVENTjoin(theHunterMain plugin) {
		this.plugin = plugin;
	}

	File file = StatsSystem.file;
	YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    @SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
			e.setJoinMessage(null);
			Player p = e.getPlayer();

            Variables.plays.add(p);

			Variables.localkills.put(p, 0);
			p.setHealth(p.getMaxHealth());
			p.setSaturation(20);


			for (Player a : Bukkit.getOnlinePlayers())
				Scoreboard.setScoreboard(p);
			p.removePotionEffect(PotionEffectType.SLOW);
			p.setLevel(0);
			p.resetPlayerWeather();
			p.setPlayerTime(6000, true);
			p.updateInventory();

            Variables.spectating.remove(p);
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
                    Variables.playing.remove(p);
                    Variables.plays.remove(p);
                    Variables.spectating.add(p);
					p.teleport(LocationCreator.getConfigLocation("Spawn.EVENTSpectator", Variables.cfg));
					p.setAllowFlight(true);
					for (Player all : Bukkit.getOnlinePlayers()) {
						if (Variables.spectating.contains(all)) {
							all.showPlayer(p);
						} else {
							all.showPlayer(p);
						}
						p.showPlayer(all);
					}
				} else {
					for (Player all : Bukkit.getOnlinePlayers()) {
						if (Variables.spectating.contains(all)) {
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
                    Variables.playing.add(p);
                    Variables.plays.add(p);
					
					try {
						p.teleport(LocationCreator.getConfigLocation("Spawn.Lobby", Variables.cfg));

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
					Scoreboard.setScoreboard(p);

				try {

				} catch (Exception e2) {
					YamlConfiguration cfg = Messages.cfg;
					String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Game_Notdefined"));
					String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
					p.sendMessage(prefix + msg);
					Scoreboard.setScoreboard(p);
				}

				if (Variables.playing.size() >= LobbyState.MIN_PLAYERS) {
					if (ls.getCountdown().isRunning == false) {

						ls.getCountdown().stopIdle();
						ls.getCountdown().start();

					}
				}
				if (Variables.playing.size() < LobbyState.MIN_PLAYERS) {
					if (ls.getCountdown().isIdling == false) {
						ls.getCountdown().idle();
						Scoreboard.setScoreboard(p);
					} else {

					}
				} else {

				}

			} else {
				p.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7" + p.getName() + "§f");
				p.setCustomName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7" + p.getName() + "§f");
				p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7" + p.getName() + "§f");
				p.setCustomNameVisible(true);
				Variables.playing.remove(p);
				Variables.plays.remove(p);
				p.setAllowFlight(true);
				Variables.spectating.add(p);
				p.teleport(LocationCreator.getConfigLocation("Spawn.Spectator", Variables.cfg));
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
			if (Variables.playing.contains(p)) {
				if (Variables.plays.contains(p)) {
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
			if (Variables.playing.contains(p)) {
				if (Variables.plays.contains(p)) {
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
				if (Variables.playing.contains(p)) {
					
				
				if (Variables.lobby == true){
						
						e.setCancelled(true);
						e.setDamage(0);
						YamlConfiguration cfg = Messages.cfg;
						String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Game_Nothit"));
						String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
						damager.sendMessage(prefix + msg);
						e.setCancelled(true);
					} else {

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
			if (Variables.playing.contains(p)) {
				if (Variables.plays.contains(p)) {
					e.setCancelled(true);
				} else {

				}
			} else {

			}
		} else {

		}
	}

    @SuppressWarnings("deprecation")
	@EventHandler
	public void onpickup(PlayerPickupItemEvent e) {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
			Player p = e.getPlayer();
			if (Variables.playing.contains(p)) {
				if (Variables.plays.contains(p)) {
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
