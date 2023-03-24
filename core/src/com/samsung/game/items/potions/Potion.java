package com.samsung.game.items.potions;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.Item;

public abstract class Potion extends Item implements Equipable<Entity> {
    protected Entity owner;

    @Override
    public void draw(Batch batch) {

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
    public void setOwner(Entity owner) {
        this.owner = owner;
    }
}
