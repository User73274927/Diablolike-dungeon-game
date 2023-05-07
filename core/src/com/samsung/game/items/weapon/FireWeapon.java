package com.samsung.game.items.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.DGame;
import com.samsung.game.engine.ProjectileManager;
import com.samsung.game.entities.Enemy;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;
import com.samsung.game.items.PlayerEquipable;
import com.samsung.game.items.projectiles.Projectile;

public class FireWeapon extends Weapon implements PlayerEquipable<Entity> {
    protected ProjectileManager<? super Projectile> handler;
    private Projectile projectile;
    private float delta_time;
    private int velocity;

    public FireWeapon() {
        super();
        name = "Energy Weapon";
        velocity = 10;
        handler = new ProjectileManager<>();
        setDamageBounds(1, 2);
    }

    public void shoot(Projectile pr, float angle) {
        if (delta_time >= 0.2) {
            pr.setDamageBounds(getMinDamage(), getMaxDamage());
            pr.speed = velocity;
            pr.angle = angle;
            handler.add(pr);
            delta_time = 0;
        }
    }

    public void shoot(float angle) {
        this.shoot(new M762(owner), angle);
    }

    @Override
    public void draw(Batch batch, float pa) {
        handler.update();
        delta_time += Gdx.graphics.getDeltaTime();

        for (Projectile p : handler.getProjectiles()) {
            p.draw(batch, 0f);
        }
    }

    @Override
    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    @Override
    public final void onTouch(float screen_x, float screen_y) {
        float x = screen_x - getX(), y = screen_y - getY();
        shoot(projectile.clone(), (float) Math.atan2(y, x));
        System.out.println();
        System.out.println((float) Math.atan2(y, x));
        System.out.println();
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
        this.hit_chance = projectile.hit_chance;
    }

    class M762 extends Projectile {
        public M762(Entity owner) {
            super(owner);
            texture = DGame.textures.getTexture("projectile-1.png");
            body.box.width = 10;
            body.box.height = 10;
        }

        @Override
        public Projectile clone() {
            return new M762(this.owner);
        }

        @Override
        public void acceptDamage(Enemy enemy) {
            enemy.putDamage(getDamage());
        }

        @Override
        public void acceptDamage(Player player) {
            player.putDamage(getDamage());
        }
    }

}
