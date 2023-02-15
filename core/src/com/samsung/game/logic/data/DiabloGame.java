package com.samsung.game.logic.data;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.samsung.game.controllers.PlayerController;
import com.samsung.game.engine.EntityBehaviorManager;
import com.samsung.game.engine.EntityFactory;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.Knight;

public class DiabloGame extends ApplicationAdapter {
    private Batch batch;
    private Knight knight;
    private PlayerController controller;

    @Override
    public void create() {
        batch = new SpriteBatch();
        knight = EntityFactory.createPlayer();
        EntityFactory.createPlayer();
        EntityFactory.createPlayer();
        controller = new PlayerController(knight);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);
        controller.keyHandler();

        batch.begin();
        for (Entity entity : EntityBehaviorManager.all()) {
            entity.draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        EntityBehaviorManager.clear();
    }
}
