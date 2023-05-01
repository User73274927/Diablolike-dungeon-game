package com.samsung.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.samsung.game.engine.Collideable;
import com.samsung.game.engine.Lifecycle;
import com.samsung.game.engine.RigidBody;
import com.samsung.game.engine.gdx.ActorWrapper;
import com.samsung.game.map.Tile;

import java.util.HashMap;

public abstract class Entity extends ActorWrapper implements Collideable, Lifecycle {
    public static final int MAX_LEVEL = 100;
    public static final float MAX_SPEED = Tile.SIZE;
    public static final float MAX_RESISTANCE = 80;
    public int MAX_HEALTH = 100;

    protected HashMap<String, Animation<TextureRegion>> walkAnimationDict;
    protected Animation<TextureRegion> current_animation;
    private float time;

    protected TextureRegion current_frame;
    protected RigidBody body;

    protected State state;
    protected Integer health = 1;

    public enum State {
        ACTIVE, STAN
    }

    public Entity(float x, float y) {
        walkAnimationDict = new HashMap<>();
        body = new RigidBody(x, y);

        state = State.ACTIVE;
        body.width = 20;
        body.height = 20;
    }

    public Entity() {
        this(0, 0);
    }

    @Override
    public void update() {
        time += Gdx.graphics.getDeltaTime();
        if (current_animation != null) {
            current_frame = current_animation.getKeyFrame(time, true);
        }

        if (!isEntityAlive()) {
            onDestroy();
        }
        body.update();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(current_frame, getX(), getY(), body.width, body.height);
        super.draw(batch, parentAlpha);
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

    public Integer getHealth() {
        return health;
    }

    public RigidBody getBody() {
        return body;
    }

    @Override
    public final float getWidth() {
        return body.width;
    }

    @Override
    public final float getHeight() {
        return body.height;
    }

    public void setLocation(float x, float y) {
        setX(x); setY(y);
    }

    public float getTime() {
        return time;
    }

    @Override
    public void setY(float y) {
        body.setPosY(y);
    }

    @Override
    public void setX(float x) {
        body.setPosX(x);
    }

    @Override
    public final float getX() {
        return body.getX();
    }

    @Override
    public final float getY() {
        return body.getY();
    }

    @Override
    public final float getCenterX() {
        return body.getCenterX();
    }

    @Override
    public final float getCenterY() {
        return body.getCenterY();
    }
}
