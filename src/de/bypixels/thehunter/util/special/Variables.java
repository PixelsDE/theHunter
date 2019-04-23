package de.bypixels.thehunter.util.special;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Variables {


    public static ArrayList<Player> playing = new ArrayList<>();
    public static ArrayList<Player> spectating = new ArrayList<>();

    public static File cfgFile = new File("plugins/theHunterMain/locations.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);


public static HashMap<Player, Integer> localkills = new HashMap<>();


    public static ArrayList<Player> plays = new ArrayList<>();
    public static boolean lobby = true;


}


