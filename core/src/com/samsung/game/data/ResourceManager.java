package com.samsung.game.data;

import com.badlogic.gdx.graphics.Texture;

public abstract class ResourceManager {
    private Texture default_frame;

    public ResourceManager() {

    }

    public void setDefaultFrame(Texture default_frame) {
        this.default_frame = default_frame;
    }
}
