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
import com.samsung.game.utils.TestAssets;

import java.util.HashMap;

public class GameScreen extends ScreenAdapter {
    private final DiabloGame diabloGame;

    public final PlayerController controller;
    public final PlayerControlField player_controls;
    public final PlayerViewPort port;

    public final InputMultiplexer inputMultiplexer;
    private final java.util.Map<String, StageWrapper> scene_dict;
    private StageWrapper current_scene;

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

        level_nm = 0;
        maps = new AsciiMap[3];
        maps[1] = new AsciiMap(TestAssets.map1);
        maps[2] = new AsciiMap(TestAssets.map2);

        controller = new PlayerController(this);
        player_controls = new PlayerControlField(controller);
        port = new PlayerViewPort(controller);

        scene_dict.put("level1", new Level(this, (AsciiMap) maps[1]));
        scene_dict.put("level2", new Level(this, (AsciiMap) maps[2]));


        current_scene = new Level(this, (AsciiMap) maps[1]).create();

        inputMultiplexer.addProcessor(port);
        inputMultiplexer.addProcessor(player_controls);
        inputMultiplexer.addProcessor(controller);
    }

    @Override
    public void render(float delta) {
        if (current_scene == null) {
            return;
        }

        current_scene.getViewport().apply();
        current_scene.act();
        current_scene.draw();

        player_controls.getViewport().apply();
        player_controls.act();
        player_controls.draw();

        port.getViewport().apply();
        port.act();
        port.update();
        port.draw();



    }

    public final void changeScene(final StageWrapper scene) {
        if (current_scene != null) {
            current_scene.dispose();
        }
        if (current_scene instanceof Level) {
            ((Level) current_scene).create();
        }
        current_scene = scene;
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
}
