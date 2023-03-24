package com.samsung.game.engine;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface Component {
    public void draw(Batch batch);
    public float getX();
    public float getY();
}


