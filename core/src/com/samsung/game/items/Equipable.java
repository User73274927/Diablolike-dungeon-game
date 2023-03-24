package com.samsung.game.items;

import com.samsung.game.engine.Component;
import com.samsung.game.entities.Entity;

public interface Equipable<T> {
    public void setOwner(T owner);
    public void onClick();
}
