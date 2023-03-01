package com.samsung.game.items.weapon;

import com.samsung.game.items.Item;
import com.samsung.game.engine.Damage;

public abstract class Weapon extends Item implements Damage {
    private float damage_min, damage_max;

    public Weapon(float damage_min, float damage_max) {
        this.damage_max = damage_max;
        this.damage_min = damage_min;
    }

}
