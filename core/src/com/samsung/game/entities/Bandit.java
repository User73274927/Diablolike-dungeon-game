package com.samsung.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.samsung.game.items.weapon.FireWeapon;
import com.samsung.game.items.weapon.Fireball;
import com.samsung.game.map.Tile;

public class Bandit extends Enemy {
    FireWeapon f;

    public Bandit(int x, int y, int width, int height) {
        super(x, y);
        setView(new BanditView());
        f = new FireWeapon();
        f.setOwner(this);
        f.hit_chance = 0.2;
    }

    public class BanditView extends EnemyView {
        @Override
        public void draw(Batch batch) {
            super.draw(batch);
            f.shoot(0);
            f.draw(batch);
        }
    }

    @Override
    public void onSpawn() {
        health = 100;
    }

    @Override
    public void update() {

    }

    @Override
    public float getX() {
        return pos.x;
    }

    @Override
    public float getY() {
        return pos.y;
    }

    @Override
    public int getWidth() {
        return Tile.SIZE;
    }

    @Override
    public int getHeight() {
        return Tile.SIZE;
    }

}
