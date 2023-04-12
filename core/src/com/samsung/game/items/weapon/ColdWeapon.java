package com.samsung.game.items.weapon;

import com.samsung.game.engine.Damage;
import com.samsung.game.engine.LevelManager;
import com.samsung.game.entities.Enemy;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;
import com.samsung.game.items.Equipable;

public abstract class ColdWeapon extends Weapon implements Damage, Equipable<Entity> {
    protected float attack_distance = 5;

    public void onTouch(float screen_x, float screen_y) {
        for (Entity entity : LevelManager.current_level.allEntity) {
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
    public void acceptEnemyDamage(Enemy enemy) {
        enemy.getDamage(getDamage());
    }

    @Override
    public void acceptPlayerDamage(Player player) {
        player.getDamage(getDamage());
    }
}
