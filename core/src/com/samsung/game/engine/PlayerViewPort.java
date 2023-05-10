package com.samsung.game.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.samsung.game.DGame;
import com.samsung.game.engine.gdx.GroupWrapper;
import com.samsung.game.engine.gdx.StageWrapper;
import com.samsung.game.entities.player.Player;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.entities.player.PlayerHUD;
import com.samsung.game.screens.GameScreen;
import com.samsung.game.ui.panels.GameOverPanel;

public class PlayerViewPort extends StageWrapper {
    public final float viewport_width = 800;
    public final float viewport_height = 800*DGame.getAspectRatio();

    private Viewport viewport;
    private OrthographicCamera camera;

    private PlayerController player_controller;
    private Player player;

    //Scenes
    public final PlayerHUD player_info;
    public final GameOverPanel game_over_panel;

    public PlayerViewPort(GameScreen screen, PlayerController player_controller) {
        this.player_controller = player_controller;
        this.player = player_controller.getPlayer();
        viewport = new ScalingViewport(Scaling.fit, viewport_width, viewport_height);

        player_info = new PlayerHUD(this, player_controller);
        game_over_panel = new GameOverPanel(this);
        game_over_panel.setVisible(false);

        setViewport(viewport);
        addActor(player_info);
        addActor(game_over_panel);
    }

    public void setPanel(GroupWrapper panel) {
        panel.setSize(viewport_width, viewport_height);
        panel.setVisible(true);
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

    public void getTileMap() {

    }
}
