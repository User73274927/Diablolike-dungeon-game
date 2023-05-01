package com.samsung.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.items.weapon.FireWeapon;

public class Bandit extends Enemy {
    private FireWeapon f;
    private float angle;

    public Bandit(float x, float y) {
        super(x, y);
    }

    @Override
    public void onCreate() {
        f = new FireWeapon();
        f.setOwner(this);
        f.hit_chance = 0.2;
        health = 100;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        f.draw(batch, 0f);
    }

    @Override
    public void update() {
        super.update();
        f.shoot(angle += 0.01);
    }

}
