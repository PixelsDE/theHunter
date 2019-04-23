package de.bypixels.thehunter.main;


import de.bypixels.thehunter.chestitems.*;

import de.bypixels.thehunter.commands.*;
import de.bypixels.thehunter.listeners.*;
import de.bypixels.thehunter.guns.AK;
import de.bypixels.thehunter.guns.Minigun;
import de.bypixels.thehunter.guns.Pistol;
import de.bypixels.thehunter.guns.Sniper;
import de.bypixels.thehunter.util.*;
import de.bypixels.thehunter.util.special.Metrics;

import de.bypixels.thehunter.gamestates.GameState;
import de.bypixels.thehunter.gamestates.GameStateHandler;

import java.io.IOException;

import de.bypixels.thehunter.util.special.Autoupdater;
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

public class theHunterMain extends JavaPlugin implements Listener{

	public static theHunterMain plugin;

	public static String prefix;
	public static theHunterMain getPlugin() {
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

        prefix = Settings.cfg.getString("Prefix");
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
		this.getCommand("setarenacenter").setExecutor(new CMDWorldcenter());
		this.getCommand("stats").setExecutor(new CMDstats());

		PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(new EVENTjoin(this), this);
		pm.registerEvents(new EVENTquit(), this);
		pm.registerEvents(new Scoreboard(this), this);
		pm.registerEvents(new EVENTdeath(this), this);
		pm.registerEvents(new Sniper(this), this);
		pm.registerEvents(new Pistol(this), this);
		pm.registerEvents(new AK(this), this);
		pm.registerEvents(new Minigun(this), this);
		pm.registerEvents(new MainChest(this), this);
		pm.registerEvents(new HackerEye(this), this);
		pm.registerEvents(new Knife(this), this);
		pm.registerEvents(new Healing(this), this);
		pm.registerEvents(new Swapper(this), this);
		pm.registerEvents(new EVENTRegeneration(this), this);
		pm.registerEvents(new EnergieDrink(this), this);
		pm.registerEvents(new Tracker(this), this);
		pm.registerEvents(new EVENTSpectator(this), this);
		pm.registerEvents(new EVENTmap(this), this);
		pm.registerEvents(new EVENTchestopen(this), this);


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

	      return (Damageable) p;
	}

}
