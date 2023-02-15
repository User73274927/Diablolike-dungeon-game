package com.samsung.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.Knight;

public class PlayerController {
    private Knight player;

    public PlayerController(Knight player) {
        this.player = player;
    }

    public void keyHandler() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.y += 5;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.x -= 5;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.y -= 5;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.x += 5;
        }
    }
}
