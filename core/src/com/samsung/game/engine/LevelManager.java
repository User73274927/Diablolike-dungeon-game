package com.samsung.game.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.Knight;
import com.samsung.game.map.Map;

import java.util.HashSet;

public class LevelManager {
    private HashSet<Entity> entities;
    private Knight player;

    private OrthographicCamera camera;
    private Map map;

    public LevelManager(Map map) {
        this.map = map;
    }
}
