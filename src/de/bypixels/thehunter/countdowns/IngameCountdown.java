package de.bypixels.thehunter.countdowns;



import de.bypixels.thehunter.main.theHunterMain;
import de.bypixels.thehunter.util.Messages;
import de.bypixels.thehunter.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


public class IngameCountdown {

	private theHunterMain plugin;

	public IngameCountdown(theHunterMain plugin) {
		this.plugin = plugin;
	}

	private static int timer = Settings.cfg.getInt("Game_Countdown");

	public static int startFinalCountdown = 0;

	private static int TaskID;


	@SuppressWarnings("deprecation")
	public static void FinalCountDown(){
		if (startFinalCountdown == 0) {
			TaskID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(theHunterMain.getPlugin(), new Runnable() {


				@Override
				public void run() {

                    if (startFinalCountdown <= timer) {
                    YamlConfiguration cfg = Messages.cfg;
                    String msg = ChatColor.translateAlternateColorCodes('&',
                            cfg.getString("Game_Shutdown"));

                    String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));

                    for (Player all : Bukkit.getOnlinePlayers()) {

                        all.sendMessage(prefix + msg);
                    }

                    Bukkit.getServer().unloadWorld(Settings.cfg.getString("World_Name"), false);
                    Bukkit.getServer().createWorld(new WorldCreator(Settings.cfg.getString("World_Name")));

                    Bukkit.getServer().reload();
                    Bukkit.reload();

                    Bukkit.getScheduler().cancelTask(TaskID);
                }
					startFinalCountdown++;

				}
			}, 5, 5);

		}
	}

}
