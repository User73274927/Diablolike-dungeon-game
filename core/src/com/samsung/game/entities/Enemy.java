package com.samsung.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Enemy extends PhysicalEntity {
    private String name;
    private Texture current_frame;

    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
        pos.x = x;
        pos.y = y;
        current_frame = new Texture("tiles/player-example1.png");
    }

    @Override
    public void draw(Batch batch) {
        if (intersects(Gdx.input.getX(), Gdx.input.getY())) {

        }
        batch.draw(current_frame, pos.x, pos.y, getWidth(), getHeight());
    }

    public String getEnemyName() {
        return name;
    }

    public void setEnemyName(String name) {
        this.name = name;
    }

}
