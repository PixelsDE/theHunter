package util;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;

//C ode by: PixelsDE /
// All rights reserved! /
// Website: bypixels.weebly.com /
// Youtube: byPixels /
public class StatsSystem implements Listener {

	public static File file = new File("plugins/theHunter/stats.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	public Player p;
	private int kills = cfg.getInt(p.getName() + ".Kills");
	private int games = cfg.getInt(p.getName() + ".Games");
	private int deaths = cfg.getInt(p.getName() + ".Deaths");
	private int points = cfg.getInt(p.getName() +".Points");

	public StatsSystem(Player p, int kills, int deaths, int games, int Points) {
		this.kills = cfg.getInt(p.getName() + ".Kills");
		this.deaths = cfg.getInt(p.getName() + ".Deaths");
		this.games = cfg.getInt(p.getName() + ".Games");
		this.games = cfg.getInt(p.getName() + ".Points");
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}
	
	public int getPoints() {
		return kills;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getGames() {
		return games;
	}

	public void setGames(int games) {
		this.games = games;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public static void setStats(Player p) {
		cfg.addDefault(p.getName() + ".Kills", 0);
		cfg.addDefault(p.getName() + ".Deaths", 0);
		cfg.addDefault(p.getName() + ".Games", 0);
		cfg.addDefault(p.getName() + ".Points", 0);
		try {
			cfg.save(StatsSystem.file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}