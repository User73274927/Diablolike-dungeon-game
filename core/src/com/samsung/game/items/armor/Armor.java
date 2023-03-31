package com.samsung.game.items.armor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.Item;
import com.samsung.game.map.Tile;

public class Armor extends Item implements Equipable<Entity> {
    private Entity owner;

    public Armor() {
        texture = new Texture("sprites/armor-example1.png");
        setOwner(owner);
        icon_texture = texture;
        width = height = Tile.SIZE;
    }

    @Override
    public void setOwner(Entity owner) {
        this.owner = owner;
        setItemSize(Tile.SIZE);
    }

    @Override
    public void onTouch(float screen_x, float screen_y) {}
}
