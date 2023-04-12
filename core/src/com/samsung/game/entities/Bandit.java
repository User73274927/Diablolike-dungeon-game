package com.samsung.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.items.weapon.FireWeapon;
import com.samsung.game.map.Map;
import com.samsung.game.map.Tile;

public class Bandit extends Enemy {
    private FireWeapon f;

    public Bandit(Map map, int x, int y) {
        super(map, x, y);
        f = new FireWeapon();
        f.setOwner(this);
        f.hit_chance = 0.2;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        f.shoot(0);
        f.draw(batch);
    }

    @Override
    public void onSpawn() {
        health = 100;
    }

    @Override
    public void update() {

    }

}
