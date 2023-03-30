package com.samsung.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.samsung.game.engine.Collideable;
import com.samsung.game.engine.Drawable;
import com.samsung.game.map.Tile;
import com.samsung.game.ui.UIComponent;
import com.samsung.game.utils.GameUtils;

import java.util.HashMap;

public abstract class Item implements Drawable, Collideable {
    protected Texture texture;
    private final HashMap<String, UIView> ui_views;

    protected int height;
    protected int width;
    protected Vector2 pos;

    public boolean item_visible;
    //Расположение предмета в инвентаре, если предмет на земле то значения будут -1
    private int col, row;
    protected Texture icon_texture;

    //@Deprecated
    public enum UILocation {
        ON_SCREEN("scr"),
        IN_INVENTORY("inv");

        final String key;
        UILocation(String key) { this.key = key; }
    }

    public Item() {
        ui_views = new HashMap<>();
        pos = new Vector2();
        createUIView(UILocation.IN_INVENTORY);
        createUIView(UILocation.ON_SCREEN);
        col = row = -1;
    }

    public void drop(float x, float y) {
        item_visible = true;
        this.pos.x = x;
        this.pos.y = y;
        col = row = -1;
    }

    public Texture getIconTexture() {
        return icon_texture;
    }

    protected void setItemSize(int height) {
        this.width = (int) GameUtils.relatedFrom(this.height, width, height);
        this.height = height;
    }

    public void createUIView(UILocation location) {
        ui_views.put(location.key, new UIView());
    }

    public UIView findUIView(UILocation location) {
        return ui_views.get(location.key);
    }

    public UIView createUIView() {
        return new UIView();
    }

    @Override
    public void draw(Batch batch) {
        if (item_visible) {
            batch.draw(texture, getX(), getY(), width, height);
        }
    }

    public void setPosInInventory(int row, int col) {
        if (row > 0 && col > 0) {
            this.row = row;
            this.col = col;
        }
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    @Override
    public float getX() {
        return pos.x;
    }

    @Override
    public float getY() {
        return pos.y;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public class UIView extends UIComponent {
        private Vector2 icon_pos;
        private int icon_width, icon_height;
        public boolean visible;

        UIView() {
            icon_pos = new Vector2();
            icon_width = icon_height = Tile.SIZE;

            addListener(new ClickListener() {
                boolean dragged = true;

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("input", "item touch up");
                    setIconX(x);
                    setIconY(y);
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    dragged = false;
                    return false;
                }
            });
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            if (visible) {
                batch.draw(icon_texture, icon_pos.x, icon_pos.y, icon_width, icon_height);
            }
        }

        public void setIconX(float x) {
            this.icon_pos.x = x;
        }

        public void setIconY(float y) {
            this.icon_pos.y = y;
        }

        @Override
        public float getX() {
            return icon_pos.x;
        }

        @Override
        public float getY() {
            return icon_pos.y;
        }
    }
}
