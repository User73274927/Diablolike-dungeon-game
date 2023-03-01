package com.samsung.game.items.weapon;

import com.samsung.game.engine.Colliable;
import com.samsung.game.engine.Damage;

public abstract class Shell implements Damage, Colliable, Cloneable {
    private int damage_min, damage_max;

    public Shell() {

    }

    public abstract Shell clone();
}
