package commands;

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

import gamestates.GameStateHandler;
import gamestates.LobbyState;


public class CMDstart implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // TODO Auto-generated method stub

        if (cmd.getName().equalsIgnoreCase("start")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("hunter.start")) {
                    if (args.length == 0) {
                        if (GameStateHandler.getCurrentState() instanceof LobbyState) {
                            LobbyState ls = (LobbyState) GameStateHandler.getCurrentState();
                            if (ls.getCountdown().getSeconds() > 5) {
                                ls.getCountdown().setSeconds(5);
                                YamlConfiguration cfg1 = Messages.cfg;
                                String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Start_Used"));
                                String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                                p.sendMessage(prefix1 + msg1);

                                
                            }

                        }else {
                            YamlConfiguration cfg1 = Messages.cfg;
                            String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Start_Lobby"));
                            String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                            p.sendMessage(prefix1 + msg1);
                        }





                    }else {
                        YamlConfiguration cfg1 = Messages.cfg;
                        String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Start_Use"));
                        String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                        p.sendMessage(prefix1 + msg1);
                    }
                }else {
                    YamlConfiguration cfg1 = Messages.cfg;
                    String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Noperm"));
                    String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                    p.sendMessage(prefix1 + msg1);
                }

            }else{
                YamlConfiguration cfg1 = Messages.cfg;
                String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Must_Player"));
                String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                Bukkit.getConsoleSender().sendMessage(prefix1 + msg1);
            }

        }
        return false;
    }

}
