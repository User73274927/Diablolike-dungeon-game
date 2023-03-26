package com.samsung.game.items;

import java.util.ArrayList;

public class Inventory<T extends Item> {
    private Item[][] container;

    public Inventory(int cols, int rows) {
        this.container = new Item[cols][rows];
    }

    public boolean addItem(T item) {
        for (int i = 1; i <= container.length; i++) {
            for (int j = 1; j < container[0].length; j++) {
                if (this.addItem(item, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addItem(T item, int col_num, int row_num) {
        col_num--; row_num--;
        if (container[col_num][row_num] != null) {
            return false;
        }
        container[col_num][row_num] = item;
        return true;
    }

    public int rows() {
        return container[0].length;
    }

    public T getItem(int col, int row) {
        return (T) container[col][row];
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();

        for (Item[] col : container) {
            for (Item item : col) {
                if (item == null) {
                    list.append("null ");
                } else {
                    list.append("obj ");
                }
            }
            list.append('\n');
        }
        return list.toString();
    }
}
