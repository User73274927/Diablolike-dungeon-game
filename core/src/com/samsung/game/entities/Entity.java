package com.samsung.game.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.samsung.game.engine.Collideable;
import com.samsung.game.engine.Drawable;
import com.samsung.game.map.Map;
import com.samsung.game.map.Tile;
import com.samsung.game.map.Wall;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Entity extends Thread implements Collideable {
    public static final int MAX_LEVEL = 100;
    public static final float MAX_SPEED = Tile.SIZE;
    public static final float MAX_RESISTANCE = 80;
    public static int MAX_HEALTH = 100;

    public final int local_id;
    private HashMap<String, Animation<TextureRegion>> animation;
    private Tile current_collide_tile;
    protected TextureRegion current_frame;
    protected Vector2 pos;
    private int width, height;
    private View view;

    private int physical_resistance = 0;
    private int fire_resistance = 0;
    private int cold_resistance = 0;

    protected Direction direction;
    protected Integer health;
    private float speed;


    public enum Direction {
        STOP, UP, DOWN, LEFT, RIGHT
    }

    public abstract class View implements Drawable {

        @Override
        public float getX() {
            return pos.x;
        }

        @Override
        public float getY() {
            return pos.y;
        }

    }

    public Entity(float x, float y) {
        Entity.add(this);
        local_id = Entity.all.size();

        pos = new Vector2(x, y);
        direction = Direction.STOP;
        width = Tile.SIZE;
        height = Tile.SIZE;
    }

    public Entity() {
        this(0, 0);
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
                    if (overlaps((Wall) tile)) {
                        current_collide_tile = tile;
                        return true;
                    }
                }
            }
        }
        current_collide_tile = null;
        return false;
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

    public void getDamage(int damage) {
        health -= damage;
    }

    public void addHealth(int points) {
        if (health + points < MAX_HEALTH) {
            this.health += points;
        } else {
            health = MAX_HEALTH;
        }
    }

    public float getCenterX() {
        return pos.x + width / 2f;
    }

    public float getCenterY() {
        return pos.y + height / 2f;
    }

    public boolean isEntityAlive() {
        return health > 0;
    }

    public Tile getCurrentCollideTile() {
        return current_collide_tile;
    }
    public Integer getHealth() {
        return health;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public abstract void onSpawn();
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
//      for (Entity entity : all) {
//          entity.onDie();
//      }
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
