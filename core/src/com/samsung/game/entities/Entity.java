package com.samsung.game.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.samsung.game.engine.Collideable;
import com.samsung.game.engine.LevelData;
import com.samsung.game.engine.gdx.ActorWrapper;
import com.samsung.game.map.Tile;
import com.samsung.game.map.Wall;

import java.util.HashMap;
import java.util.Set;

;

public abstract class Entity extends ActorWrapper implements Collideable, Runnable {
    public static final int MAX_LEVEL = 100;
    public static final float MAX_SPEED = Tile.SIZE;
    public static final float MAX_RESISTANCE = 80;
    public int MAX_HEALTH = 100;

    private HashMap<String, Animation<TextureRegion>> animation;
    private Collideable current_collide_tile;
    protected TextureRegion current_frame;
    protected Rectangle hitbox;
    protected int width, height;
    protected Vector2 pos;
    protected Vector2 velocity;
    public final Thread update_thread;
    protected final LevelData data;

    private int physical_resistance = 0;
    private int fire_resistance = 0;
    private int cold_resistance = 0;

    protected State state;
    protected Direction direction;
    protected Integer health;

    public enum State {
        ACTIVE, STAN
    }

    public enum Direction {
        STOP, UP, DOWN, LEFT, RIGHT
    }

    public Entity(LevelData data, float x, float y) {
        this.data = data;
        update_thread = new Thread(this);
        pos = new Vector2(x, y);
        velocity = new Vector2();

        state = State.ACTIVE;
        direction = Direction.STOP;
        width = 20;
        height = 20;
    }

    public Entity(LevelData data) {
        this(data, 0, 0);
    }

    @Override
    public void run() {
        while (isEntityAlive()) {
            update();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        onDie();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(current_frame, getX(), getY(), width, height);
        super.draw(batch, parentAlpha);
    }

    public boolean checkCollision() {
        Tile[][] tile_map = data.map.getTiledMap();
        Set<Entity> entitySet = data.allEntity;

        for (Entity entity : entitySet) {
            if (entity == this) continue;
            if (overlaps(entity)) {
                current_collide_tile = entity;
                return true;
            }
        }

        for (int i = 0; i < tile_map.length; i++) {
            for (int j = 0; j < tile_map[0].length; j++) {
                Tile tile = tile_map[i][j];

                if (tile instanceof Wall) {
                    if (overlaps((Wall) tile)) {
                        current_collide_tile = (Collideable) tile;
                        return true;
                    }
                }
            }
        }

        current_collide_tile = null;
        return false;
    }

    public void detectCollision() {
        if (checkCollision()) {
            Collideable collide_tile = getCurrentCollideTile();

            switch (direction) {
                case UP:
                    pos.y += -(pos.y + getHeight() - collide_tile.getY());
                    break;
                case DOWN:
                    pos.y += (collide_tile.getY() + Tile.SIZE - pos.y);
                    break;
                case LEFT:
                    pos.x += (collide_tile.getX() + Tile.SIZE - pos.x);
                    break;
                case RIGHT:
                    pos.x += -(pos.x + getWidth() - collide_tile.getX());
                    break;
            }
        }
    }

    public void putDamage(int damage) {
        health -= damage;
    }

    public void addHealth(int points) {
        health = Math.min(MAX_HEALTH, health + points);
    }

    public boolean isEntityAlive() {
        return health > 0;
    }

    public Collideable getCurrentCollideTile() {
        return current_collide_tile;
    }
    public Integer getHealth() {
        return health;
    }

    @Override
    public final float getWidth() {
        return width;
    }

    @Override
    public final float getHeight() {
        return height;
    }

    @Override
    public final float getX() {
        return pos.x;
    }

    @Override
    public final float getY() {
        return pos.y;
    }

    public abstract void onSpawn();
    public abstract void update();
    public abstract void onDie();

}
