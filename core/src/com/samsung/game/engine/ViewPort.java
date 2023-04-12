package com.samsung.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.samsung.game.entities.player.Player;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.entities.player.PlayerHUD;

public class ViewPort extends Stage {
    private OrthographicCamera camera;
    private PlayerController player_controller;
    private Player player;
    private PlayerHUD player_info;

    public ViewPort(PlayerController player_controller) {
        this.player_controller = player_controller;
        this.player = player_controller.getPlayer();
        this.player_info = player_controller.getIUPanel();
        camera = new OrthographicCamera();
        player_controller.setCamera(camera);
        addActor(player_info);
    }

    public void update() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(Color.DARK_GRAY);
        getViewport().apply();
        camera.position.set(player.getCenterX(), player.getCenterY(), 0);
        camera.update();
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }
}
