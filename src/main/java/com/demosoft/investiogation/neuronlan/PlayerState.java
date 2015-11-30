package com.demosoft.investiogation.neuronlan;

/**
 * Created by Andrii_Korkoshko on 30.11.2015.
 */
public class PlayerState {
    private double health;
    private double armor;
    private boolean gun;
    private int enemies;

    public PlayerState(double health, boolean gun, int enemies, double armor) {
        this.health = health / 100;
        this.gun = gun;
        this.enemies = enemies;
        this.armor = armor;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public boolean isGun() {
        return gun;
    }

    public int getGun() {
        return isGun() ? 1 : 0;
    }


    public void setGun(boolean gun) {
        this.gun = gun;
    }

    public int getEnemies() {
        return enemies;
    }

    public void setEnemies(int enemies) {
        this.enemies = enemies;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }
}
