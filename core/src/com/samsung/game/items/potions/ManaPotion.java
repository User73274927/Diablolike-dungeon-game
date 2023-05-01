package com.samsung.game.items.potions;

import com.badlogic.gdx.graphics.Texture;
import com.samsung.game.entities.player.Player;

public class ManaPotion extends Potion {

    public ManaPotion() {
        texture = icon_texture = new Texture("sprites/stamina-potion.png");
    }

    @Override
    public void use(Player player) {
        player.addStamina(30);
    }

    @Override
    public String getItemName() {
        return "Mana potion";
    }

    @Override
    public String info() {
        return "recover 30 ed of \nmana";
    }
}
