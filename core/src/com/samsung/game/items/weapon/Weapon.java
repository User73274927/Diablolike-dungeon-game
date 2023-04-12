package com.samsung.game.items.weapon;

import com.badlogic.gdx.Gdx;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Item;

import java.util.Random;

public abstract class Weapon extends Item {
    protected Entity owner;
    protected Random hit_rand;

    private int damage_min;
    private int damage_max;

    private int require_level;
    public double hit_chance;

    public Weapon() {
        hit_rand = new Random();
        hit_chance = 1;
    }

    public void setDamageBounds(int min, int max) {
        this.damage_min = Math.min(min, max);
        this.damage_max = Math.max(min, max);
    }

    public void setRequireLevel(int level) {
        if (level <= Entity.MAX_LEVEL) {
            this.require_level = level;
        }
    }

    public int getDamage() {
        return (int) (damage_min + Math.random() * damage_max);
    }

    public boolean isHit() {
        double num = hit_rand.nextDouble();
        System.out.println(num);
        return num >= hit_chance;
    }

    public int getMinDamage() {
        return damage_min;
    }

    public int getMaxDamage() {
        return damage_max;
    }

    @Override
    public float getX() {
        if (owner == null) {
            visible = false;
            return -1;
        }
        return owner.getCenterX();
    }

    @Override
    public float getY() {
        if (owner == null) {
            visible = false;
            return -1;
        }
        return owner.getCenterY();
    }

    @Override
    public String info() {
        return "damage: " + damage_min + "-" + damage_max + " \n" +
                "require level: " + require_level + "\n" +
                "hit chance: " + (int) (hit_chance * 100) + "\n";
    }
}
