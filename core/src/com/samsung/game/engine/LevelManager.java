package com.samsung.game.engine;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.samsung.game.map.Map;

public class LevelManager extends ScreenAdapter {
    public static Level current_level;
    private Level[] levels;

    public LevelManager(Map map) {
        levels = new Level[3];
        current_level = new Level(map);
    }

    @Override
    public void render(float delta) {
        current_level.update();
    }

    @Override
    public void dispose() {
        current_level.dispose();
    }

    @Override
    public void resize(int width, int height) {
        current_level.getViewport().update(width, height);
        current_level.port.getViewport().update(width, height);
    }
}
