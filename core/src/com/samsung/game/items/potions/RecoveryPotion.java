package com.samsung.game.items.potions;

import com.samsung.game.entities.player.Player;

public class RecoveryPotion extends Potion {

    @Override
    public void use(Player player) {
        player.addHealth(30);
        player.addStamina(30);
    }
}
