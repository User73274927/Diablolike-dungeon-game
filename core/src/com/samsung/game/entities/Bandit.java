package com.samsung.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class Bandit extends Enemy {
    public Bandit(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void onSpawn(float x, float y) {
        health = 100;
    }

    @Override
    public void update() {
        System.out.println("health = " + health);

    }

    @Override
    public void onDie() {
        Entity.remove(this);
    }

}
