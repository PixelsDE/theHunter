package methods.countdowns;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import HuntingMain.Main;
import Util.Settings;
import methods.Messages;

public class FinalCountdown {

	private Main plugin;

	public FinalCountdown(Main plugin) {
		this.plugin = plugin;
	}

	public static int time = Settings.cfg.getInt("Game_Countdown");

	@SuppressWarnings("deprecation")
	public static void CountDown() {

		Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getPlugin(), new Runnable() {

			@Override
			public void run() {
				YamlConfiguration cfg = Messages.cfg;
				String msg = ChatColor.translateAlternateColorCodes('&',
						cfg.getString("Game_Shutdown").replace("%seconds%", Integer.toString(time)));
				String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
				Bukkit.broadcastMessage(prefix + msg);
				Bukkit.getServer().unloadWorld(Settings.cfg.getString("World_Name"), false);
				Bukkit.getServer().createWorld(new WorldCreator(Settings.cfg.getString("World_Name")));

				Bukkit.getServer().reload();
				Bukkit.reload();
				Bukkit.getServer().shutdown();
				Bukkit.shutdown();

			}
		}, 20 * time);

	}

}
