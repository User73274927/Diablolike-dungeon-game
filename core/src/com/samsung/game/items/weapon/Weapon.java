package com.samsung.game.items.weapon;

import com.samsung.game.items.Item;
import com.samsung.game.engine.Damage;

public abstract class Weapon extends Item implements Damage {
    protected float[] damage_bound;
}
