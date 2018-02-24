package me.bypixels.thehunter.listeners;

import me.bypixels.thehunter.util.Settings;
import me.bypixels.thehunter.util.special.Variables;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;



public class EVENTRegeneration implements Listener {

	@EventHandler
	public static void regen(EntityRegainHealthEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (Variables.playing.contains(p)) {
				if (Settings.cfg.getBoolean("Ingame_Regeneration") == false) {
					e.setCancelled(true);
				} else {
					e.setCancelled(false);
				}
			}

		}
	}

}
