package de.bypixels.thehunter.commands;

// Code by: PixelsDE /


// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /

import de.bypixels.thehunter.main.theHunterMain;
import de.bypixels.thehunter.util.Settings;
import de.bypixels.thehunter.util.special.LocationCreator;


import de.bypixels.thehunter.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CMDsetspawn implements CommandExecutor {

	public static int i = 1;

	private theHunterMain plugin;

	public CMDsetspawn(theHunterMain plugin) {
		this.plugin = plugin;
	}

	public static File file = new File("plugins/theHunterMain/spawns.yml");

	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setspawn")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("hunter.setspawn") || p.hasPermission("hunter.*")) {

					LocationCreator.createConfiguration(p.getLocation(), "Spawn.Player." + i, file, cfg);
					YamlConfiguration cfg1 = Messages.cfg;
					String msg1 = ChatColor.translateAlternateColorCodes('&',
							cfg1.getString("Set_Spawn").replace("%zahl%", Integer.toString(i)));
					String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
					p.sendMessage(prefix1 + msg1);
					cfg.set("Spawns", cfg.getInt("Spawns") + 1);
					i++;
				} else {
					YamlConfiguration cfg1 = Messages.cfg;
					String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Noperm"));
					String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
					p.sendMessage(prefix1 + msg1);
				}
			} else {
				YamlConfiguration cfg1 = Messages.cfg;
				String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Must_Player"));
				String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
				Bukkit.getConsoleSender().sendMessage(prefix1 + msg1);
			}
		}
		return false;
	}
}
