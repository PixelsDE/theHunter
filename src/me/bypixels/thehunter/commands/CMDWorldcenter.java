package me.bypixels.thehunter.commands;

import me.bypixels.thehunter.util.Settings;
import me.bypixels.thehunter.util.special.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


import me.bypixels.thehunter.util.special.LocationCreator;
import me.bypixels.thehunter.util.Messages;

public class CMDWorldcenter implements CommandExecutor{
	
    
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if (cmd.getName().equalsIgnoreCase("setarenacenter")) {
				if (sender instanceof Player) {
					Player p = (Player)sender;
					if (p.hasPermission("hunter.worldcenter") || p.hasPermission("hunter.*")) {
						LocationCreator.createConfiguration(p.getLocation(), "WorldCenter", Variables.cfgFile, Variables.cfg);
	                    YamlConfiguration cfg1 = Messages.cfg;
	                    String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("WorldCenter_Set"));
	                    String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
	                    p.sendMessage(prefix1 + msg1);
					}else {
	                    YamlConfiguration cfg1 = Messages.cfg;
	                    String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Noperm"));
	                    String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
	                    p.sendMessage(prefix1 + msg1);
						
					}
				}else {
	                YamlConfiguration cfg1 = Messages.cfg;
	                String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Must_Player"));
	                String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
	                Bukkit.getConsoleSender().sendMessage(prefix1 + msg1);
				}
				
			}
			
			// TODO Auto-generated method stub
			return false;
		}
	



}