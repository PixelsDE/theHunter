package listeners;

import Util.Scoreboard;


import Util.Settings;
import Util.StatsSystem;
import Util.Var;
import gamestates.GameState;
import gamestates.GameStateHandler;
import gamestates.LobbyState;
import methods.Messages;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;



public class EVENTquit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
    	File file1 = StatsSystem.file;
    	YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);
        if (Settings.editmode == false) {
            e.setQuitMessage(null);
            Player p = e.getPlayer();
            if (GameStateHandler.getCurrentState() instanceof LobbyState) {

                LobbyState ls = (LobbyState) GameStateHandler.getCurrentState();

                try {
                    p.getInventory().clear();
                    p.updateInventory();
if (Var.playing.contains(p)) {
	


	int death = cfg1.getInt(p.getName()+".Deaths") +1;
	try {
		cfg1.save(file1);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

                    Var.spectating.remove(p);
                    Var.playing.remove(p);
                    Var.plays.remove(p);
                    GameState.checkWinning();
                    p.getInventory().clear();
                    YamlConfiguration cfg = Messages.cfg;
                    String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Game_Quit").replace("%max_players%", Integer.toString(Settings.cfg.getInt("Max_Players")))).replace("%player%", p.getDisplayName()).replace("%playing%", Integer.toString(Var.playing.size()));
                    String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                    Bukkit.broadcastMessage(prefix + msg);
                    GameState.checkWinning();
                    for (Player a : Bukkit.getOnlinePlayers())
                        Scoreboard.updateScoreboard(a);
}else {
	int death = cfg1.getInt(p.getName()+".Deaths") +1;
	try {
		cfg1.save(file1);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    Var.spectating.remove(p);
    Var.playing.remove(p);
    Var.plays.remove(p);
    GameState.checkWinning();
    p.getInventory().clear();
    YamlConfiguration cfg = Messages.cfg;
    String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Game_Quit").replace("%max_players%", Integer.toString(Settings.cfg.getInt("Max_Players")))).replace("%player%", p.getDisplayName()).replace("%playing%", Integer.toString(Var.playing.size()));
    String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
    Bukkit.broadcastMessage(prefix + msg);
    GameState.checkWinning();
    for (Player a : Bukkit.getOnlinePlayers())
        Scoreboard.updateScoreboard(a);
}
                } catch (Exception exe) {
                    // TODO: handle exception
                }


                if (Var.playing.size() < LobbyState.MIN_PLAYERS) {
                    if (ls.getCountdown().isRunning) {
                        ls.getCountdown().stopCountDown();
                        ls.getCountdown().idle();
                    
                    } else {

                    }

                } else {

                }
            } else {
            	int death = cfg1.getInt(p.getName()+".Deaths") +1;
            	try {
            		cfg1.save(file1);
            	} catch (IOException e1) {
            		// TODO Auto-generated catch block
            		e1.printStackTrace();
            	}
                p.getInventory().clear();
                p.updateInventory();
                if (Var.playing.contains(p)) {
                    Var.playing.remove(p);
                    Var.plays.remove(p);
                    Var.spectating.remove(p);
                    YamlConfiguration cfg = Messages.cfg;
                    String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Ingame_Quit").replace("%player%", p.getDisplayName()));
                    String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                    Bukkit.broadcastMessage(prefix + msg);
                    try {
						GameState.checkWinning();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
			
					}
                    try {
                        for (Player a : Bukkit.getOnlinePlayers()){
                            Scoreboard.updateScoreboard(a);
                        if (Var.playing.contains(a)) {
                            a.hidePlayer(p);
                        }else{
                            a.showPlayer(p);
                        }
                        }
                    } catch (Exception exe) {

                    }


                } else {
                	
                    Var.playing.remove(p);
                    Var.plays.remove(p);
                    Var.spectating.remove(p);
                }
            }
        }else {

        }
    }


    @EventHandler
    public void shutdown(PlayerQuitEvent e){
        if (Bukkit.getOnlinePlayers().equals(0)){
            Bukkit.getServer().reload();

        }else{

        }
    }

}
