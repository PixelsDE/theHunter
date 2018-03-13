package de.bypixels.thehunter.commands;
//Code by: PixelsDE


import de.bypixels.thehunter.util.Settings;
import de.bypixels.thehunter.util.StatsSystem;
import de.bypixels.thehunter.util.special.Variables;
import de.bypixels.thehunter.gamestates.GameStateHandler;
import de.bypixels.thehunter.gamestates.IngameState;
import de.bypixels.thehunter.listeners.EVENTdeath;
import de.bypixels.thehunter.util.Messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.IOException;

public class CMDleave implements CommandExecutor{


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("leave")){
            if (sender instanceof Player){
                Player p = (Player)sender;
                if (p.hasPermission("hunter.leave")){


                    if (Variables.playing.contains(p)){
                        if (GameStateHandler.getCurrentState() instanceof IngameState) {

                            try {
                                Variables.playing.remove(p);
                                Variables.spectating.add(p);
                                int kills = StatsSystem.cfg.getInt(p.getName() + ".Kills");
                                int death = StatsSystem.cfg.getInt(p.getName() + ".Deaths") + 1;
                                int points = StatsSystem.cfg.getInt(p.getName() + ".Points") - 5;

                                StatsSystem.cfg.set(p.getName() + ".Points", points);
                                StatsSystem.cfg.set(p.getName() + ".Deaths", death);
                                StatsSystem.cfg.set(p.getName() + ".Kills", kills);

                                p.getWorld().strikeLightning(p.getLocation());
                                Block blockchest = p.getWorld().getBlockAt(p.getLocation().add(0, 0.5, 0));
                                blockchest.setType(Material.TRAPPED_CHEST);

                                Inventory inv = Bukkit.createInventory(null, 36,
                                        ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§6DeathChest");
                                inv.setContents(p.getInventory().getContents());
                                EVENTdeath.DeathChest.put(blockchest, inv);

                                p.getInventory().clear();
                                p.kickPlayer("");
                                try {
                                    StatsSystem.cfg.save(StatsSystem.file);
                                } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else{
                           p.kickPlayer("");
                        }
                    }else{
                        p.kickPlayer("");
                    }
                }else{
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
