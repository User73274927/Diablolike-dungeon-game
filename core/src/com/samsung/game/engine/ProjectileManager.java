package com.samsung.game.engine;

import com.samsung.game.DGame;
import com.samsung.game.items.projectiles.Projectile;

import java.util.HashSet;

public class ProjectileManager<T extends Projectile> {
    private HashSet<T> projectiles;
    private HashSet<T> to_destroy;
    private T current_projectile;

    public ProjectileManager() {
        projectiles = new HashSet<>();
        to_destroy = new HashSet<>();
    }

    public void update() {
        for (T projectile : projectiles) {
            current_projectile = projectile;
            projectile.update();

            if (projectile.destroyed || projectile.getTime() >= projectile.limit) {
                to_destroy.add(projectile);
            }

        }

        for (Projectile p : to_destroy) {
            p.onDestroy();
            projectiles.remove(p);
        }

        to_destroy.clear();
    }

    public void add(T projectile) {
        projectile.onCreate();
        DGame.data.field.addBody(projectile.body);
        projectiles.add(projectile);
    }

    public T getCurrentProjectile() {
        return current_projectile;
    }

    public HashSet<T> getProjectiles() {
        return projectiles;
    }

}
