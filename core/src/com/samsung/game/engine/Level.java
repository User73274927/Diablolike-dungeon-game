package com.samsung.game.engine;

import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.map.Map;

public class Level {
    private Map map;
    private PlayerController controller;
    private ViewPort port;

    public Level(Map map) {
        this.map = map;
    }

    public void update() {

    }

}
