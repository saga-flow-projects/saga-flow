package com.demosoft.investiogation.neuronlan.entity;

/**
 * Created by Andrii_Korkoshko on 30.11.2015.
 */
public class PlayerStateRule {

    public enum Action {
        ATTACK(0), ESCAPE(1), HIDE(2), NONE(3), DEFENCE(4);
        private int code;

        Action(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public static Action getByCode(int code) {
            for (Action action : Action.values()) {
                if (action.getCode() == code) {
                    return action;
                }
            }
            return null;
        }
    }

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

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
