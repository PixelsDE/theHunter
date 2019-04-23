package de.bypixels.thehunter.gamestates;

import de.bypixels.thehunter.chestitems.Knife;
import de.bypixels.thehunter.chestitems.MainChest;
import de.bypixels.thehunter.commands.CMDsetspawn;
import de.bypixels.thehunter.countdowns.EndCountdown;
import de.bypixels.thehunter.countdowns.IngameCountdown;
import de.bypixels.thehunter.guns.AK;
import de.bypixels.thehunter.guns.Minigun;
import de.bypixels.thehunter.guns.Pistol;
import de.bypixels.thehunter.guns.Sniper;
import de.bypixels.thehunter.main.theHunterMain;
import de.bypixels.thehunter.util.Messages;
import de.bypixels.thehunter.util.Settings;
import de.bypixels.thehunter.util.Worldboarder;
import de.bypixels.thehunter.util.special.LocationCreator;
import de.bypixels.thehunter.util.special.Variables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class IngameState extends GameState {

	public static boolean spawners = false;
	private static theHunterMain plugin;
	private EndCountdown endCountdown;

	private int a = 1;

	public IngameState(theHunterMain plugin) {
		IngameState.plugin = plugin;
	}



    @SuppressWarnings("deprecation")
	@Override
	public void init() {
		if (Settings.editmode == false) {
			endCountdown = new EndCountdown(theHunterMain.getPlugin());
	//TODO this is a comment
            IngameCountdown.FinalCountDown();
			for (int i = 0; i < Variables.playing.size(); i++) {
				Player p = Variables.playing.get(i);



				p.getInventory().clear();
				Variables.plays.remove(p);
				MainChest.auffullen = true;

				Variables.lobby = false;
				if (Variables.playing.contains(p)) {
					Variables.plays.remove(p);
					p.teleport(LocationCreator.getConfigLocation("Spawn.Player." + a, CMDsetspawn.cfg));
					a++;
					Worldboarder.Worldboarder();
					for (Player all : Bukkit.getOnlinePlayers()) {
						all.showPlayer(all);
						p.showPlayer(all);
						all.showPlayer(p);
					}
					p.getInventory().setItem(0, Sniper.sniper1());
					p.getInventory().setItem(1, Minigun.minigun1());
					p.getInventory().setItem(2, Pistol.pistol1());
					p.setGameMode(GameMode.SURVIVAL);
					p.getInventory().setItem(3, AK.ak1());
					p.getInventory().setItem(8, Knife.Knife());
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.cfg.getString("Game_Start")));

				} else {
					p.teleport(LocationCreator.getConfigLocation("Spawn.EVENTSpectator", Variables.cfg));
				}

			}

		} else {

		}
	}

	@Override
	public void end() {
		endCountdown.start();
		if (GameStateHandler.getCurrentState() instanceof LobbyState);
		// TODO Auto-generated method stub
	}

}
