package com.samsung.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.ScreenUtils;
import com.samsung.game.entities.player.Player;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.entities.player.UIPlayerPanel;

public class ViewPort extends Actor {
    private OrthographicCamera camera;
    private PlayerController player_controller;
    private Player player;
    private UIPlayerPanel player_info;

    public ViewPort(PlayerController player_controller) {
        this.player_controller = player_controller;
        this.player = player_controller.getPlayer();
        this.player_info = player_controller.getIUPanel();

        this.camera = new OrthographicCamera(400, 300);

    }

    public void update() {
        ScreenUtils.clear(Color.BLACK);
        camera.position.set(player.getCenterX(), player.getCenterY(), 0);
        camera.update();
        player_controller.setCamLocation(getX(), getY());

        player_info.act();
        player_info.draw();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        player.getInventory().draw(batch);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public float getX() {
        return camera.position.x - camera.viewportWidth / 2;
    }

    @Override
    public float getY() {
        return camera.position.y - camera.viewportHeight / 2;
    }
}
