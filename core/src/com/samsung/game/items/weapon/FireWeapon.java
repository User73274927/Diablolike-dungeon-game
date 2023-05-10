package com.samsung.game.items.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.DGame;
import com.samsung.game.data.Textures;
import com.samsung.game.engine.ProjectileManager;
import com.samsung.game.entities.Enemy;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;
import com.samsung.game.items.PlayerEquipable;
import com.samsung.game.items.projectiles.BouncedProjectile;
import com.samsung.game.items.projectiles.Projectile;

public class FireWeapon extends Weapon implements PlayerEquipable<Entity> {
    protected Sound shoot_sound;
    protected float delta_time;
    protected float time;
    protected int velocity;
    private Projectile projectile;


    public FireWeapon(Entity owner) {
        super();
        texture = DGame.textures.getTexture(Textures.SPRITES+"fire-weapon.png");
        shoot_sound = Gdx.audio.newSound(Gdx.files.internal("shoot-example1.mp3"));
        name = "Fire Weapon";
        velocity = 10;
        delta_time = 0.08f;
        setProjectile(new Bullet(owner));
        setDamageBounds(5, 15);
    }


    public boolean shoot(Projectile pr, float angle) {
        if (time >= delta_time) {
            pr.setDamageBounds(getMinDamage(), getMaxDamage());
            pr.speed = velocity;
            pr.angle = angle;
            DGame.projectiles.add(pr);
            time = 0;
            return true;
        }
        return false;
    }

    @Override
    public void draw(Batch batch, float pa) {
        time += Gdx.graphics.getDeltaTime();
        super.draw(batch, pa);
    }

    @Override
    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    @Override
    public final void onTouch(float screen_x, float screen_y) {
        float x = screen_x - getX(), y = screen_y - getY();
        Projectile new_pr = projectile.clone();

        boolean isShoot;
        if (projectile != null)
            isShoot = shoot(new_pr, (float) Math.atan2(y, x));
        else
            isShoot = shoot(new Bullet(owner), (float) Math.atan2(y, x));

        if (owner instanceof Player && isShoot) {
            ((Player) owner).addMana(-new_pr.required_mana);
            shoot_sound.play(1f);
        }
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
        projectile.onCreate();
        this.hit_chance = projectile.hit_chance;
        setDamageBounds(projectile.getMinDamage(), projectile.getMaxDamage());
    }

    @Override
    public Integer getRequireMana() {
        return projectile.required_mana;
    }

    static class Bullet extends Projectile {
        public Bullet(Entity owner) {
            super(owner);
            texture = DGame.textures.getTexture(Textures.PROJECTILES+"bullet.png");
            hit_chance = 0.75f;
            required_mana = 1;

            body.box.width = 10;
            body.box.height = 10;
        }

        @Override
        public Projectile clone() {
            Projectile pr = new Bullet(owner);
            pr.setDamageBounds(getMinDamage(), getMaxDamage());
            return pr;
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

    @Override
    public String info() {
        return super.info() +
                "require mana: " + projectile.required_mana + "\n" +
                "speed: " + velocity;
    }
}
