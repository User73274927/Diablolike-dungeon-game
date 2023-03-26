package com.samsung.game.items;

public interface Equipable<T> {
    public void setOwner(T owner);
    public void onClick();
}
