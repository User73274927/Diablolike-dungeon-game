package com.samsung.game.items.armor;

import com.badlogic.gdx.graphics.Texture;
import com.samsung.game.entities.Entity;

public class Helmet extends Armor {
    public Helmet(Entity owner) {
        super(owner);
        texture = new Texture("sprites/helmet-example1.png");
        icon_texture = texture;
    }
}
