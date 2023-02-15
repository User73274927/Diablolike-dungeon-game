package com.samsung.game.data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;
import java.util.HashMap;

public class KnightResources extends ResourceManager {
    private Texture knight_atlas;
    private TextureRegion[][] knight_frames;
    private Map<String, Animation<TextureRegion>> animations;

    public KnightResources() {
        System.out.println("Hello");
    }

    private void method(int a) {
        int aa = a;
    }
}
