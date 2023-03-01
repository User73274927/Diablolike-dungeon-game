package com.samsung.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.samsung.game.engine.Colliable;
import com.samsung.game.engine.Component;
import com.samsung.game.map.Map;
import com.samsung.game.map.Tile;
import com.samsung.game.map.Wall;

import java.util.HashSet;

public abstract class Entity extends Thread implements Component, Colliable {
    public static final float MAX_SPEED = Tile.SIZE;
    public static final float MAX_RESISTANCE = 80;

    private float width, height;
    protected final Vector2 pos;
    private Tile current_collide_tile;
    private Rectangle hitbox;

    private int physical_resistance = 0;
    private int fire_resistance = 0;
    private int cold_resistance = 0;

    protected Direction direction;
    protected int health;
    private float speed;
    protected int level;

    public enum Direction {
        STOP, UP, DOWN, LEFT, RIGHT;
    }

    public Entity(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.pos = new Vector2();
        pos.add(x, y);
        hitbox = new Rectangle(pos.x, pos.y, getWidth(), getHeight());
        Entity.add(this);
        onSpawn(x, y);
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

    public boolean checkWall(Map map) {
        Tile[][] tile_map = map.getTileMap();

        for (int i = 0; i < tile_map.length; i++) {
            for (int j = 0; j < tile_map[0].length; j++) {
                Tile tile = tile_map[i][j];

                if (tile instanceof Wall) {
                    if ((getHitbox().overlaps(((Wall) tile).getHitbox()))) {
                        current_collide_tile = tile;
                        return true;
                    }
                }
            }
        }
        current_collide_tile = null;
        return false;
    }

    public Vector2 getCenterPos() {
        float center_x = pos.x + width / 2f;
        float center_y = pos.y + height / 2f;
        return new Vector2(center_x, center_y);
    }

    public boolean isEntityAlive() {
        return health > 0;
    }

    public Tile getCurrentCollideTile() {
        return current_collide_tile;
    }

    @Override
    public Rectangle getHitbox() {
        hitbox.x = pos.x;
        hitbox.y = pos.y;
        return hitbox;
    }

    public Vector2 getPos() {
        return pos;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public boolean getDamage(float damage) {
        health -= damage;
        return true;
    }

    public abstract void onSpawn(float x, float y);
    public abstract void update();
    public abstract void onDie();



    // Entities data
    private static HashSet<Entity> all;
    private static HashSet<Thread> all_threads;

    static {
        all = new HashSet<>();
        all_threads = new HashSet<>();
    }

    public static void clear() {
        for (Entity entity : all) {
            entity.onDie();
        }
        for (Thread thread : all_threads) {
            thread.interrupt();
        }

        all.clear();
        all_threads.clear();
    }

    public static void add(Entity entity) {
        all_threads.add(entity);
        all.add(entity);
    }

    public static void remove(Entity entity) {
        all_threads.remove(entity);
        all.remove(entity);
    }

    public static HashSet<Entity> all() {
        return all;
    }

}
