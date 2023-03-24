package com.samsung.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.samsung.game.engine.Component;
import com.samsung.game.map.Tile;
import com.samsung.game.utils.GameUtils;

public abstract class Item implements Component {
    protected Texture texture;
    protected int height;
    protected int width;

    private boolean icon_visible;
    private boolean item_visible;

    protected Texture icon_texture;
    private int icon_size;
    protected Vector2 icon_pos;

    public Item() {
        icon_pos = new Vector2();
        icon_size = Tile.SIZE;
    }

    @Override
    public void draw(Batch batch) {
        if (icon_visible) {
            batch.draw(icon_texture, icon_pos.x, icon_pos.y, icon_size, icon_size);
        }
        if (item_visible) {
            batch.draw(texture, getX(), getY(), width, height);
        }
    }

    public Texture getIconTexture() {
        return icon_texture;
    }

    public void iconVisible(boolean icon_visible) {
        this.icon_visible = icon_visible;
    }

    public void itemVisible(boolean item_visible) {
        this.item_visible = item_visible;
    }

    public void setIconX(float x) {
        this.icon_pos.x = x;
    }

    public void setIconY(float y) {
        this.icon_pos.y = y;
    }

    protected void setItemSize(int height) {
        this.width = (int) GameUtils.relatedFrom(this.height, width, height);
        this.height = height;
    }

}
