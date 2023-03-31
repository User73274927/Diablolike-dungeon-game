package com.samsung.game.items.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.engine.Collideable;
import com.samsung.game.engine.Damage;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.weapon.Weapon;

import java.util.HashSet;

public abstract class Projectile extends Weapon implements Collideable, Damage {
    public boolean destroyed;
    public Texture texture;
    private Entity owner;
    private float angle;
    private float speed;
    private float x, y;

    public Projectile(Entity owner, float speed, float angle) {
        super();
        this.owner = owner;
        this.angle = angle;
        this.speed = speed;
        this.x = owner.getCenterX();
        this.y = owner.getCenterY();
        destroyed = false;
        projectiles.add(this);
    }

    public void update() {
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);

        for (Entity entity : Entity.all()) {
            if (entity == getOwner()) {
                continue;
            }
            if (overlaps(entity)) {
                if (isHit()) {
                    acceptDamage(entity);
                    destroyed = true;
                    break;
                }
            }
        }

        if (x > Gdx.graphics.getWidth() || x < 0 || y > Gdx.graphics.getHeight() || y < 0) {
            destroyed = true;
        }
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public Entity getOwner() {
        return owner;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    //static members

    private static HashSet<Projectile> projectiles;

    static {
        projectiles = new HashSet<>();
    }

    public static HashSet<Projectile> projectiles() {
        return projectiles;
    }
}
