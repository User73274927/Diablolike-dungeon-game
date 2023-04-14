package com.samsung.game.items.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.engine.ProjectileManager;
import com.samsung.game.entities.Enemy;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.projectiles.Projectile;

import java.util.Set;

public class FireWeapon extends Weapon implements Equipable<Entity> {
    private ProjectileManager<Projectile> handler;
    protected Set<Entity> entitySet;
    private float delta_time;
    private int velocity;

    public FireWeapon(Set<Entity> entitySet) {
        super();
        name = "Energy Weapon";
        velocity = 10;
        this.entitySet = entitySet;
        handler = new ProjectileManager<>();
        hit_chance = 0.5;
        setDamageBounds(15, 25);
    }

    public final void shoot(float angle) {
        if (delta_time >= 0.1) {
            M762 m762 = new M762(owner);
            m762.setDamageBounds(getMinDamage(), getMaxDamage());
            m762.hit_chance = hit_chance;
            m762.velocity = velocity;
            m762.angle = angle;
            handler.add(m762);
            delta_time = 0;
        }
    }

    @Override
    public void draw(Batch batch) {
        handler.update();
        delta_time += Gdx.graphics.getDeltaTime();

        for (Projectile p : handler.getProjectiles()) {
            p.draw(batch);
        }
    }

    @Override
    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    @Override
    public final void onTouch(float screen_x, float screen_y) {
        float x = getX() - screen_x, y = getY() - screen_y;
        shoot((float) Math.atan2(-y, -x));
    }

    class M762 extends Projectile {
        public M762(Entity owner) {
            super(owner, FireWeapon.this.entitySet);
            texture = new Texture("projectile-1.png");
        }

        @Override
        public float getWidth() {
            return 10;
        }

        @Override
        public float getHeight() {
            return 10;
        }

        @Override
        public void acceptEnemyDamage(Enemy enemy) {
            enemy.putDamage(getDamage());
        }

        @Override
        public void acceptPlayerDamage(Player player) {
            player.putDamage(getDamage());
        }
    }

}
