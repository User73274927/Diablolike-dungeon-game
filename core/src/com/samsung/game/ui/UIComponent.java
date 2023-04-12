package com.samsung.game.ui;


import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class UIComponent extends Actor {
    public void update() {

    }

    public float getCenterX() {
        return getX() + getWidth() / 2f;
    }

    public float getCenterY() {
        return getY() + getHeight() / 2f;
    }
}
