package com.samsung.game.entities.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.samsung.game.engine.LevelManager;
import com.samsung.game.engine.ProjectileManager;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Item;
import com.samsung.game.items.armor.Helmet;
import com.samsung.game.items.potions.Potion;
import com.samsung.game.items.weapon.FireWeapon;
import com.samsung.game.items.weapon.Fireball;
import com.samsung.game.items.armor.Armor;
import com.samsung.game.map.Map;
import com.samsung.game.map.Tile;

import java.util.HashMap;

public class Player extends Entity {
    private TextureRegion[][] knight_frames;
    private HashMap<String, Animation<TextureRegion>> walkAnimationDict;
    private Animation<TextureRegion> current_animation;
    private KnightView view;

    private TextureRegion current_frame;
    private ProjectileManager<Fireball> projectile_handler;

    private int speed;
    private Map map;

    PlayerInventory inventory;
    Integer stamina;
    Integer level;

    public class KnightView extends View {
        @Override
        public void draw(Batch batch) {
            batch.draw(current_frame, pos.x, pos.y,
                    getWidth(), getHeight()
            );
            inventory.draw(batch);
        }

    }

    public Player(Map map) {
        super(30, 30);
        this.map = map;
        inventory = new PlayerInventory(this);
        view = new KnightView();
        setView(view);

        onSpawn();
        start();
    }

    @Override
    public void onSpawn() {
        final Texture knight_asset = new Texture("sprites/player-example1.png");
        current_frame = new TextureRegion(knight_asset);
        projectile_handler = new ProjectileManager<>();

        inventory.setHelmet(new Helmet());
        inventory.setArmor(new Armor());
        inventory.setItemOnHand(new FireWeapon());

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
        health = Entity.MAX_HEALTH;
    }

    @Override
    public void update() {
        move();
        detectCollision(map);
    }

    public void updateClick(float mouse_x, float mouse_y) {
        for (Item item : LevelManager.visible_components) {
            if (item.intersects(mouse_x, mouse_y)) {
                putPotion(item);
                return;
            }
        }
        inventory.item_on_hand.onClick();
    }

    private void putPotion(Item item) {
        boolean isPut;

        if (item instanceof Potion) {
            isPut = inventory.addPotionToBar((Potion) item);
        } else {
            isPut = inventory.inventory.addItem(item);
        }

        if (isPut) {
            LevelManager.visible_components.remove(item);
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
        Entity.remove(this);
    }

    @Override
    public KnightView getView() {
        return view;
    }

    @Override
    public int getWidth() {
        return Tile.SIZE;
    }

    @Override
    public int getHeight() {
        return Tile.SIZE;
    }

    public int getStamina() {
        return stamina;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

}
