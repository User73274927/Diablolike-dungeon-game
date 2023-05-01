package com.samsung.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.samsung.game.engine.gdx.ActorWrapper;
import com.samsung.game.entities.player.Player;
import com.samsung.game.utils.GameUtils;

public abstract class Item extends ActorWrapper {
    protected String name;
    protected Texture texture;
    protected Rectangle size;

    public boolean visible;
    protected Texture icon_texture;

    public Item() {
        size = new Rectangle();
        texture = icon_texture = new Texture("sprites/unknown.png");
    }

    public void drop(float x, float y) {
        visible = true;
        size.x = x;
        size.y = y;
    }

    public void drop(Player player) {
        this.drop(player.getCenterX(), player.getCenterY());
    }

    public Texture getIconTexture() {
        return icon_texture;
    }

    protected void setItemSize(int height) {
        size.width = (int) GameUtils.relatedFrom(size.height, size.width, height);
        size.height = height;
    }

    @Override
    public void draw(Batch batch, float pa) {
        batch.draw(texture, getX(), getY(), size.width, size.height);
    }

    public String getItemName() {
        return name;
    }

    @Override
    public float getX() {
        return size.x;
    }

    @Override
    public float getY() {
        return size.y;
    }

    @Override
    public float getHeight() {
        return size.height;
    }

    @Override
    public float getWidth() {
        return size.width;
    }

    public boolean intersects(float x, float y) {
        return size.contains(x, y) && isVisible();
    }

    public String info() {
        return "-";
    }

}
