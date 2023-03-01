package com.samsung.game.items.weapon;

import com.samsung.game.engine.Colliable;

public abstract class Shell extends Weapon implements Colliable, Cloneable {

    public Shell(float damage_min, float damage_max) {
        super(damage_min, damage_max);
    }

    public abstract Shell clone();
}
