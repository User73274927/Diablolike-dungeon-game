package com.samsung.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.samsung.game.controllers.PlayerController;
import com.samsung.game.entities.Bandit;
import com.samsung.game.entities.Enemy;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.Knight;
import com.samsung.game.map.Map;
import com.samsung.game.utils.GameUtils;
import com.samsung.game.utils.PxNumber;

import java.util.HashSet;
import java.util.Set;

public class LevelManager {
    public static final Logger log = new Logger("TTT");
    private Set<Component> visible_components;
    private PlayerController controller;
    private Knight player;
    private PxNumber nm;

    private Viewport port;
    private OrthographicCamera camera;
    private Map map;

    public LevelManager(Map map) {
        this.map = map;
        map.load();
        this.player = new Knight(map, 2, 50 ,80);
        this.nm = new PxNumber(0, 100, 50, 15);
        this.controller = new PlayerController(player);
        this.camera = new OrthographicCamera(500,
                GameUtils.relatedFrom(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 500));

        new Bandit(500, 200, 25,30).start();
        new Bandit(150, 30, 25,30).start();
        new Bandit(200, 100, 25,30).start();
        new Bandit(300, 75, 25,30).start();
    }

    public void update(Batch batch) {
        batch.setProjectionMatrix(camera.combined);
        camera.position.set(player.getCenterPos(), 0);
        camera.update();
        ScreenUtils.clear(Color.BLACK);
        controller.keyHandler();

        batch.begin();
        map.draw(batch);

        for (Entity entity : Entity.all()) {
            entity.draw(batch);
        }

        batch.end();
    }

    private void drawScreen() {

    }
}
