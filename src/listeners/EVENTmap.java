package listeners;
// Made by PixelsDE /

// Minecraft-Developer /
// Copyright PixelsDE /
// youtube.com/bypixels /

import util.Settings;
import util.Messages;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.ArrayList;

public class EVENTmap implements Listener {

	public static ArrayList<Block> playerblock = new ArrayList<>();

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {

			Player p = e.getPlayer();
			e.setCancelled(true);
			p.updateInventory();
		} else {

		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (Settings.cfg.getBoolean("Edit_Mode") == false) {
				if (Settings.cfg.getBoolean("World_Build") == false){
					e.setCancelled(true);
				YamlConfiguration cfg = Messages.cfg;
				String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Map_Break"));
				String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
				e.getPlayer().sendMessage(prefix + msg.replace("%block%", e.getBlock().getType().name()));
				Player p = e.getPlayer();
				p.updateInventory();

			}
		}

	}

	@EventHandler
	public void onPing(ServerListPingEvent event) {

		event.setMaxPlayers(Settings.cfg.getInt("Max_Players"));
	}
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		if (e.getEntity() instanceof Player) {
			e.setCancelled(false);
			
		}else {
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getDamager() instanceof Player) {
			}else {
				Player p  = (Player) e.getEntity();
				Entity a = e.getEntity();
				e.setCancelled(true);
			}
		}
	}
}
