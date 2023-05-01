package com.samsung.game.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.samsung.game.DGame;
import com.samsung.game.engine.gdx.StageWrapper;
import com.samsung.game.entities.player.Player;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.entities.player.PlayerHUD;

public class PlayerViewPort extends StageWrapper {
    private Viewport viewport;
    private OrthographicCamera camera;

    private PlayerController player_controller;
    private Player player;
    private PlayerHUD player_info;

    public final float viewport_width = 800;
    public final float viewport_height = 800*DGame.getAspectRatio();

    public PlayerViewPort(PlayerController player_controller) {
        this.player_controller = player_controller;
        this.player = player_controller.getPlayer();
        player_info = new PlayerHUD(this, player_controller);
        viewport = new ScalingViewport(Scaling.fit, viewport_width, viewport_height);

        setViewport(viewport);
        addActor(player_info);
    }

    public void update() {

    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

}
