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
import com.samsung.game.items.Item;
import com.samsung.game.items.weapon.Projectile;
import com.samsung.game.map.Map;
import com.samsung.game.utils.GameUtils;
import com.samsung.game.utils.PxNumber;

import java.util.HashSet;
import java.util.Set;

public class LevelManager {
    public static Set<Component> visible_components = new HashSet<>();
    private PlayerController controller;
    private Knight player;

    private OrthographicCamera camera;
    private Map map;

    public LevelManager(Map map) {
        this.map = map;
        map.load();

        this.camera = new OrthographicCamera(500,
                GameUtils.relatedFrom(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 500));
        this.player = new Knight(camera, map);
        this.controller = new PlayerController(player);

        new Bandit(500, 200, 25,30).start();
        new Bandit(150, 30, 25,30).start();
        new Bandit(200, 100, 25,30).start();
        new Bandit(300, 75, 25,30).start();

        System.out.println(this);
    }

    public void update(Batch batch) {
        batch.setProjectionMatrix(camera.combined);
        camera.position.set(player.getCenterX(), player.getCenterY(), 0);
        camera.update();
        ScreenUtils.clear(Color.BLACK);
        controller.keyHandler();

        batch.begin();
        map.draw(batch);

        synchronized (this) {
            for (Entity entity : Entity.all()) {
                entity.getView().draw(batch);
            }
        }

        for (Component item : visible_components) {
            item.draw(batch);
        }

        batch.end();

        System.out.println("camera x: " + camera.position.x +
                "\ncamera y: " + camera.position.y);
    }

    private void drawScreen() {

    }

    @Override
    public String toString() {
        return "viewport world width: " + camera.viewportWidth +
                "\nviewport world height: " + camera.viewportHeight +
                "\ncamera x: " + camera.direction.x +
                "\ncamera y: " + camera.direction.y;
    }
}
