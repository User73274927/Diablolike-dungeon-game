package com.samsung.game.items.weapon;

import com.samsung.game.engine.Damage;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.Item;

public abstract class Weapon extends Item {
    private Entity owner;
    private int require_level;

    private int damage_min;
    private int damage_max;


    public Weapon() {
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
    @Override
    public float getX() {
        return owner.getCenterX();
    }

    @Override
    public float getY() {
        return owner.getCenterY();
    }

    public abstract Entity getOwner();

    @Override
    public String toString() {
        return "damage: " + damage_min + "-" + damage_max + "\n" +
                "required level: " + require_level;
    }
}
