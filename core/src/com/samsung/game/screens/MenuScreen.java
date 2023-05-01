package com.samsung.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.samsung.game.DiabloGame;
import com.samsung.game.data.Json;

public class MenuScreen extends ScreenAdapter {
    public DiabloGame context;
    public ImageButton play;

    public MenuScreen(DiabloGame context) {
        this.context = context;
        play = new ImageButton(new Skin(Gdx.files.internal(Json.PATH_UIStyle)));
    }

}
