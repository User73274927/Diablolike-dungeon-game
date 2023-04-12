package com.samsung.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.samsung.game.items.Item;

public class DescriptionPanel extends UIComponent {
    private Texture frame;
    private BitmapFont text_painter;
    private String title;
    private String text;
    public float margin;
    public float font_size = 0.8f;

    public DescriptionPanel() {
        frame = new Texture("tx-description-panel.png");
        text_painter = new BitmapFont();
        text_painter.getData().setScale(font_size);
        text_painter.setColor(0, 0, 0, 255);
        title = "";
        text = "";
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        float x = getX();
        float y = getY();
        int w = (int)getWidth();
        int h = (int)getHeight();

        batch.draw(frame, x, y, w, h);
        text_painter.draw(batch, title, x + margin, y + h - margin);
        text_painter.draw(batch, text, x + margin, y + h - (margin + 12));
    }

    public void setItemDescription(Item item) {
        setTitle(item.getItemName());
        setText(item.info());
    }

    public void setTitle(String title) {
        this.title = (title != null) ? title : "";
    }

    public void setText(String text) {
        this.text = (text != null) ? text : "";
    }
}
