package com.samsung.game.items.potions;

import com.badlogic.gdx.graphics.Texture;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;

public class HealthPotion extends Potion {

    public HealthPotion() {
        texture = icon_texture = new Texture("sprites/health-potion.png");
    }

    @Override
    public void use(Player player) {
        player.addHealth(40);
    }

}
