package com.samsung.game.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.items.Inventory;
import com.samsung.game.items.Item;

public class UIItemBar extends UIComponent {
    Inventory<? extends Item> items;
    UICell[] cells;
    int indent;

    public UIItemBar(int indent) {
        this.indent = indent;
    }

    public UIItemBar(int indent, Inventory<? extends Item> items) {
        this(indent);
        this.items = items;
        cells = new UICell[items.rows()];

        for (int i = 0; i < cells.length; i++) {
            cells[i] = new UICell();
            Item item = items.getItem(0, i);

            if (item != null) {
                cells[i].addTexture(item.getIconTexture());
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        float x = getX();
        float y = getY();

        for (UICell cell : cells) {
            cell.setX(x);
            cell.setY(y);

            cell.draw(batch, parentAlpha);

            x += indent + cell.width;
        }
    }

    @Override
    public float getWidth() {
        return cells[0].width * cells.length + indent * (cells.length - 1);
    }

    @Override
    public float getHeight() {
        return cells[0].height;
    }

    @Override
    public void update() {
        for (int i = 0; i < cells.length; i++) {
            Item item = items.getItem(0, i);
            if (item != null) {
                cells[i].addTexture(item.getIconTexture());
            }
        }
    }
}
