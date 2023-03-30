package com.samsung.game.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.samsung.game.items.Inventory;
import com.samsung.game.items.Item;

public class UIItemBar extends Group {
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

        for (int i = 0; i < items.rows(); i++) {
            Item item = items.getItem(0, i);
            if (item != null)
                item.findUIView(Item.UILocation.IN_INVENTORY).visible = true;

            cells[i] = new UICell(items);
            cells[i].setCell(0, i);
            addActor(cells[i]);
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

    public void update() {
        float x = getX();
        float y = getY();

        for (int i = 0; i < getChildren().size; i++) {
            UICell cell = (UICell) getChildren().get(i);

            cell.setX(x);
            cell.setY(y);

            x += cell.getWidth() + indent;
        }
    }
}
