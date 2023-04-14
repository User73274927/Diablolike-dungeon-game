package com.samsung.game.logic.data;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.samsung.game.engine.LevelManager;
import com.samsung.game.map.AsciiMap;

public class DiabloGame extends ApplicationAdapter {
    private LevelManager lvl;
    private Batch batch;
    private AsciiMap map;

    @Override
    public void create() {
        batch = new SpriteBatch();
        map = new AsciiMap();
        lvl = new LevelManager(map);
    }

    @Override
    public void render() {
        LevelManager.current_level.update();
    }

    @Override
    public void resize(int width, int height) {
        lvl.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        lvl.dispose();
    }
}
