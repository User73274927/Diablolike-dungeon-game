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

        //player
        addAnimation("hero-walk-left", 0.07f, textures.getTexture("sprites/player-sheet-left.png"), 5);
        addAnimation("hero-walk-right", 0.07f, textures.getTexture("sprites/player-sheet-right.png"), 5);
        addAnimation("hero-attack", 1f, textures.getTexture("sprites/player-sheet-attack.png"), 2);

        //enemies
        addAnimation("monster1-right", 0.09f, textures.getTexture("sprites/monster1-idle-right.png"), 8);
        getAnimation("monster1-right").setPlayMode(Animation.PlayMode.LOOP);
        addAnimation("monster1-left", 0.09f, textures.getTexture("sprites/monster1-idle-left.png"), 8);
        getAnimation("monster1-left").setPlayMode(Animation.PlayMode.LOOP);
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
