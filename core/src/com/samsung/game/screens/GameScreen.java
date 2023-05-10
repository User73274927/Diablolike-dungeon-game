package com.samsung.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.samsung.game.DiabloGame;
import com.samsung.game.engine.Level;
import com.samsung.game.engine.PlayerViewPort;
import com.samsung.game.engine.gdx.StageWrapper;
import com.samsung.game.entities.player.PlayerControlField;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.map.AsciiMap;
import com.samsung.game.map.Map;
import com.samsung.game.utils.Maps;
import com.samsung.game.utils.TestAssets;

import java.util.HashMap;

public class GameScreen extends ScreenAdapter {
    private final DiabloGame diabloGame;

    public final PlayerController controller;
    public final PlayerControlField player_controls;
    public final PlayerViewPort port;

    public final InputMultiplexer inputMultiplexer;
    private final java.util.Map<String, Level> scene_dict;
    private StageWrapper current_scene;
    private State state;

    private Map[] maps;
    private int level_nm;

    public GameScreen(DiabloGame context) {
        this(context, null);
    }

    public GameScreen(DiabloGame context, PlayerController player) {
        this.diabloGame = context;
        scene_dict = new HashMap<>();
        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
        state = State.RESUME;

        level_nm = 0;

        controller = new PlayerController(this);
        player_controls = new PlayerControlField(controller);
        port = new PlayerViewPort(this, controller);

        maps = new AsciiMap[3];
        maps[1] = new AsciiMap(TestAssets.map1, controller.getPlayer());
        maps[2] = new AsciiMap(TestAssets.map2, controller.getPlayer());

        scene_dict.put("level1", new Level(this, new AsciiMap(Maps.level1, controller.getPlayer())));
        scene_dict.put("level2", new Level(this, (AsciiMap) maps[2]));


        current_scene = new Level(this, new AsciiMap(Maps.level1, controller.getPlayer())).create();

        inputMultiplexer.addProcessor(port);
        inputMultiplexer.addProcessor(player_controls);
        inputMultiplexer.addProcessor(controller);
    }

    public boolean paused;

    @Override
    public void render(float delta) {
        if (current_scene == null) {
            return;
        }

        state = (paused) ? State.PAUSED : State.RESUME;

        switch (state) {
            case PAUSED:
                return;
            case RESUME:
        }

        if (!controller.getPlayer().isEntityAlive()) {
            port.player_info.setVisible(false);
            port.setPanel(port.game_over_panel);
        }

        current_scene.getViewport().apply();
        current_scene.act();
        current_scene.draw();

        player_controls.getViewport().apply();
        player_controls.act();
        player_controls.draw();

        port.getViewport().apply();
        port.act();
        port.draw();
    }

    public final void changeScene(final Level level) {
        if (current_scene != null) {
            current_scene.dispose();
        }
        current_scene = level.create();
    }

    public final void changeScene(String key) {
        changeScene(scene_dict.get(key));
    }

    @Override
    public void resize(int width, int height) {
        current_scene.getViewport().update(width, height);
        port.getViewport().update(width, height, true);
        player_controls.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        current_scene.dispose();
    }

    @Override
    public void pause() {
        super.pause();
        state = State.PAUSED;
    }

    @Override
    public void resume() {
        super.resume();
        state = State.RESUME;
    }
}
