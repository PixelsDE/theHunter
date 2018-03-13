package de.bypixels.thehunter.commands;
//Code by: PixelsDE


import de.bypixels.thehunter.util.Settings;
import de.bypixels.thehunter.util.special.LocationCreator;
import de.bypixels.thehunter.util.special.Variables;
import de.bypixels.thehunter.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CMDsetSpectator implements CommandExecutor {


    File file = Variables.cfgFile;
    YamlConfiguration cfg = Variables.cfg;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setspectator")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                if (p.hasPermission("hunter.setlobby")) {
                    try {
                        LocationCreator.createConfiguration(p.getLocation(), "Spawn.EVENTSpectator", file, cfg);
                        YamlConfiguration cfg1 = Messages.cfg;
                        String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Set_Spectator"));
                        String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                        p.sendMessage(prefix1 + msg1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


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
