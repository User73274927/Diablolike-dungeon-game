package com.samsung.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.engine.LevelData;
import com.samsung.game.items.weapon.FireWeapon;

public class Bandit extends Enemy {
    private FireWeapon f;

    public Bandit(LevelData data, int x, int y) {
        super(data, x, y);
        f = new FireWeapon(data.allEntity);
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
