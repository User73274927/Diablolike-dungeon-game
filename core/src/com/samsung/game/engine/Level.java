package com.samsung.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.samsung.game.entities.Bandit;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.Npc;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.items.Item;
import com.samsung.game.map.Map;

import java.util.HashSet;
import java.util.Set;

public class Level extends Stage {
    public static Set<Item> visible_components = new HashSet<>();
    public final Set<Entity> allEntity = new HashSet<>();

    private Group entityHandler;
    private Group itemHandler;
    final InputMultiplexer multiplexer;
    final PlayerController controller;
    final ViewPort port;
    final Map map;

    public Level(Map map) {
        entityHandler = new Group();
        addActor(entityHandler);

        this.map = map;
        map.load();
        multiplexer = new InputMultiplexer();
        this.controller = new PlayerController(map);
        addActor(controller.getPlayer());
        port = new ViewPort(controller);
        setViewport(new FillViewport(600, 350, port.getCamera()));

        Bandit bandit1 = new Bandit(map, 400, 200);
        Bandit bandit2 = new Bandit(map, 150, 30);
        Bandit bandit3 = new Bandit(map, 200, 100);
        Bandit bandit4 = new Bandit(map, 300, 75);
        Npc npc = new Npc(map, 50, 350);
        addEntity(bandit1);
        addEntity(bandit2);
        addEntity(bandit3);
        addEntity(bandit4);
        addEntity(npc);
        addEntity(controller.getPlayer());
        controller.startTalk(npc);

        multiplexer.addProcessor(this);
        multiplexer.addProcessor(port);
        multiplexer.addProcessor(controller);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void update() {
        port.act();
        act();
        getBatch().setProjectionMatrix(port.getCamera().combined);
        controller.keyHandler();
        getViewport().apply();
        port.update();
        draw();
        port.draw();

        getBatch().begin();

        map.draw(getBatch());
        for (Drawable item : visible_components) {
            item.draw(getBatch());
        }
        getBatch().end();
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

    public void dispose() {
        removeAllEntity();
    }
}
