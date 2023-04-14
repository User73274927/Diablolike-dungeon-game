package com.samsung.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.samsung.game.engine.Collideable;

public class Chest extends Tile implements Collideable {
    public Chest() {
        super(new Texture("E"));
    }

    @Override
    public float getWidth() {
        return Tile.SIZE;
    }

    @Override
    public float getHeight() {
        return Tile.SIZE;
    }
}
