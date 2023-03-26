package com.samsung.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.entities.Bandit;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;
import com.samsung.game.items.Item;
import com.samsung.game.map.Map;
import com.samsung.game.utils.GameUtils;

import java.util.HashSet;
import java.util.Set;

public class LevelManager {
    public static Set<Item> visible_components = new HashSet<>();
    private Level[] levels;
    private InputMultiplexer multiplexer;
    private PlayerController controller;
    private ViewPort port;

    private OrthographicCamera camera;
    private Map map;

    public LevelManager(Map map) {
        this.map = map;
        map.load();
        multiplexer = new InputMultiplexer();

        this.controller = new PlayerController(map);
        port = new ViewPort(controller);

        new Bandit(500, 200, 25,30).start();
        new Bandit(150, 30, 25,30).start();
        new Bandit(200, 100, 25,30).start();
        new Bandit(300, 75, 25,30).start();

        multiplexer.addProcessor(controller);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void update(Batch batch) {
        batch.setProjectionMatrix(port.getCamera().combined);
        port.act(Gdx.graphics.getDeltaTime());
        port.update();
        controller.keyHandler();

        batch.begin();
        map.draw(batch);

        synchronized (this) {
            for (Entity entity : Entity.all()) {
                entity.getView().draw(batch);
            }
        }

        for (Drawable item : visible_components) {
            item.draw(batch);
        }

        port.draw(batch, 1);
        batch.end();

    }

    @Override
    public String toString() {
        return "viewport world width: " + camera.viewportWidth +
                "\nviewport world height: " + camera.viewportHeight +
                "\ncamera x: " + camera.direction.x +
                "\ncamera y: " + camera.direction.y;
    }
}
