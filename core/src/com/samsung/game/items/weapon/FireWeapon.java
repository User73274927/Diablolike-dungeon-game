package com.samsung.game.items.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.engine.ProjectileManager;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.projectiles.Projectile;

public class FireWeapon extends Weapon implements Equipable<Entity> {
    private Entity owner;
    private ProjectileManager<M762> handler;
    private float time;

    public FireWeapon() {
        super();
        handler = new ProjectileManager<>();
    }

    public void shoot() {
        if (time >= 0.1) {
            handler.add(new M762(owner, 10, 0));
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
    public float getX() {
        if (owner == null) {
            return 0;
        }
        return owner.getCenterX();
    }

    @Override
    public float getY() {
        if (owner == null) {
            return 0;
        }
        return owner.getCenterY();
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
    public void onClick() {
        shoot();
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
