package me.bypixels.thehunter.listeners;

// Project: Hunting 
// Package: me.bypixels.thehunter.listeners 
// Made by PixelsDE 
// Date: 24.02.2018 
// Copyright PixelsDE 

import me.bypixels.thehunter.gamestates.EndState;
import me.bypixels.thehunter.gamestates.GameState;
import me.bypixels.thehunter.gamestates.GameStateHandler;
import me.bypixels.thehunter.gamestates.LobbyState;
import me.bypixels.thehunter.main.Main;
import me.bypixels.thehunter.util.Messages;
import me.bypixels.thehunter.util.special.Variables;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class EVENTchestopen implements Listener{

    @EventHandler
    public void onChestOpen(PlayerInteractEvent e){

        Player p = e.getPlayer();
        if (GameStateHandler.getCurrentState() instanceof LobbyState || GameStateHandler.getCurrentState() instanceof EndState || Variables.spectating.contains(p)) {
                   if (e.getClickedBlock() instanceof Block) {
                    e.setCancelled(true);

                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.prefix + Messages.cfg.getString("Not_Interact").replace("%object%", e.getClickedBlock().getType().name())));
                }
            }
        }



    private Main plugin;

    public EVENTchestopen(Main plugin) {
        this.plugin = plugin;
    }
}
