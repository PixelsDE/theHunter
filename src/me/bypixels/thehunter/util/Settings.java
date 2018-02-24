package me.bypixels.thehunter.util;
//Code by: PixelsDE

import me.bypixels.thehunter.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Settings {

	private static Main plugin;

	public Settings(Main plugin) {
		Settings.plugin = plugin;
	}

	public static File cfgFile = new File("plugins/theHunter/settings.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);
	public static String pl = new String();
	public static boolean editmode = cfg.getBoolean("Edit_Mode");

	public static void settings() {

		cfg.addDefault("Prefix", "&7[&6TheHunter&7] ");
		cfg.addDefault("Max_Players", 16);
		cfg.addDefault("Min_Players", 2);
		cfg.addDefault("Server_Name", "&fPixelTopia");
		cfg.addDefault("Ts_Ip", "&fts.PixelTopia.de");
		cfg.addDefault("Autoupdate", true);
		cfg.addDefault("Scoreboard", true);
		cfg.addDefault("Scoreboard_Health", true);
		cfg.addDefault("Edit_Mode", false);
		cfg.addDefault("Shoot_Sniper", 4);
		cfg.addDefault("Damage_Sniper", 5);
		cfg.addDefault("Shoot_AK", 1);
		cfg.addDefault("Damage_AK", 3);
		cfg.addDefault("Shoot_Pistol", 3);
		cfg.addDefault("Damage_Pistol", 2);
		cfg.addDefault("Shoot_Minigun", 1);
		cfg.addDefault("Damage_Minigun", 1);
		cfg.addDefault("Eye_View", 10);
		cfg.addDefault("Chests_Items", 15);
		cfg.addDefault("Chests_Refill", 6000);
		cfg.addDefault("World_Name", "world");
		cfg.addDefault("Border_MaxSize", 1400);
		cfg.addDefault("Border_MinSize", 200);
		cfg.addDefault("Border_Damage", 2.5);
		cfg.addDefault("Border_Shrink", 5);
		cfg.addDefault("World_Build", false);
		cfg.addDefault("Ingame_Regeneration", false);
		cfg.addDefault("Healing_Heal", 5);
		cfg.addDefault("Game_Countdown", 72000);
		cfg.addDefault("Map_Name", "Arena");
		cfg.addDefault("Can_Win", true);


		try {
			cfg.options().copyDefaults(true);
			cfg.save(cfgFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
