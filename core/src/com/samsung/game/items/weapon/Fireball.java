package com.samsung.game.items.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.projectiles.Projectile;

public class Fireball extends Projectile {
    private Texture texture;

    public Fireball(Entity owner, float speed, float angle) {
        super(owner, speed, angle);
        texture = new Texture("sprites/fireball-example1.png");
    }

    @Override
    public void acceptDamage(Entity entity) {
        entity.getDamage(15);
    }

    @Override
    public int getWidth() {
        return 20;
    }

    @Override
    public int getHeight() {
        return 20;
    }
}
