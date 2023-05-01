package com.samsung.game.items.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.DGame;
import com.samsung.game.engine.Collideable;
import com.samsung.game.engine.Damage;
import com.samsung.game.engine.Lifecycle;
import com.samsung.game.engine.RigidBody;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.weapon.Weapon;
import com.samsung.game.map.Wall;

public abstract class Projectile extends Weapon implements
                                    Lifecycle, Collideable, Damage {
    public final RigidBody body;

    private float time;
    public float limit;
    private boolean invisible;
    public boolean destroyed;
    public Texture texture;
    protected Entity owner;
    public float angle;
    public float speed;

    public Projectile(Entity owner) {
        body = new RigidBody(0, 0);
        body.addWallTouchedListener(new RigidBody.WallTouchedListener() {
            @Override
            public void touched(Wall wall) {
                destroyed = !body.flag_wallIgnore;
            }
        });

        this.owner = owner;
        body.setPosX(owner.getCenterX());
        body.setPosY(owner.getCenterY());
        destroyed = false;
    }

    @Override
    public void onCreate() {
        body.flag_wallIgnore = false;
        limit = 3;
    }

    public void update() {
        time += Gdx.graphics.getDeltaTime();

        body.getVel().set(
                (float) (speed*Math.cos(angle)),
                (float) (speed*Math.sin(angle))
        );
        body.update();

        //Условие если пуля коснулась цели, запрещающая засчитывать новые попадания
        if (invisible) {
            return;
        }

        for (Entity entity : DGame.data.allEntity) {
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

        if (getX() > Gdx.graphics.getWidth() || getX() < 0 || getY() > Gdx.graphics.getHeight() || getY() < 0) {
            destroyed = true;
        }
    }

    @Override
    public void draw(Batch batch, float pa) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void onDestroy() {
        texture.dispose();
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
    public final float getWidth() {
        return body.width;
    }

    @Override
    public final float getHeight() {
        return body.height;
    }

    public float getTime() {
        return time;
    }

    @Override
    public String info() {
        return "";
    }

    public abstract Projectile clone();
}
