package com.samsung.game.engine;

import com.badlogic.gdx.Gdx;


import com.samsung.game.entities.Entity;
import com.samsung.game.logic.data.DiabloGame;

import java.util.HashSet;

public class EntityBehaviorManager implements Runnable {
    private Entity entity;

    public EntityBehaviorManager(Entity entity) {
        this.entity = entity;
        entity.onSpawn();
    }

    @Override
    public void run() {

        while (entity.isAlive()) {
            entity.update();

            if (entity instanceof AnimationComponent) {
                ((AnimationComponent) entity).updateFrame();
            }

            try {
                Thread.sleep((int) (Gdx.graphics.getDeltaTime() / 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        synchronized (this) {
            entity.onDie();
            all.remove(entity);
        }
    }

    // Entities data
    private static HashSet<Entity> all;
    private static HashSet<Thread> all_threads;

    static {
        all = new HashSet<>();
        all_threads = new HashSet<>();
    }

    public static void clear() {
        for (Entity entity : all) {
            entity.onDie();
        }
        for (Thread thread : all_threads) {
            thread.interrupt();
        }

        all.clear();
        all_threads.clear();
    }

    public static void add(EntityBehaviorManager manager, Entity entity) {
        Thread thread = new Thread(manager);
        thread.start();
        all_threads.add(thread);
        all.add(entity);
    }

    public static HashSet<Entity> all() {
        return all;
    }
}
