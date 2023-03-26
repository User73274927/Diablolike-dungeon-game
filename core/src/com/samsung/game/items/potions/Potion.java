package com.samsung.game.items.potions;

import com.badlogic.gdx.graphics.Texture;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.Item;
import com.samsung.game.map.Tile;

public abstract class Potion extends Item implements Equipable<Entity> {
    protected Entity owner;

    public Potion() {
        texture = icon_texture = new Texture("sprites/potion-example1.png");
        width = height = Tile.SIZE;
    }

    @Override
    public void setOwner(Entity owner) {
        this.owner = owner;
    }

}
