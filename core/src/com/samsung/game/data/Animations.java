package com.samsung.game.data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.samsung.game.DGame;

import java.util.HashMap;

public class Animations {
    private HashMap<String, Animation<TextureRegion>> animation_dict;
    private Textures textures;

    public Animations() {
        textures = DGame.textures;
        animation_dict = new HashMap<>();

        addAnimation("hero-walk-left", 0.07f, textures.getTexture("sprites/player-sheet-left.png"), 5);
        addAnimation("hero-walk-right", 0.07f, textures.getTexture("sprites/player-sheet-right.png"), 5);
        addAnimation("hero-attack", 0.14f, textures.getTexture("sprites/player-sheet-attack.png"), 2);
    }

    public void addAnimation(String tag, float frame_duration, Texture texture, int rows) {
        TextureRegion[][] region = TextureRegion.split(texture, texture.getWidth() / rows, texture.getHeight());
        Animation<TextureRegion> anim = new Animation<>(frame_duration, region[0]);
        animation_dict.put(tag, anim);
    }

    public Animation<TextureRegion> getAnimation(String tag) {
        return animation_dict.get(tag);
    }
}
