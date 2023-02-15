package com.samsung.game.engine;

import com.samsung.game.entities.Knight;

public class EntityFactory {
    public static Knight createPlayer() {
        Knight knight = new Knight();
        EntityBehaviorManager manager = new EntityBehaviorManager(knight);
        EntityBehaviorManager.add(manager, knight);
        return knight;
    }

}
