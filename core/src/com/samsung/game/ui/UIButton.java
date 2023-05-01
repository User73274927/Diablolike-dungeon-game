package com.samsung.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class UIButton extends UIComponent {
    @FunctionalInterface
    public interface ClickAction {
        void action();
    }

    private ClickAction click_action;
    private Texture button_on;
    private Texture button_off;
    private boolean pressed;
    private Action action;

    public UIButton(Texture button_on, Texture button_off) {
        this(button_on, button_off, null);
    }

    public UIButton(Texture button_on, Texture button_off, Action action) {
        this.button_on = button_on;
        this.button_off = button_off;
        this.action = action;
        addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                pressed = true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (click_action != null) {
                    click_action.action();
                }
                pressed = false;
                return true;
            }

        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(pressed ? button_on : button_off,
                getX(), getY(), getWidth(), getHeight()
        );
    }

    public void setClickAction(ClickAction click_action) {
        this.click_action = click_action;
    }
}
