package com.samsung.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class UIButton extends UIComponent {
    private Texture button_on;
    private Texture button_off;
    private boolean pressed;
    private Action action;

    public UIButton() {

    }

    public UIButton(Texture button_on, Texture button_off) {
        this(button_on, button_off, null);
    }

    public UIButton(Texture button_on, Texture button_off, Action action) {
        this.button_on = button_on;
        this.button_off = button_off;
        this.action = action;
    }

    public void setAction() {

    }
}
