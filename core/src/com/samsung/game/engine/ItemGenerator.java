package com.samsung.game.engine;

import com.samsung.game.items.armor.Armor;
import com.samsung.game.items.armor.Helmet;
import com.samsung.game.items.potions.HealthPotion;
import com.samsung.game.items.potions.Potion;
import com.samsung.game.items.potions.StaminaPotion;

public class ItemGenerator {
    private Class<?>[] item_types = {
        Armor.class, Helmet.class, Potion.class
    };

    public Potion generatePotion() {
        return Math.random() <= 0.5 ? new StaminaPotion() : new HealthPotion();
    }

}
