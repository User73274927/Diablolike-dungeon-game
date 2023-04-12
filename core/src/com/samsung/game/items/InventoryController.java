package com.samsung.game.items;

public class InventoryController<T extends Item> {
    @FunctionalInterface
    public interface Action<T> {
        void action(T item, int col, int row);
    }

    private final Inventory<T> inventory;
    private Action<T> onTouchAction;
    private Action<T> onDoubleTouchAction;

    public InventoryController(Inventory<T> inventory) {
        this.inventory = inventory;
    }

    public Inventory<T> getInventory() {
        return inventory;
    }

    public final void onTouch(int col, int row) {
        inventory.getItem(col, row).ifPresent(item -> {
            if (onTouchAction != null) {
                onTouchAction.action(item, col, row);
            }
        });
    }

    public final void onDoubleTouch(int col, int row) {
        inventory.getItem(col, row).ifPresent(item -> {
            if (onDoubleTouchAction != null) {
                onDoubleTouchAction.action(item, col, row);
            }
        });
    }

    public final void setOnDoubleTouchAction(Action<T> action) {
        this.onDoubleTouchAction = action;
    }

    public final void setOnTouchAction(Action<T> action) {
        this.onTouchAction = action;
    }
}