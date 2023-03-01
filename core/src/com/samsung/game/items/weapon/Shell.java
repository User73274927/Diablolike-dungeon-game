package com.samsung.game.items.weapon;

import com.samsung.game.engine.Colliable;
import com.samsung.game.engine.Component;
import com.samsung.game.engine.Damage;

public abstract class Shell implements Component, Damage,
                                        Colliable, Cloneable {
    private int damage_min, damage_max;

    public Shell(int damage_min, int damage_max) {
        this.damage_min = damage_min;
        this.damage_max = damage_max;
    }

    public abstract Shell clone();
}
