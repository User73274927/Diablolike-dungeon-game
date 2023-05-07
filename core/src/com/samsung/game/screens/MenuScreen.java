package com.samsung.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.samsung.game.DiabloGame;
import com.samsung.game.data.Json;
import com.samsung.game.ui.UIButton;

public class MenuScreen extends ScreenAdapter {
    public DiabloGame context;
    public UIButton play;

    public MenuScreen(DiabloGame context) {
        this.context = context;
        Texture play_texture = new Texture("");
        //play = new UIButton();
    }

}
