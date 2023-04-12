package com.samsung.game.engine;

import com.samsung.game.items.Item;
import com.samsung.game.items.armor.*;
import com.samsung.game.items.potions.*;

import java.util.function.Supplier;

public class ItemGenerator {
    private Class<?>[] item_types = {
        Armor.class, Helmet.class, Potion.class
    };

    public Potion generatePotion() {
        return Math.random() <= 0.5 ? new StaminaPotion() : new HealthPotion();
    }

}
