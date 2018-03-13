package de.bypixels.thehunter.gamestates;


import de.bypixels.thehunter.util.Messages;
import de.bypixels.thehunter.util.Settings;
import de.bypixels.thehunter.util.special.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public abstract class GameState {

	public static final int LOBBY_STATE = 0, INGAME_STATE = 1, END_STATE = 2;

	public abstract void init();

	public abstract void end();

	public static void checkWinning(Player p) {

        if (Settings.editmode == false) {
            if (GameStateHandler.getCurrentState() instanceof IngameState) {

                if (Settings.cfg.getBoolean("Can_Win") == true) {
                    if (Variables.playing.size() == 1) {
                        if (Variables.playing.size() == 1) {


                            YamlConfiguration cfg1 = Messages.cfg;
                            String msg1 = ChatColor.translateAlternateColorCodes('&',
                                    cfg1.getString("Win_Player").replace("%player%", p.getName()));
                            String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                players.sendMessage(prefix1 + msg1);
                            }

                            GameStateHandler.getCurrentState().end();
                            GameStateHandler.setGameState(2);
                        }
                    } else {

                    }
                }else{

                }
            } else {

            }
        }

    }

    public static void checkWinning() {

        if (Settings.editmode == false) {
            if (GameStateHandler.getCurrentState() instanceof IngameState) {

                if (Settings.cfg.getBoolean("Can_Win") == true) {
                    if (Variables.playing.size() == 0 || Variables.playing.size() == 1) {
             if (Variables.playing.size() == 0) {
                            YamlConfiguration cfg1 = Messages.cfg;
                            String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Win_MSG"));
                            String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                            for (Player players : Bukkit.getOnlinePlayers()) {

                                players.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                                players.sendMessage(prefix1 + msg1);
                            }
                            GameStateHandler.getCurrentState().end();
                            try {
                                GameStateHandler.setGameState(2);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block

                            }

                        } else if (Variables.playing.size() == 1){
                 YamlConfiguration cfg1 = Messages.cfg;
                 for (Player players : Bukkit.getOnlinePlayers()) {
                     if (Variables.playing.contains(players)) {
                         String msg1 = ChatColor.translateAlternateColorCodes('&',
                                 cfg1.getString("Win_Player").replace("%player%", players.getName()));
                         String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                         for (Player all : Bukkit.getOnlinePlayers()) {
                             all.playSound(players.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                             all.sendMessage(prefix1 + msg1);
                         }
                     }


                 }

                 GameStateHandler.getCurrentState().end();
                 GameStateHandler.setGameState(2);
                        }

                    } else {

                    }
                }else{

                }
            } else {

            }
        }

    }
}
