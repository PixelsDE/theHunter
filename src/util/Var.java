package Util;

import java.io.File;
import java.util.ArrayList;


import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Var {


    public static ArrayList<Player> playing = new ArrayList<>();
    public static ArrayList<Player> spectating = new ArrayList<>();

    public static File cfgFile = new File("plugins/theHunter/locations.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfgFile);





    public static ArrayList<Player> plays = new ArrayList<>();


}


