package com.samsung.game.items.weapon;

import com.samsung.game.items.Item;
import com.samsung.game.engine.Damage;

public abstract class Weapon extends Item implements Damage {
    private int damage_min, damage_max;

    public Weapon(int damage_min, int damage_max) {
        this.damage_max = damage_max;
        this.damage_min = damage_min;
    }

}
