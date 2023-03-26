package com.samsung.game.logic.data;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.engine.LevelManager;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;
import com.samsung.game.map.Map;
import com.samsung.game.ui.PxNumber;

public class DiabloGame extends ApplicationAdapter {
    private LevelManager lvl;
    private Batch batch;
    private Map map;
    private Player knight;
    private PlayerController controller;
    private PxNumber number;

    @Override
    public void create() {
        batch = new SpriteBatch();
        map = new Map();
        lvl = new LevelManager(map);
    }

    @Override
    public void render() {
        lvl.update(batch);
    }

    @Override
    public void dispose() {
        super.dispose();
        Entity.clear();
    }
}
