package com.samsung.game.items.weapon;

import com.samsung.game.engine.Damage;
import com.samsung.game.entities.Enemy;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;
import com.samsung.game.items.PlayerEquipable;

import java.util.Set;

public abstract class ColdWeapon extends Weapon implements Damage, PlayerEquipable<Entity> {
    protected float attack_distance = 5;
    protected Set<Entity> entitySet;

    public ColdWeapon(Set<Entity> entitySet) {
        this.entitySet = entitySet;
    }

    public void onTouch(float screen_x, float screen_y) {
        for (Entity entity : entitySet) {
            if (entity == owner) {
                continue;
            }
            float cx = Math.abs(owner.getCenterX() - entity.getCenterX()),
                    cy = Math.abs(owner.getCenterY() - entity.getCenterY());

            if (Math.sqrt(cx + cy) <= attack_distance) {
                acceptDamage(entity);
                break;
            }
        }
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
