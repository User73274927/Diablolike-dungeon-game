package com.samsung.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.samsung.game.engine.Collideable;

public class Wall extends Tile implements Collideable {
    private Rectangle hitbox;

    public Wall(Texture texture) {
        super(texture);
        hitbox = new Rectangle(x, y, Tile.SIZE, Tile.SIZE);
    }

    @Override
    public int getWidth() {
        return (int) hitbox.width;
    }

    @Override
    public int getHeight() {
        return (int) hitbox.height;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
}
