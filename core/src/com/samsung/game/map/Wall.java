package com.samsung.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.samsung.game.engine.Colliable;

public class Wall extends Tile implements Colliable {
    private Rectangle hitbox;

    public Wall(Texture texture) {
        super(texture);
        hitbox = new Rectangle(x, y, Tile.SIZE, Tile.SIZE);
    }

    @Override
    public Rectangle getHitbox() {
        hitbox.x = x;
        hitbox.y = y;
        return hitbox;
    }
}
