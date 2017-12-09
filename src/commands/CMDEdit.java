package commands;
// Made by PixelsDE /
// Minecraft-Developer /
// Copyright PixelsDE /
// youtube.com/bypixels /

import Util.Settings;
import Util.Var;
import methods.Messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class CMDEdit implements CommandExecutor {
    File file = Settings.cfgFile;
    YamlConfiguration cfg = Var.cfg;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if (cmd.getName().equalsIgnoreCase("editmode")) {
            if (sender instanceof Player) {
                if (args.length == 1) {
                    Player p = (Player) sender;
                    if (p.hasPermission("hunter.edit")) {
                        if (args[0].equalsIgnoreCase("off")) {

                            Settings.cfg.set("Edit_Mode", false);
                            Settings.editmode = false;
                            p.sendMessage("§cEditMode OFF!");

                        } else if (args[0].equalsIgnoreCase("on")) {
                            Settings.cfg.set("Edit_Mode", true);
                            Settings.editmode = true;
                            p.sendMessage("§cEditMode ON!");
                        } else {

                            p.sendMessage("§cEDITMODE ON / OFF!");

                        }
                    } else {
                        YamlConfiguration cfg1 = Messages.cfg;
                        String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Noperm"));
                        String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                        p.sendMessage(prefix1 + msg1);
                    }

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
