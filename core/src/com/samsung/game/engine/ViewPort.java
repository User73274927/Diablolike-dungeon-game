package com.samsung.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.samsung.game.entities.player.Player;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.entities.player.UIPlayerPanel;

public class ViewPort extends Stage {
    private OrthographicCamera camera;
    private PlayerController player_controller;
    private Vector3 touch_pos;
    private Player player;
    private UIPlayerPanel player_info;

    public ViewPort(PlayerController player_controller) {
        this.player_controller = player_controller;
        this.player = player_controller.getPlayer();
        this.player_info = player_controller.getIUPanel();
        camera = new OrthographicCamera(400, 300);
        player_controller.setCamera(camera);
        touch_pos = new Vector3();
        addActor(player_info);
    }

    public void update() {
        ScreenUtils.clear(Color.BLACK);
        camera.position.set(player.getCenterX(), player.getCenterY(), 0);
        camera.update();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }
}
