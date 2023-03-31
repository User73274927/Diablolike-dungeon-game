package com.samsung.game.items.weapon;

import com.samsung.game.engine.Damage;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Equipable;
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
        if (min <= max) {
            this.damage_min = min;
            this.damage_max = max;
        } else {
            System.out.println(getClass().getName() + ": incorrect damage arguments");
        }
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
        return hit_rand.nextDouble() <= hit_chance;
    }

    @Override
    public float getX() {
        if (owner == null) {
            return 0;
        }
        return owner.getCenterX();
    }

    @Override
    public float getY() {
        if (owner == null) {
            return 0;
        }
        return owner.getCenterY();
    }

    public abstract Entity getOwner();

    @Override
    public String toString() {
        return "damage: " + damage_min + "-" + damage_max + " \n" +
                "required level: " + require_level +
                "hit chance (%): " + hit_chance * 100;
    }
}
