package com.samsung.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.engine.Component;

public class Tile implements Component {
    private Texture texture;
    private boolean isCollision;

    public boolean hasCollision() {
        return isCollision;
    }

    @Override
    public void draw(Batch batch) {

    }
}
