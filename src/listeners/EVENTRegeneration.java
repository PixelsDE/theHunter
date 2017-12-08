package listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import util.Settings;
import util.special.Var;

public class EVENTRegeneration implements Listener {

	@EventHandler
	public static void regen(EntityRegainHealthEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (Var.playing.contains(p)) {
				if (Settings.cfg.getBoolean("Ingame_Regeneration") == false) {
					e.setCancelled(true);
				} else {
					e.setCancelled(false);
				}
			}

		}
	}

}
