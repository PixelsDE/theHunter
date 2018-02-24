package me.bypixels.thehunter.enums;

// Project: Hunting 
// Package: me.bypixels.thehunter.enums 
// Made by PixelsDE 
// Date: 24.02.2018 
// Copyright PixelsDE 

import me.bypixels.thehunter.main.Main;
import org.bukkit.Material;

import java.util.ArrayList;

public enum Guns {

    AK(Main.prefix +"§fAK", 3, 1, Material.MAGMA_CREAM),
    MINIGUN(Main.prefix +"§fMinigun", 1, 1, Material.SNOW_BALL),
    PISTOL(Main.prefix +"§fPistol", 4, 3, Material.FIREBALL),
    SNIPER(Main.prefix +"", 5, 4, Material.ENDER_PEARL);


    String name;
    int damage;
    int delay;
    Material ammomaterial;


    Guns(String name, int damage, int delay, Material ammomaterial) {
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
