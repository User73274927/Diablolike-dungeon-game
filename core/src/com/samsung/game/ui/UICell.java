package com.samsung.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.Inventory;
import com.samsung.game.items.Item;
import com.samsung.game.map.Tile;

public class UICell extends UIComponent {
    private Texture frame;
    private int col, row;
    private Inventory<? extends Item> items;
    int width, height;

    public UICell(Inventory<? extends Item> items, int width, int height) {
        frame = new Texture("sprites/player-example1.png");
        this.items = items;
        this.width = width;
        this.height = height;

        setBounds(getX(), getY(), width, height);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(true);
                Item item = items.getItem(col, row);

                if (item != null & item instanceof Equipable<?>) {
                    ((Equipable<?>) item).onTouch(x, y);
                    items.remove(col, row);
                }

            }
        });
    }

    public UICell(Inventory<? extends Item> items) {
        this(items, Tile.SIZE, Tile.SIZE);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (items.getItem(col, row) != null) {
            batch.draw(items.getItem(col, row).getIconTexture(), getX(), getY(), width, height);
        }
        batch.draw(frame, getX(), getY(), width, height);
    }

    public void setCell(int col, int row) {
        this.row = row;
        this.col = col;
    }

}
