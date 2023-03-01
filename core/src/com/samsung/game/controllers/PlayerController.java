package com.samsung.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.samsung.game.entities.Knight;

public class PlayerController {
    private Knight player;

    public PlayerController(Knight player) {
        this.player = player;
    }

    public void keyHandler() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.setDirection(Knight.Direction.UP);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.setDirection(Knight.Direction.LEFT);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.setDirection(Knight.Direction.DOWN);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setDirection(Knight.Direction.RIGHT);
        } else {
            player.setDirection(Knight.Direction.STOP);
        }
    }
}
