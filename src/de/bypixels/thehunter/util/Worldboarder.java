package de.bypixels.thehunter.util;

import de.bypixels.thehunter.main.theHunterMain;
import de.bypixels.thehunter.util.special.LocationCreator;
import de.bypixels.thehunter.util.special.Variables;
import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.WorldBorder;
import org.bukkit.configuration.file.YamlConfiguration;

public class Worldboarder {

	private theHunterMain plugin;

	public Worldboarder(theHunterMain plugin) {
		this.plugin = plugin;
	}

	private static int maxsize = Settings.cfg.getInt("Border_MaxSize");
	private static int minsize = Settings.cfg.getInt("Border_MinSize");

	public static void ResetWorldboarder() {
		WorldBorder border = Bukkit.getWorld(Settings.cfg.getString("World_Name")).getWorldBorder();
		try {
			border.setCenter(LocationCreator.getConfigLocation("WorldCenter", Variables.cfg));
			border.setSize(maxsize);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			YamlConfiguration cfg = Messages.cfg;
			String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("WorldCenter_Error"));
			String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
			Bukkit.getConsoleSender().sendMessage(prefix + msg);

		}
	}

	@SuppressWarnings("deprecation")
	public static void Worldboarder() {
		WorldBorder border = Bukkit.getWorld(Settings.cfg.getString("World_Name")).getWorldBorder();
		try {
			border.setCenter(LocationCreator.getConfigLocation("WorldCenter", Variables.cfg));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			YamlConfiguration cfg = Messages.cfg;
			String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("WorldCenter_Error"));
			String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
			Bukkit.getConsoleSender().sendMessage(prefix + msg);

		}
		border.setSize(maxsize);
		border.setDamageAmount(Settings.cfg.getInt("Border_Damage"));
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(theHunterMain.getPlugin(), new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (maxsize > minsize) {
					maxsize--;
					border.setSize(maxsize);
				} else {
					border.setSize(minsize);

				}

			}
		}, 20 * Settings.cfg.getInt("Border_Shrink"), 20 * Settings.cfg.getInt("Border_Shrink"));

	}

}
