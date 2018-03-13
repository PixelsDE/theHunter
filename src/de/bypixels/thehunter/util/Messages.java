package de.bypixels.thehunter.util;
//Code by: PixelsDE

import de.bypixels.thehunter.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Messages {

	private static Main plugin;

	public Messages(Main plugin) {
		this.plugin = plugin;
	}

	public static File File = new File("plugins/theHunter/messages.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(File);

	public static void setMessages() {


		cfg.addDefault("Game_Start", "&aThe Game has started!");
		cfg.addDefault("Game_End", "&cThe Game is over!");
		cfg.addDefault("Game_Restart", "&7The Server will restart in &6%seconds% Seconds&7!");
		cfg.addDefault("Game_Timer", "&7The Game will start in &6%seconds% &7Seconds&7!");
		cfg.addDefault("Missing_Player", "&7To start the Game we need &c%missing%&7 Player!");
		cfg.addDefault("Game_Join", "&7The Player %player% &7has joined the Game! [&n %playing% / %max_players%]");
		cfg.addDefault("Game_Notbuild", "&cYou cant build in the Lobby!");
		cfg.addDefault("Game_Notdefined", "&cThe Game has not beeen defined!");
		cfg.addDefault("Game_Nothit", "&cYou cant hit someone in the Lobby!");
		cfg.addDefault("Game_Quit", "&7The Player &c%player% &7has left the Game! &f[&n %playing% &7/ &f%max_players%]&7!");
		cfg.addDefault("Ingame_Quit", "&7The Player &c%player% &7has left the Game!");
		cfg.addDefault("Start_Use", "&cPlease just use /start!");
		cfg.addDefault("Start_Used", "&aThe Gametimer has been speeded up!");
		cfg.addDefault("Start_Lobby", "&cYou can just speed up the Countdown in the Lobby!");
		cfg.addDefault("Noperm", "&cYou dont have the Permissions to to that!");
		cfg.addDefault("Set_Spectator", "&aThe Spawn of the Spectators were set!");
		cfg.addDefault("Set_Lobby", "&aThe Spawn of the Lobby was set!");
		cfg.addDefault("Kickmessage", "&cThe Game is over!");
		cfg.addDefault("Deathmessage", "&fThe Player: %player% &fdied!");
		cfg.addDefault("Autoupdate", "&cThe Plugin has a &6new Version&c!");
		cfg.addDefault("Update_Error", "&cCant check for Updates!");
		cfg.addDefault("Update_True", "&aThe Plugin is upto Date!");
		cfg.addDefault("Death_Message", "&fThe Player: &6%player% was killed by: &6%killer%&f!");
		cfg.addDefault("Win_Player", "&aThe Player &6%player% &awon the Game!");
		cfg.addDefault("Map_Break", "&cYou cant break the Block: &6%block%&c!");
		cfg.addDefault("Must_Player", "&cYou must be a Player to do that!");
		cfg.addDefault("No_Ammo", "&cYou dont have enoght Ammo for that Gun!");
		cfg.addDefault("Gun_Reload", "&cThe Gun is reloading!");
		cfg.addDefault("Set_Spawn", "&aYou set the Spawn number: &6%zahl%&a!");
		cfg.addDefault("Chests_Refill", "&aAll Chests will refill in:: &6%seconds%&a!");
		cfg.addDefault("Chests_Refilled", "&aAll Chests has been refilled!");
		cfg.addDefault("WorldCenter_Set", "&aYou set the &6Arenacenter&a!");
		cfg.addDefault("WorldCenter_Error", "&cThe &6Arenacenter &cis not defined!");
		cfg.addDefault("WorldCenter_Reset", "&aThe &6Worldboarder &ahas been reset!");
		cfg.addDefault("Win_MSG", "&aThe Game is now over...!");
		cfg.addDefault("Swapper_Use", "&aYou swapped the Location with: &6%player%&a!");
		cfg.addDefault("Player_Tacker", "&aThe Player &6%player% &ais &6%distance% &a away from you!");
		cfg.addDefault("theEye_Use", "&aYou are in the view of: &6%player%&a!");
		cfg.addDefault("Game_Shutdown", "&7The Serer will stop!");
		cfg.addDefault("No_Stats", "&cThere are no stats about: &6%player%&c!");
		cfg.addDefault("Not_Enoght", "&7To start the Game we need: &c%missing%&7 more Player!");
		cfg.addDefault("Not_Interact", "&cYou cannot interact with: &6%object%&c!");
		cfg.addDefault("Stats", "&7The Stats of the Player: &6%player% &7are:&a Kills: &6%kills%&7, &bGames: &6%games%&7, &eDeaths: &6%deaths%&7, &cPoints: &6%points%&7!");

		try {
			cfg.options().copyDefaults(true);
			cfg.save(File);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
