package com.samsung.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.engine.Component;

public abstract class Tile implements Component {
    public static final int SIZE = 30;
    private Texture texture;
    public int x, y;

    public Tile(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, x, y, SIZE, SIZE);
    }

    public Texture getTexture() {
        return texture;
    }

}
