package com.samsung.game.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.samsung.game.controllers.PlayerController;
import com.samsung.game.map.Map;

public class Level {
    private Map map;
    private PlayerController controller;

    private OrthographicCamera camera;

    public Level(Map map) {
        this.map = map;
    }

    public void update() {

    }

}
