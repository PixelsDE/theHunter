package gamestates;

import Util.Settings;

import Util.Var;
import listeners.EVENTdeath;
import methods.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public abstract class GameState {

	public static final int LOBBY_STATE = 0, INGAME_STATE = 1, END_STATE = 2;

	public abstract void init();

	public abstract void end();

	public static void checkWinning() {

		if (Settings.editmode == false) {
			if (GameStateHandler.getCurrentState() instanceof IngameState) {

				if (Var.playing.size() == 1 || Var.playing.size() == 0) {
					if (Var.playing.size() == 1) {
						YamlConfiguration cfg1 = Messages.cfg;
						String msg1 = ChatColor.translateAlternateColorCodes('&',
								cfg1.getString("Win_Player").replace("%player%", EVENTdeath.winner));
						String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
						Bukkit.broadcastMessage(prefix1 + msg1);
						GameStateHandler.getCurrentState().end();
						GameStateHandler.setGameState(2);
					} else if (Var.playing.size() == 0) {
						YamlConfiguration cfg1 = Messages.cfg;
						String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Win_MSG"));
						String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
						Bukkit.broadcastMessage(prefix1 + msg1);
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
			} else {

			}
		}

	}
}
