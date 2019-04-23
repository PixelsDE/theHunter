package de.bypixels.thehunter.enums;

// Project: Hunting 
// Package: me.bypixels.thehunter.enums 
// Made by PixelsDE 
// Date: 24.02.2018 
// Copyright PixelsDE 

import de.bypixels.thehunter.main.theHunterMain;
import org.bukkit.Material;

public enum Weapons {

    AK(theHunterMain.prefix +"§fAK", 3, 1, Material.MAGMA_CREAM),
    MINIGUN(theHunterMain.prefix +"§fMinigun", 1, 1, Material.SNOWBALL),
    PISTOL(theHunterMain.prefix +"§fPistol", 4, 3, Material.LEGACY_FIREBALL),
    SNIPER(theHunterMain.prefix +"§fSniper", 5, 4, Material.ENDER_PEARL),
    KNIFE(theHunterMain.prefix +"§fKnife", 1, 0, Material.WOODEN_SWORD);

    String name;
    int damage;
    int delay;
    Material ammomaterial;


    Weapons(String name, int damage, int delay, Material ammomaterial) {
        this.name = name;
        this.damage = damage;
        this.delay = delay;
        this.ammomaterial = ammomaterial;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Material getammomaterial() {
        return ammomaterial;
    }

    public void setammomaterial(Material ammomaterial) {
        this.ammomaterial = ammomaterial;
    }
}
