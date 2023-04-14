package com.samsung.game.items.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.engine.Collideable;
import com.samsung.game.engine.Damage;
import com.samsung.game.engine.LevelManager;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.weapon.Weapon;

import java.util.HashSet;
import java.util.Set;

public abstract class Projectile extends Weapon implements Collideable, Damage {
    private boolean invisible;
    public boolean destroyed;
    public Texture texture;
    protected Set<Entity> entitySet;
    private Entity owner;
    public float angle;
    public float velocity;
    private float x, y;

    public Projectile(Entity owner, Set<Entity> entitySet) {
        this.owner = owner;
        this.entitySet = entitySet;
        this.x = owner.getCenterX();
        this.y = owner.getCenterY();
        destroyed = false;
        projectiles.add(this);
    }

    public void update() {
        x += velocity * Math.cos(angle);
        y += velocity * Math.sin(angle);

        //Условие если пуля коснулась цели, запрещающая засчитывать новые попадания
        if (invisible) {
            return;
        }

        for (Entity entity : entitySet) {
            if (entity == owner) {
                continue;
            }
            if (overlaps(entity)) {
                if (isHit()) {
                    acceptDamage(entity);
                    destroyed = true;
                    break;
                }
                invisible = true;
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

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public String info() {
        return "";
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
