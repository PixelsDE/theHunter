package de.bypixels.thehunter.api;
// Made by PixelsDE 
// Project: Hunting
// Copyright PixelsDE
// youtube.com/bypixels
// Date: 06.03.2018
// Package: de.bypixels.thehunter.api
// Created by: Daniel

import de.bypixels.thehunter.util.StatsSystem;
import de.bypixels.thehunter.util.special.Variables;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class API {


    public static int kills;
    public static int ammoInInventory;
    public static int StatsKills;
    public static int StatsDeaths;
    public static int StatsGames;
    private static Player player;

    private static File file = StatsSystem.file;
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);


    public API( ) {

    }




    public static int getKills(Player player) {
        return kills;
    }

    public static void setKills(int kills, Player player) {
        Variables.cfg.set(player.getName()+".Kills", kills);
    }

    public static int getAmmoInInventory(Player player) {
        return ammoInInventory;
    }

    public static void setAmmoInInventory(int ammoInInventory) {
        API.ammoInInventory = ammoInInventory;
    }

    public static int getStatsKills(Player player) {
        StatsKills = cfg.getInt(player.getName()+".Kills");
        return StatsKills;
    }

    public static void setStatsKills(int statsKills, Player player) {
       cfg.set(player.getName()+".Kills", statsKills);

    }

    public static int getStatsDeaths(Player player) {
        StatsDeaths = cfg.getInt(player.getName()+".Deaths");
        return StatsDeaths;
    }

    public static void setStatsDeaths(int statsDeaths, Player player) {
        cfg.set(player.getName()+".Kills", statsDeaths);
    }



}
