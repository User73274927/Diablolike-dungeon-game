package com.samsung.game.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;

public class DialogPanel extends Group {
    private BitmapFont text_painter;
    private Texture background;
    private String[] dialog_text;
    private int indent = 10;
    private float font_size = 0.8f;
    private int list_num;

    private UIButton next_button;
    private UIButton previous_button;
    private UIButton ok_button;
    private UIButton cancel_button;

    public DialogPanel() {
        background = new Texture("sprites/player-example1.png");
        dialog_text = new String[] {"Hello world", "How are you", "I need your help, please"};
        text_painter = new BitmapFont();
        text_painter.getData().setScale(font_size);

        next_button = new UIButton(new Texture("button-next.png"), new Texture("button-next.png"));
        next_button.setBounds(40, 10, 20, 20);
        next_button.setClickAction(() -> list_num += (list_num < dialog_text.length - 1) ? 1 : 0);

        previous_button = new UIButton(new Texture("button-previous.png"), new Texture("button-previous.png"));
        previous_button.setBounds(10, 10, 20, 20);
        previous_button.setClickAction(() -> list_num -= (list_num > 0) ? 1 : 0);

        addActor(next_button);
        addActor(previous_button);
        //addActor(ok_button);
        //addActor(cancel_button);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        String text = dialog_text[list_num];
        batch.draw(background, getX(), getY(), getWidth(), getHeight());
        text_painter.draw(batch, text, getX() + indent, getY() + getHeight() - indent);
        super.draw(batch, parentAlpha);
    }

    public void setDialogText(String[] dialog_text) {
        this.dialog_text = dialog_text;
    }
}
