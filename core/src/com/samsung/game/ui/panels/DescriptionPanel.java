package com.samsung.game.ui.panels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.samsung.game.DGame;
import com.samsung.game.data.Fonts;
import com.samsung.game.engine.gdx.GroupWrapper;
import com.samsung.game.items.Item;
import com.samsung.game.items.PlayerEquipable;
import com.samsung.game.items.PlayerUsable;
import com.samsung.game.ui.UIButton;

import static com.samsung.game.data.Textures.UI;

public class DescriptionPanel extends GroupWrapper {
    private Texture frame;
    private BitmapFont text_painter;

    private UIButton drop_button;
    private UIButton use_button;
    private UIButton equip_button;
    private UIButton button;

    private Item item;
    private int col, row;
    private String title;
    private String text;
    public float margin;
    public float font_size = 0.8f;

    public DescriptionPanel() {
        frame = new Texture(UI+"tx-description-panel.png");
        text_painter = DGame.fonts.getFont(Fonts.Type.PxBlackFont10);
        text_painter.setColor(0, 0, 0, 255);
        title = "";
        text = "";

        drop_button = new UIButton(DGame.textures.getTexture(UI+"plain-button.png"), DGame.textures.getTexture(UI+"plain-button.png"));
        use_button = new UIButton(DGame.textures.getTexture(UI+"plain-button.png"), DGame.textures.getTexture(UI+"plain-button.png"));
        equip_button = new UIButton(DGame.textures.getTexture(UI+"plain-button.png"), DGame.textures.getTexture(UI+"plain-button.png"));

        drop_button.setBounds(10, 10, 25, 10);
        //addActor(button);
        addActor(drop_button);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        float x = getX();
        float y = getY();
        int w = (int)getWidth();
        int h = (int)getHeight();

        batch.draw(frame, x, y, w, h);
        text_painter.draw(batch, title, x + margin, y + h - margin - 8);
        text_painter.draw(batch, text, x + margin, y + h - (margin + 26));
        super.draw(batch, parentAlpha);
    }

    public void setItemDescription(Item item) {
        setTitle(item.getItemName());
        setText(item.info());
    }

    public void setItem(Item item) {
        if (item instanceof PlayerEquipable) {
            button = equip_button;
        }
        else if (item instanceof PlayerUsable) {
            button = use_button;
        }

        drop_button.setVisible(true);

        setTitle(item.getItemName());
        setText(item.info());
    }

    public void setTitle(String title) {
        this.title = (title != null) ? title : "";
    }

    public void setText(String text) {
        this.text = (text != null) ? text : "";
    }

    public void clean() {
        drop_button.setVisible(false);
        title="";
        text="";
    }
}
