package me.bypixels.thehunter.gamestates;


import me.bypixels.thehunter.util.Messages;
import me.bypixels.thehunter.util.Settings;
import me.bypixels.thehunter.util.special.Variables;
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
                    if (Variables.playing.size() == 1 || Variables.playing.size() == 0) {
                        if (Variables.playing.size() == 1) {

                            YamlConfiguration cfg1 = Messages.cfg;
                            String msg1 = ChatColor.translateAlternateColorCodes('&',
                                    cfg1.getString("Win_Player").replace("%player%", p.getName()));
                            String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.playSound(players.getLocation(), Sound.LEVEL_UP, 1, 1);
                                players.sendMessage(prefix1 + msg1);
                            }

                            GameStateHandler.getCurrentState().end();
                            GameStateHandler.setGameState(2);
                        } else if (Variables.playing.size() == 0) {
                            YamlConfiguration cfg1 = Messages.cfg;
                            String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Win_MSG"));
                            String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                            for (Player players : Bukkit.getOnlinePlayers()) {

                                players.playSound(players.getLocation(), Sound.LEVEL_UP, 1, 1);
                                players.sendMessage(prefix1 + msg1);
                            }
                            GameStateHandler.getCurrentState().end();
                            try {
                                GameStateHandler.setGameState(2);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block

                            }

                        } else {

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
