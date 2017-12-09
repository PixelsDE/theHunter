package gamestates;

import ChestItems.Knife;
import ChestItems.MainChest;
import Guns.AK;
import Guns.Minigun;
import Guns.Pistol;
import Guns.Sniper;
import HuntingMain.Main;
import Util.Settings;
import Util.Var;
import Util.Worldboarder;
import commands.CMDsetspawn;
import methods.LocationCreator;
import methods.Messages;
import methods.countdowns.EndCountdown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class IngameState extends GameState {

	public static boolean spawners = false;
	private static Main plugin;
	private EndCountdown endCountdown;

	private int a = 0;

	public IngameState(Main plugin) {
		IngameState.plugin = plugin;
	}

	@Override
	public void init() {
		if (Settings.editmode == false) {
			endCountdown = new EndCountdown(Main.getPlugin());
			// TODO Auto-generated method stub
			for (int i = 0; i < Var.playing.size(); i++) {
				Player p = Var.playing.get(i);

				Var.plays.remove(p);
				p.getInventory().clear();

				MainChest.auffullen = true;

				if (Var.playing.contains(p)) {

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
					p.getInventory().setItem(3, AK.mg1());
					p.getInventory().setItem(8, Knife.theKnife());
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.cfg.getString("Game_Start")));

				} else {
					p.teleport(LocationCreator.getConfigLocation("Spawn.Spectator", Var.cfg));
				}

			}

		} else {

		}
	}

	@Override
	public void end() {
		endCountdown.start();
		// TODO Auto-generated method stub
	}

}
