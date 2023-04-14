package com.samsung.game.items.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.samsung.game.entities.Enemy;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;

import java.util.Set;

public class Fireball extends Projectile {
    private Texture texture;

    public Fireball(Entity owner, Set<Entity> entitySet) {
        super(owner, entitySet);
        texture = new Texture("sprites/fireball-example1.png");
    }

    @Override
    public void acceptEnemyDamage(Enemy enemy) {
        enemy.putDamage(15);
    }

    @Override
    public void acceptPlayerDamage(Player player) {
        player.putDamage(15);
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
