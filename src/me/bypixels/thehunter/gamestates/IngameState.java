package me.bypixels.thehunter.gamestates;

import me.bypixels.thehunter.guns.AK;
import me.bypixels.thehunter.guns.Minigun;
import me.bypixels.thehunter.guns.Pistol;
import me.bypixels.thehunter.guns.Sniper;
import me.bypixels.thehunter.main.Main;

import me.bypixels.thehunter.chestitems.Knife;
import me.bypixels.thehunter.chestitems.MainChest;
import me.bypixels.thehunter.commands.CMDsetspawn;
import me.bypixels.thehunter.countdowns.EndCountdown;
import me.bypixels.thehunter.util.special.LocationCreator;
import me.bypixels.thehunter.util.Messages;

import me.bypixels.thehunter.util.Settings;
import me.bypixels.thehunter.util.Worldboarder;
import me.bypixels.thehunter.util.special.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class IngameState extends GameState {

	public static boolean spawners = false;
	private static Main plugin;
	private EndCountdown endCountdown;

	private int a = 1;

	public IngameState(Main plugin) {
		IngameState.plugin = plugin;
	}

	@Override
	public void init() {
		if (Settings.editmode == false) {
			endCountdown = new EndCountdown(Main.getPlugin());
			// TODO Auto-generated method stub
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
