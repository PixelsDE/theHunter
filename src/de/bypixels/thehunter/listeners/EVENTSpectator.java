package de.bypixels.thehunter.listeners;

import java.util.ArrayList;

import de.bypixels.thehunter.main.Main;
import de.bypixels.thehunter.util.special.Variables;
import net.minecraft.server.v1_12_R1.PacketPlayOutCamera;
import org.bukkit.Bukkit;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;




public class EVENTSpectator implements Listener {
    /*     */ private org.bukkit.craftbukkit.Main plugin;
    public EVENTSpectator(Main pluign)
        /*     */ {
        /*  31 */
        this.plugin = plugin;
        /*     */
    }

	public static ArrayList<Player> looking = new ArrayList<Player>();


    @SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(EntityDamageByEntityEvent e) {
        PacketPlayOutCamera camera = new PacketPlayOutCamera();
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


    @SuppressWarnings("deprecation")
	@EventHandler
	public void onShift(PlayerToggleSneakEvent e) {
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
