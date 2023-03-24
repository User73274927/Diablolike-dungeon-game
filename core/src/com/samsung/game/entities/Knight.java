package com.samsung.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.samsung.game.engine.ProjectileManager;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.armor.Helmet;
import com.samsung.game.items.weapon.FireWeapon;
import com.samsung.game.items.weapon.Fireball;
import com.samsung.game.items.armor.Armor;
import com.samsung.game.items.Inventory;
import com.samsung.game.map.Map;
import com.samsung.game.map.Tile;
import com.samsung.game.utils.PxNumber;

import java.util.HashMap;

public class Knight extends Entity {
    private TextureRegion[][] knight_frames;
    private HashMap<String, Animation<TextureRegion>> walkAnimationDict;
    private Animation<TextureRegion> current_animation;
    private KnightView view;

    private TextureRegion current_frame;
    private ProjectileManager<Fireball> projectile_handler;
    private Inventory inventory;
    private float time;

    private Equipable<? super Entity> used_item;
    private Armor helmet;
    private Armor armor;

    private int speed;
    private Map map;

    public enum State {
        EVADING, TELEPORT
    }

    public class KnightView extends View {
        private OrthographicCamera camera;

        public KnightView(OrthographicCamera camera) {
            super();
            this.camera = camera;
        }

        @Override
        public void draw(Batch batch) {
            updateAttack();
            batch.draw(current_frame, pos.x, pos.y,
                    getWidth(), getHeight()
            );

            if (used_item instanceof FireWeapon) {
                ((FireWeapon) used_item).draw(batch);
            }

            PxNumber n = new PxNumber(health, (int) camera.position.x + 150, (int) camera.position.y + 150, 30);
            n.draw(batch);

            helmet.setIconX(camera.position.x - camera.viewportWidth / 2 + 10);
            helmet.setIconY(camera.position.y + camera.viewportHeight / 2 - Tile.SIZE - 10);

            armor.setIconX(camera.position.x - camera.viewportWidth / 2 + 10);
            armor.setIconY(camera.position.y + camera.viewportHeight / 2 - Tile.SIZE - 50);

            armor.draw(batch);
            helmet.draw(batch);
        }
    }

    public Knight(OrthographicCamera camera, Map map) {
        super(30, 30);
        this.view = new KnightView(camera);
        setView(view);
        this.map = map;
        start();
    }

    @Override
    public void onSpawn() {
        final Texture knight_asset = new Texture("tiles/player-example1.png");
        current_frame = new TextureRegion(knight_asset);
        projectile_handler = new ProjectileManager<>();

        used_item = new FireWeapon();
        used_item.setOwner(this);

        helmet = new Helmet(this);
        helmet.iconVisible(true);

        armor = new Armor(this);
        armor.iconVisible(true);
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
        speed = 5;
        health = 100;
    }

    @Override
    public void update() {
        move();
        detectCollision(map);
    }

    private void updateAttack() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            used_item.onClick();
        }
    }

    @Override
    public float getX() {
        return pos.x;
    }

    @Override
    public float getY() {
        return pos.y;
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

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void onDie() {

    }

    @Override
    public int getWidth() {
        return Tile.SIZE;
    }

    @Override
    public int getHeight() {
        return Tile.SIZE;
    }

}
