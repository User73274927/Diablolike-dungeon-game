package com.samsung.game.engine;

public interface Collideable {
    public int getWidth();
    public int getHeight();
    public float getX();
    public float getY();

    default boolean intersects(float x, float y) {
        return x > getX() && x < getX() + getWidth() &&
                y > getY() && y < getY() + getHeight();
    }

    default boolean overlaps(Collideable c) {
        return getX() < c.getX() + c.getWidth() && getX() + getWidth() > c.getX() &&
                getY() < c.getY() + c.getHeight() && getY() + getHeight() > c.getY();
    }
}
