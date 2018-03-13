package de.bypixels.thehunter.commands;

import de.bypixels.thehunter.util.Settings;
import de.bypixels.thehunter.util.StatsSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.bypixels.thehunter.util.Messages;

/**
 * @author Daniel
 *
 */
public class CMDstats implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("stats")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("hunter.stats") || p.hasPermission("hunter.*")) {
					if (args.length != 0) {
						Player statsplayer = (Player) Bukkit.getServer().getOfflinePlayer(args[0]);
						   try {
		
							   int points = StatsSystem.cfg.getInt(statsplayer.getName() + ".Points");
							   int kills = StatsSystem.cfg.getInt(statsplayer.getName() + ".Kills");
							   int games = StatsSystem.cfg.getInt(statsplayer.getName() + ".Games");
							   int deaths = StatsSystem.cfg.getInt(statsplayer.getName() + ".Deahts");
							   YamlConfiguration cfg1 = Messages.cfg;
							   String msg = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Stats")).replace("%kills%", Integer.toString(kills)).replace("%games%", Integer.toString(games)).replace("%deaths%", Integer.toString(deaths)).replace("%player%", statsplayer.getName()).replace("%points%", Integer.toString(points));
						
							    String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
							    p.sendMessage(prefix1 + msg);
						} catch (Exception e) {
							// TODO Auto-generated catch block
				            YamlConfiguration cfg1 = Messages.cfg;
				            String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("No_Stats").replace("%player%", statsplayer.getName()));
				            String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
				            p.sendMessage(prefix1 + msg1);
						}
					}else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix") + "§c/Stats §6<Player>§c!"));
					}
				}else {
		               YamlConfiguration cfg1 = Messages.cfg;
	                    String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Noperm"));
	                    String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
	                    p.sendMessage(prefix1 + msg1);
				}
			}else {
				
			}
            YamlConfiguration cfg1 = Messages.cfg;
            String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Must_Player"));
            String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
            Bukkit.getConsoleSender().sendMessage(prefix1 + msg1);
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
