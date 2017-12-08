package HuntingMain;

import chestitems.Energie;

import chestitems.Eye;
import chestitems.Healing;
import chestitems.Knife;
import chestitems.MainChest;
import chestitems.Swapper;
import chestitems.Tracker;
import Guns.AK;
import Guns.Minigun;
import Guns.Pistol;
import Guns.Sniper;
import util.special.Metrics;
import util.special.Autoupdater;
import util.Scoreboard;

import util.Settings;
import util.StatsSystem;
import util.Worldboarder;
import commands.*;
import gamestates.GameState;
import gamestates.GameStateHandler;
import listeners.EVENTRegeneration;

import listeners.EVENTdeath;
import listeners.EVENTjoin;
import listeners.EVENTmap;
import listeners.EVENTquit;
import util.Messages;
import util.Spectator;


import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	public static Main plugin;

	public static Main getPlugin() {
		return plugin;
	}

	public void onEnable() {


		Bukkit.getServer().setSpawnRadius(0);

		if (Settings.cfg.getBoolean("Edit_Mode") == true) {
			Bukkit.getConsoleSender().sendMessage("§cThe Server is in EditMode!");
		} else {

		}
		Settings.settings();
		plugin = this;
		init();

		new Autoupdater(this).checkUpdate("41971");
		new GameStateHandler();

		GameStateHandler.setGameState(GameState.LOBBY_STATE);
		Bukkit.getConsoleSender().sendMessage("§a->" + ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix") )+ "§aHunter-Game has been enabled!");
	}

	public void onDisable() {

		if (Settings.cfg.getBoolean("Edit_Mode") == true)
			Bukkit.getConsoleSender().sendMessage("§a-> §b[Hunter] §cEditmode is still ON!");
		Settings.cfg.set("Edit_Mode", false);
		Settings.editmode = false;
		for (Block blocks : EVENTdeath.DeathChest.keySet()) {
			blocks.setType(Material.AIR);
		
	        try {
	            StatsSystem.cfg.save(StatsSystem.file);
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
		}

		Bukkit.getConsoleSender().sendMessage("§a->" + ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix") )+ "§ctheHunter has been disabled!");

	}

	private void init() {

		this.getCommand("start").setExecutor(new CMDstart());
		this.getCommand("setlobby").setExecutor(new CMDsetLobby());
		this.getCommand("setspectator").setExecutor(new CMDsetSpectator());
		this.getCommand("leave").setExecutor(new CMDleave());
		this.getCommand("editmode").setExecutor(new CMDEdit());
		this.getCommand("setspawn").setExecutor(new CMDsetspawn(this));
		this.getCommand("arenacenter").setExecutor(new CMDWorldcenter());
		this.getCommand("stats").setExecutor(new CMDstats());
		
		PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(new EVENTjoin(this), this);
		pm.registerEvents(new EVENTquit(), this);
		pm.registerEvents(new Scoreboard(this), this);
		pm.registerEvents(new EVENTdeath(this), this);
		pm.registerEvents(new Sniper(this), this);
		pm.registerEvents(new Pistol(), this);
		pm.registerEvents(new AK(this), this);
		pm.registerEvents(new Minigun(this), this);
		pm.registerEvents(new MainChest(this), this);
		pm.registerEvents(new Eye(this), this);
		pm.registerEvents(new Knife(this), this);
		pm.registerEvents(new Healing(), this);
		pm.registerEvents(new Swapper(this), this);
		pm.registerEvents(new EVENTRegeneration(), this);
		pm.registerEvents(new Energie(this), this);
		pm.registerEvents(new Tracker(), this);
		pm.registerEvents(new Spectator(), this);
		pm.registerEvents(new EVENTmap(), this);


		Bukkit.getWorlds().get(0).setDifficulty(Difficulty.NORMAL);

		Messages.setMessages();

		Worldboarder.ResetWorldboarder();

		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public int getHealth(Player p) {
        return (int)StrictMath.ceil(damageable(p).getHealth());
	}
        
	
	public Damageable damageable(Player p) {

	      return p;
	}

}
