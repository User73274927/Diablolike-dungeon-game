package com.samsung.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.samsung.game.engine.Collideable;
import com.samsung.game.engine.Drawable;
import com.samsung.game.entities.player.Player;
import com.samsung.game.map.Tile;
import com.samsung.game.ui.UIComponent;
import com.samsung.game.utils.GameUtils;

import java.util.HashMap;

public abstract class Item implements Drawable, Collideable {
    protected String name;
    protected Texture texture;
    private final HashMap<String, UIView> ui_views;

    protected int height;
    protected int width;
    protected float x, y;

    public boolean isDestroy;
    public boolean visible;
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
        texture = icon_texture = new Texture("sprites/unknown.png");
        createUIView(UILocation.IN_INVENTORY);
        createUIView(UILocation.ON_SCREEN);
        isDestroy = false;
    }

    public void drop(float x, float y) {
        visible = true;
        this.x = x;
        this.y = y;
    }

    public void drop(Player player) {
        this.drop(player.getCenterX(), player.getCenterY());
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
        if (visible) {
            batch.draw(texture, getX(), getY(), width, height);
        }
    }

    public String getItemName() {
        return name;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public boolean intersects(float x, float y) {
        return Collideable.super.intersects(x, y) && visible;
    }

    public class UIView extends UIComponent {

        private Vector2 icon_pos;
        private int icon_width, icon_height;
        public boolean visible;

        UIView() {
            icon_pos = new Vector2();
            icon_width = icon_height = Tile.SIZE;
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

    public String info() {
        return "-";
    }
}
