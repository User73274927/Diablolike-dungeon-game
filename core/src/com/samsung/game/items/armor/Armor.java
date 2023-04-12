package com.samsung.game.items.armor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.Item;
import com.samsung.game.map.Tile;

public abstract class Armor extends Item {
    private Entity owner;
    public Integer protection;

    public Armor() {
        texture = new Texture("sprites/armor-example1.png");
        icon_texture = texture;
        width = height = Tile.SIZE;
    }

    @Override
    public float getX() {
        if (owner == null) {
            visible = false;
            return -1;
        }
        return owner.getX();
    }

    @Override
    public float getY() {
        if (owner == null) {
            visible = false;
            return -1;
        }
        return owner.getY();
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
        setItemSize((int) owner.getHeight());
    }

    @Override
    public String info() {
        return "protection: " + protection + "\n";
    }
}
