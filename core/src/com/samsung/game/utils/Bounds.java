package com.samsung.game.utils;

public class Bounds {
    private float width, height;
    private float x, y;

    public Bounds(float width, float height, float x, float y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public float getCenterX() {
        return x + width / 2;
    }

    public float getCenterY() {
        return y + height / 2;
    }
}
