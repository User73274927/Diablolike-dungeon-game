package com.samsung.game.items.potions;

import com.samsung.game.entities.Entity;
import com.samsung.game.entities.Knight;

public enum Potions {
    HEALTH("health-potion", new Potion() {
        @Override
        public void onClick() {
            owner.addHealth(30);
        }
    }),

    STAMINA("stamina-potion", new Potion() {
        @Override
        public void onClick() {
            owner.addStamina(30);
        }
    });

    private String name;
    private Potion potion;

    private Potions(String name, Potion potion) {
        this.name = name;
        this.potion = potion;
    }
}
