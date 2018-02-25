package me.bypixels.thehunter.listeners;


import me.bypixels.thehunter.gamestates.GameState;
import me.bypixels.thehunter.gamestates.GameStateHandler;
import me.bypixels.thehunter.gamestates.IngameState;
import me.bypixels.thehunter.gamestates.LobbyState;
import me.bypixels.thehunter.util.Messages;

import java.io.File;
import java.io.IOException;

import me.bypixels.thehunter.util.Scoreboard;
import me.bypixels.thehunter.util.Settings;
import me.bypixels.thehunter.util.StatsSystem;
import me.bypixels.thehunter.util.special.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;


public class EVENTquit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        File file1 = StatsSystem.file;
        YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);
        if (Settings.editmode == false) {
            e.setQuitMessage(null);
            Player p = e.getPlayer();

            if (GameStateHandler.getCurrentState() instanceof LobbyState) {

                LobbyState ls = (LobbyState) GameStateHandler.getCurrentState();

                try {

                    if (Variables.playing.contains(p)) {

                        Variables.spectating.add(p);
                        Variables.playing.remove(p);
                        Variables.plays.remove(p);
                        GameState.checkWinning();
                        YamlConfiguration cfg = Messages.cfg;
                        String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Game_Quit").replace("%max_players%", Integer.toString(Settings.cfg.getInt("Max_Players")))).replace("%player%", p.getDisplayName()).replace("%playing%", Integer.toString(Variables.playing.size()));
                        String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                        GameState.checkWinning();
                        for (Player all : Bukkit.getOnlinePlayers())
                            all.sendMessage(prefix + msg);
                        GameState.checkWinning();
                        for (Player a : Bukkit.getOnlinePlayers()) {
                            Scoreboard.setScoreboard(a);
                        }

                    } else {
                        Variables.spectating.add(p);
                        for (Player a : Bukkit.getOnlinePlayers()) {
                            Scoreboard.setScoreboard(a);
                        }
                        GameState.checkWinning();
                    }
                } catch (Exception exe) {
                    // TODO: handle exception
                }


                if (Variables.playing.size() < LobbyState.MIN_PLAYERS) {
                    if (ls.getCountdown().isRunning) {
                        ls.getCountdown().stopCountDown();
                        ls.getCountdown().idle();

                    } else {

                    }

                } else {

                }
            } else if (GameStateHandler.getCurrentState() instanceof IngameState){

                if (Variables.playing.contains(p)) {
                    //player is a player in the ingamestate

                    int kills = StatsSystem.cfg.getInt(p.getName() + ".Kills");
                    int death = StatsSystem.cfg.getInt(p.getName() + ".Deaths") + 1;
                    int points = StatsSystem.cfg.getInt(p.getName() + ".Points") - 5;

                    StatsSystem.cfg.set(p.getName() + ".Points", points);
                    StatsSystem.cfg.set(p.getName() + ".Deaths", death);
                    StatsSystem.cfg.set(p.getName() + ".Kills", kills);

                    try {
                        cfg1.save(file1);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }


                    p.getWorld().strikeLightning(p.getLocation());
                    Block blockchest = p.getWorld().getBlockAt(p.getLocation().add(0, 0.5, 0));
                    blockchest.setType(Material.TRAPPED_CHEST);

                    Inventory inv = Bukkit.createInventory(null, 36,
                            ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§6DeathChest");
                    inv.setContents(p.getInventory().getContents());
                    EVENTdeath.DeathChest.put(blockchest, inv);

                    p.getInventory().clear();
                    p.updateInventory();
                    Variables.playing.remove(p);
                    Variables.plays.remove(p);
                    Variables.spectating.add(p);
                    YamlConfiguration cfg = Messages.cfg;
                    String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Ingame_Quit").replace("%player%", p.getDisplayName()));
                    String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                    GameState.checkWinning();
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.sendMessage(prefix + msg);

                    }
                    try {
                        for (Player a : Bukkit.getOnlinePlayers()) {
                            Scoreboard.setScoreboard(a);
                            if (Variables.playing.contains(a)) {
                                a.hidePlayer(p);
                            } else {
                                a.showPlayer(p);
                            }
                        }
                    } catch (Exception exe) {
                        exe.printStackTrace();
                    }

                } else {



                    //if Player is not a living Player in the ingamestate

                }
            }
        } else {

        }
    }


    @EventHandler
    public void shutdown(PlayerQuitEvent e) {
        if (Bukkit.getOnlinePlayers().equals(0)) {
            Bukkit.getServer().reload();

        } else {

        }
    }

}
