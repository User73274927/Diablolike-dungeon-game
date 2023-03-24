package com.samsung.game.items.armor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.Item;
import com.samsung.game.map.Tile;

public class Armor extends Item implements Equipable<Entity> {
    private Entity owner;

    public Armor(Entity owner) {
        texture = new Texture("sprites/armor-example1.png");
        setOwner(owner);
        icon_texture = texture;
        width = height = Tile.SIZE;
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

    @Override
    public void setOwner(Entity owner) {
        this.owner = owner;
        setItemSize(Tile.SIZE);
    }

    @Override
    public float getX() {
        return owner.getX();
    }

    @Override
    public float getY() {
        return owner.getY();
    }

    @Override
    public void onClick() {}
}
