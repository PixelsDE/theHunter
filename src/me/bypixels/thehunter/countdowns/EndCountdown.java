package me.bypixels.thehunter.countdowns;


import me.bypixels.thehunter.main.Main;
import me.bypixels.thehunter.util.Messages;
import me.bypixels.thehunter.util.Settings;
import me.bypixels.thehunter.util.special.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;


public class EndCountdown extends me.bypixels.thehunter.countdowns.Countdown {

    public static boolean nobuild = false;
    private static Main plugin;
    private int taskID;
    private int seconds = 10;

    public EndCountdown(Main plugin) {
        EndCountdown.plugin = plugin;
    }

    @Override
    public void start() {
        if (Settings.cfg.getBoolean("Edit_Mode") == false) {
            nobuild = true;


            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {

                @Override
                public void run() {
                    YamlConfiguration cfg = Messages.cfg;
                    String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Game_Restart").replace("%seconds%", Integer.toString(seconds)));
                    String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                    switch (seconds) {
                        case 10:
                            Bukkit.broadcastMessage(prefix + msg);
                            for (Player a : Variables.playing)
                                a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
                            break;
                        case 5:
                            Bukkit.broadcastMessage(prefix + msg);
                            for (Player a : Variables.playing)
                                a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
                            break;
                        case 3:
                            Bukkit.broadcastMessage(prefix + msg);
                            for (Player a : Variables.playing)
                                a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
                            break;
                            case 2:
                            Bukkit.broadcastMessage(prefix + msg);
                            for (Player a : Variables.playing)
                                a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
                            break;
                        case 1:
                            Bukkit.broadcastMessage(prefix + msg);
                            for (Player a : Variables.playing)
                                a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
                            break;
                        case 0:
                            for (Player a : Bukkit.getOnlinePlayers()) {
                                YamlConfiguration cfg1 = Messages.cfg;
                                String msg1 = ChatColor.translateAlternateColorCodes('&', cfg1.getString("Kickmessage"));
                                String prefix1 = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));

                                for (Entity e : Bukkit.getWorld(a.getWorld().getName()).getEntities()) {
                                    if (e instanceof Item)
                                        e.remove();
                                }
                                Bukkit.getServer().unloadWorld(Settings.cfg.getString("World_Name"), false);
                                Bukkit.getServer().createWorld(new WorldCreator(Settings.cfg.getString("World_Name")));

                                Bukkit.getServer().reload();
                                Bukkit.reload();
                                Bukkit.getServer().shutdown();
                                Bukkit.shutdown();

                            }
                            break;
                        default:
                            break;
                    }
                    // TODO Auto-generated method stub
                    seconds--;
                }
            }, 0, 20);
            // TODO Auto-generated method stub

        } else {

        }
    }

    @Override
    public void stop() {
        if (Variables.cfg.getBoolean("Edit_Mode") == false) {
            nobuild = false;
            Bukkit.getScheduler().cancelTask(taskID);
            // TODO Auto-generated method stub

        }
    }

}


