package com.samsung.game.engine;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.weapon.Projectile;

import java.util.HashSet;

public class ProjectileManager<T extends Projectile> {
    private HashSet<T> projectiles;
    private HashSet<T> to_destroy;
    private volatile T current_projectile;

    public ProjectileManager() {
        projectiles = new HashSet<>();
        to_destroy = new HashSet<>();
    }

    public void update() {
        for (T projectile : projectiles) {
            current_projectile = projectile;
            projectile.update();

            if (projectile.destroyed) {
                to_destroy.add(projectile);
            }

        }

        for (Projectile p : to_destroy) {
            projectiles.remove(p);
        }

        to_destroy.clear();
    }

    public void add(T projectile) {
        projectiles.add(projectile);
    }

    public T getCurrentProjectile() {
        return current_projectile;
    }

    public HashSet<T> getProjectiles() {
        return projectiles;
    }

}
