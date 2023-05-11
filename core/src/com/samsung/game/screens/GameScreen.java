package com.samsung.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.samsung.game.DGame;
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
    private StageWrapper current_level;
    public boolean paused;
    private State state;
    private Map[] maps;

    public GameScreen(DiabloGame context) {
        this(context, null);
    }

    public GameScreen(DiabloGame context, PlayerController player) {
        this.diabloGame = context;
        scene_dict = new HashMap<>();
        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
        state = State.RESUME;

        controller = new PlayerController(this);
        player_controls = new PlayerControlField(controller);
        port = new PlayerViewPort(this, controller);

        scene_dict.put("level1", new Level(this, new AsciiMap(Maps.level1, controller.getPlayer())));
        scene_dict.put("level2", new Level(this, new AsciiMap(Maps.level2, controller.getPlayer())));

        changeLevel("level1");
        inputMultiplexer.addProcessor(port);
        inputMultiplexer.addProcessor(player_controls);
        inputMultiplexer.addProcessor(controller);
    }

    @Override
    public void render(float delta) {
        if (current_level == null) {
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

        current_level.getViewport().apply();
        current_level.act();
        current_level.draw();

        player_controls.getViewport().apply();
        player_controls.act();
        player_controls.draw();

        port.getViewport().apply();
        port.act();
        port.draw();
    }

    public final void changeLevel(final Level level) {
        if (current_level != null) {
            current_level.dispose();
        }
        System.out.println(level);
        current_level = level.create();
        resize((int)DGame.getScreenWidth(), (int)DGame.getScreenHeight());
    }

    public final void changeLevel(String key) {
        changeLevel(scene_dict.get(key));
    }

    @Override
    public void resize(int width, int height) {
        current_level.getViewport().update(width, height);
        port.getViewport().update(width, height, true);
        player_controls.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        for (StageWrapper level : scene_dict.values())
            level.dispose();
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

    public int levels() {
        return scene_dict.size();
    }
}
