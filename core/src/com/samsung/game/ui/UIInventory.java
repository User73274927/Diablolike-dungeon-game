package com.samsung.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.Inventory;
import com.samsung.game.items.Item;
import com.samsung.game.map.Tile;

public class UIInventory extends Group {
    public boolean isOpened;
    private Slot[][] slots;
    private Inventory<? extends Item> inventory;
    public int indent;

    public UIInventory(Inventory<? extends Item> inventory) {
        this.inventory = inventory;
        slots = new Slot[inventory.cols()][inventory.rows()];
        isOpened = true;
        init();
    }

    private void init() {
        for (int i = 0; i < inventory.cols(); i++) {
            for (int j = 0; j < inventory.rows(); j++) {
                Item item = inventory.getItem(i + 1, j + 1);

                if (item != null) {
                    item.findUIView(Item.UILocation.IN_INVENTORY).visible = true;
                }

                slots[i][j] = new Slot();
                Slot slot = slots[i][j];
                slot.setInventoryCell(i + 1, j + 1);
                addActor(slot);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void update() {
        final float x = getX();
        final float y = getY() + getHeight();

        float lx = x;
        float ly = y;

        for (int i = 0; i < inventory.cols(); i++) {
            ly -= slots[i][0].height + indent;

            for (int j = 0; j < inventory.rows(); j++) {
                Slot slot = slots[i][j];

                slot.setX(lx);
                slot.setY(ly);

                lx += slot.getWidth() + indent;
            }
            lx = x;
        }
    }

    @Override
    public float getWidth() {
        return (slots[0][0].width + indent) * slots[0].length - indent;
    }

    @Override
    public float getHeight() {
        return (slots[0][0].height + indent) * slots.length - indent;
    }

    public void setIsOpened(boolean isOpened) {
        this.isOpened = isOpened;
        setVisible(isOpened);
    }

    public class Slot extends UIComponent {
        private Texture frame;
        public int width, height;
        private int col, row;

        public Slot(int width, int height) {
            frame = new Texture("sprites/player-example1.png");
            this.width = width;
            this.height = height;
            setBounds(getX(), getY(), width, height);

            addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Item item = inventory.getItem(col, row);

                    if (item != null & item instanceof Equipable<?>) {
                        ((Equipable<?>) item).onTouch(x, y);
                        inventory.remove(col, row);
                    }
                }
            });
        }

        public Slot() {
            this(Tile.SIZE, Tile.SIZE);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            batch.draw(frame, getX(), getY(), width, height);
            if (inventory.getItem(col, row) != null) {
                batch.draw(inventory.getItem(col, row).getIconTexture(), getX(), getY(), getWidth(), getHeight());
            }
        }

        @Override
        public void update() {

        }

        public void setInventoryCell(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }
}
