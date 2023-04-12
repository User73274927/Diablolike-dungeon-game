package com.samsung.game.items.weapon.coldWeapon;

import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.weapon.ColdWeapon;

public class Sword extends ColdWeapon {

    @Override
    public void onTouch(float screen_x, float screen_y) {
        super.onTouch(screen_x, screen_y);
    }

    @Override
    public void setOwner(Entity owner) {
        this.owner = owner;
        setDamageBounds(30, 50);
    }
}
