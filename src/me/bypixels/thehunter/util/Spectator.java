package me.bypixels.thehunter.util;

import java.util.ArrayList;

import me.bypixels.thehunter.util.special.Variables;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;


import net.minecraft.server.v1_8_R3.PacketPlayOutCamera;

public class Spectator implements Listener {

	public static PacketPlayOutCamera camera = new PacketPlayOutCamera();
	public static ArrayList<Player> looking = new ArrayList<Player>();

	@EventHandler
	public static void onClick(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			Player target = (Player) e.getEntity();
			if (Variables.spectating.contains(p)) {
				if (Variables.playing.contains(target)) {
					if (!looking.contains(p)) {
						looking.add(p);
						e.setCancelled(true);
						camera.a = target.getEntityId();
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(camera);
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (!Variables.playing.contains(all)) {
								if (Variables.spectating.contains(all)) {
									all.hidePlayer(p);
								}

							}
						}

					}
				}
			}
			}else {
				
			}

		}
	}

	@EventHandler
	public static void onShift(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		if (Variables.spectating.contains(p)) {
			if (looking.contains(p)) {
				looking.remove(p);
				PacketPlayOutCamera camera = new PacketPlayOutCamera();
				camera.a = p.getEntityId();
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(camera);
				for (Player all : Bukkit.getOnlinePlayers()) {
					if (!Variables.playing.contains(all)) {
						if (Variables.spectating.contains(all)) {
							all.showPlayer(p);
						}

					}
				}
			}

		}
	}

}
