package com.samsung.game.engine;

import com.samsung.game.entities.Enemy;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;

public interface Damage {
    public void acceptEnemyDamage(Enemy enemy);
    public void acceptPlayerDamage(Player player);

    public default void acceptDamage(Entity entity) {
        if (entity instanceof Player) {
            acceptPlayerDamage((Player) entity);
        } else if (entity instanceof Enemy) {
            acceptEnemyDamage((Enemy) entity);
        }
    }
}
