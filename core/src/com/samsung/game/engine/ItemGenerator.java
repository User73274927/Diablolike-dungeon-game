package com.samsung.game.engine;

import com.samsung.game.items.armor.Armor;
import com.samsung.game.items.armor.Helmet;
import com.samsung.game.items.potions.HealthPotion;
import com.samsung.game.items.potions.Potion;
import com.samsung.game.items.potions.ManaPotion;

public class ItemGenerator {
    private Class<?>[] item_types = {
        Armor.class, Helmet.class, Potion.class
    };

    public Potion generatePotion() {
        if (Math.random() <= 0.25) {
            return Math.random() <= 0.5 ? new ManaPotion() : new HealthPotion();
        }
        return null;
    }

}
