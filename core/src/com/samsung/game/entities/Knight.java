package com.samsung.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Rectangle;
import com.samsung.game.engine.AnimationComponent;
import com.samsung.game.engine.Damage;
import com.samsung.game.items.weapon.FireWeapon;
import com.samsung.game.items.weapon.Fireball;
import com.samsung.game.map.Map;

import java.util.HashMap;
import java.util.Optional;

public class Knight extends PhysicalEntity implements AnimationComponent {
    private TextureRegion[][] knight_frames;
    private HashMap<String, Animation<TextureRegion>> walkAnimationDict;
    private Animation<TextureRegion> current_animation;

    private TextureRegion current_frame;
    private float time;
    private Damage weapon;
    private FireWeapon weapon1;

    public float lx, ly;
    private int knight_speed;
    private int speed;
    private Map map;
    private Fireball fireball;

    public Knight(Map map, int size, int x, int y) {
        super(100, 50, 25, 30);
        this.map = map;
        this.direction = Direction.STOP;
        getPos().add(x, y);
        speed = knight_speed = 5;
        start();
    }
    @Override
    public void onSpawn(float x, float y) {
        final Texture knight_asset = new Texture("tiles/player-example1.png");
        current_frame = new TextureRegion(knight_asset);
        weapon1 = new FireWeapon(map);
//        walkAnimationDict = new HashMap<>();
//
//        knight_frames = TextureRegion.split(knight_asset,
//                knight_asset.getWidth() / 4,
//                knight_asset.getHeight() / 4
//        );
//
//        walkAnimationDict.put("left", new Animation<>(0.25f, knight_frames[2]));
//        walkAnimationDict.put("right", new Animation<>(0.25f, knight_frames[1]));
//
//        current_animation = walkAnimationDict.get("right");
//        current_frame = current_animation.getKeyFrame(time);
        health = 100;
    }

    @Override
    public void update() {
        move();
        weapon1.x = pos.x;
        weapon1.y = pos.y;
        detectCollision(map);
    }

    @Override
    public void draw(Batch batch) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            weapon1.shoot(new Fireball(map, 30, 30, pos.x, pos.y, 77));
        }
        weapon1.draw(batch);
        batch.draw(current_frame, getPos().x, getPos().y,
                getWidth(), getHeight()
        );
    }

    private void move() {
        switch (direction) {
            case UP:
                pos.y += speed;
                break;
            case DOWN:
                pos.y -= speed;
                break;
            case LEFT:
                pos.x -= speed;
                break;
            case RIGHT:
                pos.x += speed;
                break;
        }
    }

    @Override
    public void updateFrame() {

    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void onDie() {

    }
}
