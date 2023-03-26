package com.samsung.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.samsung.game.map.Tile;

public class UICell extends UIComponent {
    private Texture frame;
    private Texture item;
    int width, height;

    public UICell(int width, int height) {
        frame = new Texture("sprites/player-example1.png");

        this.width = width;
        this.height = height;
    }

    public UICell() {
        this(Tile.SIZE, Tile.SIZE);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (item != null) {
            batch.draw(item, getX(), getY(), width, height);
        }
        batch.draw(frame, getX(), getY(), width, height);
    }

    public void addTexture(Texture item) {
        if (item != null) {
            this.item = item;
        }
    }

}
