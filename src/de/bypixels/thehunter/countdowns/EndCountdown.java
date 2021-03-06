package de.bypixels.thehunter.countdowns;


import de.bypixels.thehunter.main.theHunterMain;
import de.bypixels.thehunter.util.Settings;
import de.bypixels.thehunter.util.special.Variables;
import de.bypixels.thehunter.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;


public class EndCountdown extends Countdown {

    public static boolean nobuild = false;
    private static theHunterMain plugin;
    private int taskID;
    private int seconds = 10;

    public EndCountdown(theHunterMain plugin) {
        EndCountdown.plugin = plugin;
    }

    @Override
    public void start() {
        if (Settings.cfg.getBoolean("Edit_Mode") == false) {
            nobuild = true;




            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(theHunterMain.getPlugin(), new Runnable() {

                @Override
                public void run() {
                    YamlConfiguration cfg = Messages.cfg;
                    String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Game_Restart").replace("%seconds%", Integer.toString(seconds)));
                    String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                    switch (seconds) {
                        case 10:
                            for (Player a : Bukkit.getOnlinePlayers()) {
                                a.playSound(a.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
                                a.sendMessage(prefix + msg);
                            }
                            break;
                        case 5:
                            for (Player a : Bukkit.getOnlinePlayers()) {
                                a.playSound(a.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
                                a.sendMessage(prefix + msg);
                            }
                            break;
                        case 3:
                            for (Player a : Bukkit.getOnlinePlayers()) {
                                a.playSound(a.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
                                a.sendMessage(prefix + msg);
                            }
                            break;
                            case 2:

                                for (Player a : Bukkit.getOnlinePlayers()) {
                                    a.playSound(a.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
                                    a.sendMessage(prefix + msg);
                                }
                            break;
                        case 1:

                            for (Player a : Bukkit.getOnlinePlayers()) {
                                a.playSound(a.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
                                a.sendMessage(prefix + msg);
                            }

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


                                Bukkit.getServer().savePlayers();
                                Bukkit.getServer().reload();
                                Bukkit.reload();


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


