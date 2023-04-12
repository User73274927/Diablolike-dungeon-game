package com.samsung.game.items.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.samsung.game.entities.Enemy;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;
import com.samsung.game.items.projectiles.Projectile;

public class Fireball extends Projectile {
    private Texture texture;

    public Fireball(Entity owner) {
        super(owner);
        texture = new Texture("sprites/fireball-example1.png");
    }

    @Override
    public void acceptEnemyDamage(Enemy enemy) {
        enemy.getDamage(15);
    }

    @Override
    public void acceptPlayerDamage(Player player) {
        player.getDamage(15);
    }

    @Override
    public float getWidth() {
        return 20;
    }

    @Override
    public float getHeight() {
        return 20;
    }
}
