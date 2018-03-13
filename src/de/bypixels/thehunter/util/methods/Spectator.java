package de.bypixels.thehunter.util.methods;

import java.util.ArrayList;

import net.minecraft.server.v1_12_R1.PacketPlayOutCamera;
import org.bukkit.Bukkit;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;




public class Spectator implements Listener {

	public static PacketPlayOutCamera camera = new PacketPlayOutCamera();
	public static ArrayList<Player> looking = new ArrayList<Player>();
    @SuppressWarnings("deprecation")
	@EventHandler
	public static void onClick(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			Player target = (Player) e.getEntity();
			if (Var.spectating.contains(p)) {
				if (Var.playing.contains(target)) {
					if (!looking.contains(p)) {
						looking.add(p);
						e.setCancelled(true);
						camera.a = target.getEntityId();
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(camera);
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (!Var.playing.contains(all)) {
								if (Var.spectating.contains(all)) {
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


    @SuppressWarnings("deprecation")
	@EventHandler
	public static void onShift(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		if (Var.spectating.contains(p)) {
			if (looking.contains(p)) {
				looking.remove(p);
				PacketPlayOutCamera camera = new PacketPlayOutCamera();
				camera.a = p.getEntityId();
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(camera);
				for (Player all : Bukkit.getOnlinePlayers()) {
					if (!Var.playing.contains(all)) {
						if (Var.spectating.contains(all)) {
							all.showPlayer(p);
						}

					}
				}
			}

		}
	}

}
