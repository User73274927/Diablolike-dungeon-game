package com.samsung.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.samsung.game.entities.Bandit;
import com.samsung.game.entities.Npc;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.map.AsciiMap;

public class Level extends Stage {
    final LevelData data;
    final InputMultiplexer multiplexer;
    final PlayerController controller;
    final ViewPort port;
    final AsciiMap map;

    public Level(AsciiMap map) {
        data = new LevelData(map);
        addActor(data.entityHandler);
        addActor(data.itemHandler);

        this.map = map;
        map.load();
        multiplexer = new InputMultiplexer();
        this.controller = new PlayerController(data);
        addActor(controller.getPlayer());
        port = new ViewPort(controller);
        setViewport(new FillViewport(600, 350, port.getCamera()));

        Bandit bandit1 = new Bandit(data, 400, 200);
        Bandit bandit2 = new Bandit(data, 150, 30);
        Bandit bandit3 = new Bandit(data, 200, 100);
        Bandit bandit4 = new Bandit(data, 300, 75);
        Npc npc = new Npc(data, 50, 350);
        data.addEntity(bandit1);
        data.addEntity(bandit2);
        data.addEntity(bandit3);
        data.addEntity(bandit4);
        data.addEntity(npc);
        data.addEntity(controller.getPlayer());

        multiplexer.addProcessor(this);
        multiplexer.addProcessor(port);
        multiplexer.addProcessor(controller);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void update() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(0.04f, 0.31f, 0.40f, 1);
        port.act();
        act();
        getBatch().setProjectionMatrix(port.getCamera().combined);
        controller.keyHandler();
        getViewport().apply();
        port.update();

        getBatch().begin();

        map.draw(getBatch());
        for (Drawable item : data.visible_components) {
            item.draw(getBatch());
        }
        getBatch().end();

        draw();
        port.draw();
    }

    public void dispose() {
        data.removeAllEntity();
    }
}
