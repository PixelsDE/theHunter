package de.bypixels.thehunter.listeners;

import de.bypixels.thehunter.main.Main;
import de.bypixels.thehunter.util.Settings;
import de.bypixels.thehunter.util.special.Variables;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;



public class EVENTRegeneration implements Listener {
    /*     */ private org.bukkit.craftbukkit.Main plugin;
    public EVENTRegeneration(Main pluign)
        /*     */ {
        /*  31 */
        this.plugin = plugin;
        /*     */
    }
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
