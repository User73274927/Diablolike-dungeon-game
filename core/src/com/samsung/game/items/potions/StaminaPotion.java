package com.samsung.game.items.potions;

import com.badlogic.gdx.graphics.Texture;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;
import com.samsung.game.items.Equipable;

public class StaminaPotion extends Potion {
    private Player owner;

    public StaminaPotion() {
        texture = icon_texture = new Texture("sprites/stamina-potion.png");
    }

    @Override
    public void use(Player player) {
        player.addStamina(30);
    }

}
