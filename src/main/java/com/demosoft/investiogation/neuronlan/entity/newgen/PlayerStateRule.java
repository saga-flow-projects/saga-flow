package com.demosoft.investiogation.neuronlan.entity.newgen;

import com.demosoft.investiogation.neuronlan.entity.StateRule;

import java.util.List;

/**
 * Created by Andrii_Korkoshko on 05.12.2015.
 */
public class PlayerStateRule implements StateRule {


    private double health;
    private double armor;
    private boolean gun;
    private int enemies;
    private Action action;

    public PlayerStateRule(double health, boolean gun, int enemies, double armor, Action action) {
        this.health = health / 100;
        this.gun = gun;
        this.enemies = enemies;
        this.armor = armor;
        this.action = action;
    }

    public PlayerStateRule(double health, boolean gun, int enemies, double armor) {
        this.health = health / 100;
        this.gun = gun;
        this.enemies = enemies;
        this.armor = armor;
    }

    public PlayerStateRule() {
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

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public Object getValue(String key) {
        return null;
    }

    @Override
    public List<String> getKeys() {
        return null;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
