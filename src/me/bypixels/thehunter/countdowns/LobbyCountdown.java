package me.bypixels.thehunter.countdowns;

import me.bypixels.thehunter.gamestates.GameState;
import me.bypixels.thehunter.gamestates.GameStateHandler;
import me.bypixels.thehunter.gamestates.LobbyState;

import me.bypixels.thehunter.main.Main;
import me.bypixels.thehunter.util.Messages;
import me.bypixels.thehunter.util.Settings;
import me.bypixels.thehunter.util.special.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class LobbyCountdown extends Countdown {

	public static boolean isIdling = false, isRunning = false;
	private Main plugin;
	private int seconds = 60;
	private int taskID, idleID;

	public LobbyCountdown(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public void start() {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
			isRunning = true;

			taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {

				@Override
				public void run() {
					if (Settings.cfg.getBoolean("Edit_Mode") == false) {

						for (Player a : Variables.playing)
							if (Variables.plays.contains(a)) {
								a.setLevel(seconds);
							} else {

							}

                        YamlConfiguration cfg = Messages.cfg;
                        String msg = ChatColor.translateAlternateColorCodes('&',
                                cfg.getString("Game_Timer").replace("%seconds%", Integer.toString(seconds)));
                        String prefix = ChatColor.translateAlternateColorCodes('&',
                                Settings.cfg.getString("Prefix"));
						switch (seconds) {
						case 60:
                            Bukkit.broadcastMessage(prefix + msg);
                            for (Player a : Variables.playing)
                                a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
							break;
						case 30:
                            Bukkit.broadcastMessage(prefix + msg);
                            for (Player a : Variables.playing)
                                a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
                            break;
						case 15:
                            Bukkit.broadcastMessage(prefix + msg);
                            for (Player a : Variables.playing)
                                a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
                            break;
						case 10:
                            Bukkit.broadcastMessage(prefix + msg);
                            for (Player a : Variables.playing)
                                a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
                            break;
						case 5:
                            Bukkit.broadcastMessage(prefix + msg);
                            for (Player a : Variables.playing)
                                a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
                            break;
						case 3:
                            Bukkit.broadcastMessage(prefix + msg);
                            for (Player a : Variables.playing)
                                a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
                            break;
						case 2:
							Bukkit.broadcastMessage(prefix + msg);
							for (Player a : Variables.playing)
								a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
							break;
						case 1:
                            Bukkit.broadcastMessage(prefix + msg);
                            for (Player a : Variables.playing)
                                a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
							break;
						case 0:
							GameStateHandler.setGameState(GameState.INGAME_STATE);
							break;
						default:
							break;
						}
						seconds--;
						// TODO Auto-generated method stub

					} else {
						LobbyCountdown.isRunning = false;
						LobbyCountdown.isIdling = false;
					}
				}
			}, 0, 20 * 1);

		} else {
			stop();
			stopIdle();
			stopCountDown();
		}
	}

	public void idle() {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
			isIdling = true;
			idleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {

				@Override
				public void run() {

					int missing = LobbyState.MIN_PLAYERS - Variables.playing.size();
					YamlConfiguration cfg = Messages.cfg;
					String msg = ChatColor.translateAlternateColorCodes('&',
							cfg.getString("Missing_Player").replace("%missing%", Integer.toString(missing)));
					String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
					Bukkit.broadcastMessage(prefix + msg);
					// TODO Auto-generated method stub

				}
			}, 0, 20 * 10);

		} else {

		}
	}

	public void stopIdle() {

		if (isIdling)
			isIdling = false;
		Bukkit.getScheduler().cancelTask(idleID);

	}

	public void stopCountDown() {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
			if (isRunning) {
				isRunning = false;
				Bukkit.getScheduler().cancelTask(taskID);
				seconds = 60;
				for (Player a : Variables.playing)
					a.setLevel(0);
			}
		} else {

		}

	}

	public void stop() {
		isIdling = false;
		isRunning = false;
		// TODO Auto-generated method stub
		seconds = 60;
		Bukkit.getScheduler().cancelTask(taskID);

		Bukkit.getScheduler().cancelTask(idleID);
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

}
