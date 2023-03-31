package com.samsung.game.items;

public interface Equipable<T> {
    public void setOwner(T owner);
    public void onTouch(float screen_x, float screen_y);
}
