package com.samsung.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.samsung.game.engine.Colliable;
import com.samsung.game.map.Map;
import com.samsung.game.map.Tile;
import com.samsung.game.map.Wall;

public abstract class PhysicalEntity extends Entity implements Colliable {

    public PhysicalEntity(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void detectCollision(Map map) {
        if (checkWall(map)) {
            Wall collide_tile = (Wall) getCurrentCollideTile();

            switch (direction) {
                case UP:
                    pos.y += -(pos.y + getHeight() - collide_tile.y);
                    break;
                case DOWN:
                    pos.y += (collide_tile.y + Tile.SIZE - pos.y);
                    break;
                case LEFT:
                    pos.x += (collide_tile.x + Tile.SIZE - pos.x);
                    break;
                case RIGHT:
                    pos.x += -(pos.x + getWidth() - collide_tile.x);
                    break;
            }
        }
    }
}
