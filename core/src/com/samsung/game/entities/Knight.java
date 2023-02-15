package com.samsung.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.samsung.game.engine.AnimationComponent;
import com.samsung.game.engine.Damage;

import java.util.HashMap;

public class Knight extends Entity implements AnimationComponent {
    private TextureRegion[][] knight_frames;
    private HashMap<String, Animation<TextureRegion>> walkAnimationDict;
    private Animation<TextureRegion> current_animation;
    private TextureRegion current_frame;
    private float time;

    private Damage weapon;
    public int x, y;
    private int speed;

    @Override
    public void onSpawn() {
        final Texture knight_asset = new Texture("sprites/player.png");
        walkAnimationDict = new HashMap<>();

        knight_frames = TextureRegion.split(knight_asset,
                knight_asset.getWidth() / 4,
                knight_asset.getHeight() / 4
        );

        walkAnimationDict.put("left", new Animation<>(0.25f, knight_frames[1]));
        walkAnimationDict.put("right", new Animation<>(0.25f, knight_frames[2]));

        current_animation = walkAnimationDict.get("right");
        current_frame = current_animation.getKeyFrame(time);
        health = 100;
    }

    @Override
    public void update() {
        getPos().y = y;
        getPos().x = x;
    }

    private void move() {

    }

    @Override
    public void draw(Batch batch) {
        batch.draw(current_frame, getPos().x, getPos().y);
    }

    public void attack(Entity entity) {
        weapon.acceptDamage(entity);
    }

    @Override
    public void updateFrame() {
        time += Gdx.graphics.getDeltaTime();
        current_frame = current_animation.getKeyFrame(time);
    }

    @Override
    public void onDie() {
        System.out.println("onDie works");
    }
}
