package de.bypixels.thehunter.util;


import de.bypixels.thehunter.main.Main;
import de.bypixels.thehunter.util.special.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

//C ode by: PixelsDE /
// All rights reserved! /
// Website: bypixels.weebly.com /
// Youtube: byPixels /
public class Scoreboard implements Listener {

    private static Main plugin;
    int sched;

    public Scoreboard(Main plugin) {
        Scoreboard.plugin = plugin;
    }

    
    public static int kills = 0;
    public static void setScoreboard(Player p) {
        if (Settings.cfg.getBoolean("Edit_Mode") == false) {
            if (Settings.cfg.getBoolean("Scoreboard") == true) {

            
                org.bukkit.scoreboard.Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
                Objective obj = scoreboard.registerNewObjective("aaa", "bbb");


                YamlConfiguration cfg = Settings.cfg;
                String name = ChatColor.translateAlternateColorCodes('&', cfg.getString("Server_Name"));
                String ip = ChatColor.translateAlternateColorCodes('&', cfg.getString("Ts_Ip"));

                obj.setDisplayName(name);
                obj.setDisplaySlot(DisplaySlot.SIDEBAR);




                Score five = obj.getScore("§aAlive Players:");
                Score four = obj.getScore("§f" + Integer.toString(Variables.playing.size()));
                Score three = obj.getScore("§eMap Name:");
                Score two = obj.getScore("§f" + Settings.cfg.getString("Map_Name"));
                Score one = obj.getScore("§cTeamspeak: ");
                Score zero = obj.getScore(ip);
                four.setScore(4);
                three.setScore(3);
                two.setScore(2);
                one.setScore(1);
                zero.setScore(0);
                five.setScore(5);
                for (Player a : Bukkit.getOnlinePlayers())
                    a.setScoreboard(scoreboard);

            } else {

            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (Settings.cfg.getBoolean("Edit_Mode") == false) {
            final Player p = e.getPlayer();
            for (Player a : Bukkit.getOnlinePlayers())
                Scoreboard.setScoreboard(p);



        } else {

        }
    }

    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
