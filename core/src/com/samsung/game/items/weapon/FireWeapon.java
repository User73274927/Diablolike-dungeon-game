package com.samsung.game.items.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.samsung.game.engine.ProjectileManager;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.projectiles.Projectile;

public class FireWeapon extends Weapon implements Equipable<Entity> {
    private ProjectileManager<M762> handler;
    private float time;
    public float angle;


    public FireWeapon() {
        super();
        handler = new ProjectileManager<>();
    }

    public void shoot(float angle) {
        if (time >= 0.1) {
            M762 m762 = new M762(owner, 10, angle);
            m762.hit_chance = hit_chance;
            handler.add(m762);
            time = 0;
        }
    }

    @Override
    public void draw(Batch batch) {
        handler.update();
        time += Gdx.graphics.getDeltaTime();

        for (Projectile p : handler.getProjectiles()) {
            p.draw(batch);
        }
    }

    @Override
    public Entity getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    @Override
    public final void onTouch(float screen_x, float screen_y) {
        float x = getX() - screen_x, y = getY() - screen_y;

        shoot((float) Math.atan2(-y, -x));
        if (owner.getClass() == Player.class) {
            Gdx.app.log("x", x + "");
            Gdx.app.log("y", y + "");
            Gdx.app.log("angle",  Math.atan2(-y, -x) + "");
        }
    }

    class M762 extends Projectile {
        public M762(Entity owner, float speed, float angle) {
            super(owner, speed, angle);
            texture = new Texture("sprites/fireball-example1.png");
            setDamageBounds(10, 20);
        }

        @Override
        public int getWidth() {
            return 10;
        }

        @Override
        public int getHeight() {
            return 10;
        }


        @Override
        public void acceptDamage(Entity entity) {
            entity.getDamage(getDamage());
        }
    }
}
