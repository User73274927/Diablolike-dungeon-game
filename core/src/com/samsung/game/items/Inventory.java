package com.samsung.game.items;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.samsung.game.engine.Component;

public class Inventory {
    private Item[][] container;

    public boolean addItem(Item item) {
        for (int i = 1; i <= container.length; i++) {
            for (int j = 1; j < container[0].length; j++) {
                if (this.addItem(item, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addItem(Item item, int col_num, int row_num) {
        col_num--; row_num--;
        if (container[col_num][row_num] != null) {
            return false;
        }
        container[col_num][row_num] = item;
        return true;
    }
}
