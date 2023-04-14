package com.samsung.game.engine;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Item;
import com.samsung.game.map.AsciiMap;

import java.util.HashSet;
import java.util.Set;

public class LevelData {
    public final AsciiMap map;
    public final Set<Item> visible_components;
    public final Set<Entity> allEntity;

    final Group entityHandler;
    final Group itemHandler;

    public LevelData(AsciiMap map) {
        this.map = map;
        visible_components = new HashSet<>();
        allEntity = new HashSet<>();
        entityHandler = new Group();
        itemHandler = new Group();
    }

    public void update() {

    }

    public void addEntity(Entity entity) {
        allEntity.add(entity);
        entityHandler.addActor(entity);
        entity.update_thread.start();
    }

    public void removeEntity(Entity entity) {
        entity.update_thread.interrupt();
        entityHandler.removeActor(entity);
        allEntity.remove(entity);
    }

    public void removeAllEntity() {
        for (Entity entity : allEntity) {
            entity.update_thread.interrupt();
            entityHandler.removeActor(entity);
        }
        allEntity.clear();
    }

}
