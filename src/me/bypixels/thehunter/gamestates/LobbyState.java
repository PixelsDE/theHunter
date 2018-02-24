package me.bypixels.thehunter.gamestates;

import me.bypixels.thehunter.main.Main;

import me.bypixels.thehunter.countdowns.LobbyCountdown;
import me.bypixels.thehunter.util.Messages;

import me.bypixels.thehunter.util.Settings;
import org.bukkit.Bukkit;


import org.bukkit.ChatColor;

import org.bukkit.configuration.file.YamlConfiguration;


public class LobbyState extends GameState {

    public static final int MIN_PLAYERS = Settings.cfg.getInt("Min_Players"), MAX_PLAYERS = Settings.cfg.getInt("Max_Players");

    private LobbyCountdown countdown;

    @Override
    public void init() {

        if (Settings.editmode == false) {
            // TODO Auto-generated method stub
            countdown = new LobbyCountdown(Main.getPlugin());

        } else {

        }
    }

    @Override
    public void end() {

        // TODO Auto-generated method stub

        YamlConfiguration cfg1 = Messages.cfg;
        String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Game_Start"));
        String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
        Bukkit.broadcastMessage(prefix1 + msg1);


    }

    public LobbyCountdown getCountdown() {

        return countdown;
    }

}
