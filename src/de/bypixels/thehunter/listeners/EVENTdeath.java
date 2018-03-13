package de.bypixels.thehunter.listeners;

// Code by: PixelsDE /

// All Rights Reserved! /
// Website: https://www.spigotmc.org/resources/authors/pixelsde.403284/
// Youtube: byPixels /


import de.bypixels.thehunter.gamestates.GameState;
import de.bypixels.thehunter.main.Main;
import de.bypixels.thehunter.util.Messages;
import de.bypixels.thehunter.util.Scoreboard;
import de.bypixels.thehunter.util.Settings;
import de.bypixels.thehunter.util.StatsSystem;
import de.bypixels.thehunter.util.special.LocationCreator;
import de.bypixels.thehunter.util.special.Variables;

import net.minecraft.server.v1_12_R1.PacketPlayInClientCommand;
import net.minecraft.server.v1_12_R1.PacketPlayOutCamera;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class EVENTdeath implements Listener {
    public static String winner;
    public static HashMap<Block, Inventory> DeathChest = new HashMap<>();
    private Main plugin;

    public EVENTdeath(Main plugin) {
        this.plugin = plugin;
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);
        Player p = (Player) e.getEntity();
        Variables.playing.remove(p);
        Variables.spectating.add(p);

        for (Player all : Bukkit.getOnlinePlayers()) {
            if (EVENTSpectator.looking.contains(all)) {
                PacketPlayOutCamera camera = new PacketPlayOutCamera();
                camera.a = all.getEntityId();
                ((CraftPlayer) all).getHandle().playerConnection.sendPacket(camera);
            } else {

            }
        }
        Player killer = e.getEntity().getKiller();
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (Variables.playing.contains(all)) {
                all.hidePlayer(p);
                p.showPlayer(all);
            } else {
                all.showPlayer(p);
                p.showPlayer(all);
            }
        }
        if (Variables.playing.contains(p)) {
            File file1 = StatsSystem.file;
            YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);

            if (e.getEntity().getKiller() instanceof Player) {
                int kills = cfg1.getInt(p.getName() + ".Kills");
                int death = cfg1.getInt(p.getName() + ".Deaths") + 1;
                int points = cfg1.getInt(p.getName() + ".Points") - 5;

                int kills1 = cfg1.getInt(killer.getName() + ".Kills") + 1;
                int points1 = cfg1.getInt(killer.getName() + ".Points") + 10;
                int death1 = cfg1.getInt(killer.getName() + ".Deaths");

                Variables.localkills.put(killer, Variables.localkills.get(killer) + 1);
                cfg1.set(killer.getName() + ".Kills", kills1);
                cfg1.set(killer.getName() + ".Points", points1);
                cfg1.set(killer.getName() + ".Deaths", death1);
                cfg1.set(p.getName() + ".Points", points);
                cfg1.set(p.getName() + ".Deaths", death);
                cfg1.set(p.getName() + ".Kills", kills);
                try {
                    cfg1.save(file1);
                } catch (Exception e2) {
                    // TODO: handle exception
                }
            }

            int kills = cfg1.getInt(p.getName() + ".Kills");
            int death = cfg1.getInt(p.getName() + ".Deaths") + 1;
            int points = cfg1.getInt(p.getName() + ".Points") - 5;

            cfg1.set(p.getName() + ".Points", points);
            cfg1.set(p.getName() + ".Deaths", death);
            cfg1.set(p.getName() + ".Kills", kills);
            try {
                cfg1.save(file1);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        p.getWorld().strikeLightning(p.getLocation());
        Block blockchest = p.getWorld().getBlockAt(p.getLocation().add(0, 0.5, 0));
        blockchest.setType(Material.TRAPPED_CHEST);

        Inventory inv = Bukkit.createInventory(null, 36,
                ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§6DeathChest");
        inv.setContents(p.getInventory().getContents());
        DeathChest.put(blockchest, inv);
        e.getDrops().clear();
        p.getInventory().clear();
        Variables.playing.remove(p);
        Variables.spectating.add(p);
        p.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7"
                + p.getName() + "§f");
        p.setCustomName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7"
                + p.getName() + "§f");
        p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§7"
                + p.getName() + "§f");
        p.setCustomNameVisible(true);

        p.setGameMode(GameMode.SURVIVAL);
        p.setAllowFlight(true);
        p.getInventory().clear();
        ((CraftPlayer) p).getHandle().playerConnection
                .a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
        p.teleport(LocationCreator.getConfigLocation("Spawn.Spectator", Variables.cfg));
        Scoreboard.setScoreboard(p);
        GameState.checkWinning(killer);
        for (Player all : Bukkit.getOnlinePlayers())
            Scoreboard.setScoreboard(p);
        if (p.getKiller() != null) {
            GameState.checkWinning(killer);
            if (Variables.playing.size() == 1) {
                File file1 = StatsSystem.file;
                YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);
                winner = p.getKiller().getName();
                int wins = cfg1.getInt(winner + ".Points") + 1;
                cfg1.set(winner + ".Wins", wins);
                try {
                    cfg1.save(file1);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    GameState.checkWinning(killer);
                } catch (Exception e1) {

                }
            }
            try {
                GameState.checkWinning(killer);
            } catch (Exception e1) {

            }
        }
        if (p.getLastDamageCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            if (e.getEntity().getKiller() instanceof Player) {
                if (Variables.playing.contains(p)) {
                    File file1 = StatsSystem.file;
                    YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);

                    if (e.getEntity().getKiller() instanceof Player) {
                        int kills = cfg1.getInt(p.getName() + ".Kills");
                        int death = cfg1.getInt(p.getName() + ".Deaths") + 1;
                        int points = cfg1.getInt(p.getName() + ".Points") - 5;

                        int kills1 = cfg1.getInt(killer.getName() + ".Kills") + 1;
                        int points1 = cfg1.getInt(killer.getName() + ".Points") + 10;
                        int death1 = cfg1.getInt(killer.getName() + ".Deaths");

                        cfg1.set(killer.getName() + ".Kills", kills1);
                        cfg1.set(killer.getName() + ".Points", points1);
                        cfg1.set(killer.getName() + ".Deaths", death1);
                        Variables.localkills.put(killer, Variables.localkills.get(killer) + 1);
                        cfg1.set(p.getName() + ".Points", points);
                        cfg1.set(p.getName() + ".Deaths", death);
                        cfg1.set(p.getName() + ".Kills", kills);
                        try {
                            cfg1.save(file1);
                        } catch (Exception e2) {
                            // TODO: handle exception
                        }
                    }
                    int kills = cfg1.getInt(p.getName() + ".Kills");
                    int death = cfg1.getInt(p.getName() + ".Deaths") + 1;
                    int points = cfg1.getInt(p.getName() + ".Points") - 5;

                    cfg1.set(p.getName() + ".Points", points);
                    cfg1.set(p.getName() + ".Deaths", death);
                    cfg1.set(p.getName() + ".Kills", kills);
                    try {
                        cfg1.save(file1);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    try {
                        GameState.checkWinning(killer);
                    } catch (Exception e1) {

                    }
                    if (Variables.playing.size() == 1) {
                        GameState.checkWinning(killer);
                        winner = p.getKiller().getName();
                        int wins = cfg1.getInt(winner + ".Points") + 1;
                        cfg1.set(winner + ".Wins", wins);
                        try {
                            cfg1.save(file1);
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        try {
                            GameState.checkWinning(killer);
                        } catch (Exception e1) {

                        }
                    }
                    try {
                        GameState.checkWinning(killer);
                    } catch (Exception e1) {

                    }
                }

                try {
                    GameState.checkWinning(killer);
                } catch (Exception e1) {


                }
                YamlConfiguration cfg = Messages.cfg;
                String msg = ChatColor.translateAlternateColorCodes('&',
                        cfg.getString("Death_Message").replace("%player%", p.getName()).replace("%killer%",
                                "§6" + e.getEntity().getKiller().getName()));
                String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.sendMessage(prefix + msg);
                    all.playSound(all.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
                }

            } else {
                YamlConfiguration cfg = Messages.cfg;
                String msg = ChatColor.translateAlternateColorCodes('&',
                        cfg.getString("Deathmessage").replace("%player%", "§6" + p.getName()));
                String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.sendMessage(prefix + msg);
                    all.playSound(all.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
                }
            }
        } else {
            YamlConfiguration cfg = Messages.cfg;
            String msg = ChatColor.translateAlternateColorCodes('&',
                    cfg.getString("Deathmessage").replace("%player%", "§6" + p.getName()));
            String prefix = ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix"));
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage(prefix + msg);
                all.playSound(all.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
            }
        }
    }

    @EventHandler
    public void onOpen(PlayerInteractEvent e) {
        if (Variables.playing.contains(e.getPlayer())) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getClickedBlock().getType() == Material.TRAPPED_CHEST) {
                    Block block = e.getClickedBlock();
                    for (Block blocks : DeathChest.keySet()) {
                        if (blocks.getLocation().equals(block.getLocation())) {
                            e.setCancelled(true);
                            e.getPlayer().openInventory(DeathChest.get(blocks));
                        }
                    }
                }
            }
        }
    }

}
