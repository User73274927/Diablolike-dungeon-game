package com.samsung.game.engine;

import com.badlogic.gdx.math.Rectangle;

public interface Colliable {
    Rectangle getHitbox();

    default boolean intersects(float x, float y) {
        return x > getHitbox().x && x < getHitbox().x + getHitbox().width &&
                y > getHitbox().y && y < getHitbox().y + getHitbox().height;
    }

    default boolean overlaps(Colliable colliable) {
        return getHitbox().overlaps(colliable.getHitbox());
    }
}
