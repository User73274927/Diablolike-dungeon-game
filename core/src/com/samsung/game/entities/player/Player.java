package com.samsung.game.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.samsung.game.engine.Level;
import com.samsung.game.engine.LevelManager;
import com.samsung.game.engine.ProjectileManager;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Item;
import com.samsung.game.items.armor.Armour;
import com.samsung.game.items.armor.Helmet;
import com.samsung.game.items.potions.Potion;
import com.samsung.game.items.weapon.FireWeapon;
import com.samsung.game.items.projectiles.Fireball;
import com.samsung.game.map.Map;

import java.util.HashMap;

public class Player extends Entity {
    public static int MAX_STAMINA = 50;

    private TextureRegion[][] knight_frames;
    private HashMap<String, Animation<TextureRegion>> walkAnimationDict;
    private Animation<TextureRegion> current_animation;

    private TextureRegion current_frame;
    private ProjectileManager<Fireball> projectile_handler;
    private int speed;
    Sound shoot_sound;

    PlayerInventory inventory;
    Integer stamina;
    Integer level;


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(current_frame, getX(), getY(),
                getWidth(), getHeight()
        );
        if (inventory.armor != null) {
            inventory.armor.draw(batch);
        }
        inventory.draw(batch);
    }

    public Player(Map map) {
        super(map, 30, 30);
        inventory = new PlayerInventory(this);
        onSpawn();
    }

    @Override
    public void onSpawn() {
        final Texture knight_asset = new Texture("sprites/player-example1.png");
        shoot_sound = Gdx.audio.newSound(Gdx.files.internal("shoot-example1.mp3"));
        current_frame = new TextureRegion(knight_asset);
        projectile_handler = new ProjectileManager<>();

        inventory.setArmour(new Armour(Armour.Type.Iron));
        inventory.setHelmet(new Helmet(Helmet.Type.Iron));

        inventory.main_inventory.addItem(new Armour(Armour.Type.Leather));
        inventory.main_inventory.addItem(new Armour(Armour.Type.Iron));
        inventory.main_inventory.addItem(new Armour(Armour.Type.Diamond));
        inventory.main_inventory.addItem(new FireWeapon());
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
        health = Entity.MAX_HEALTH;
        stamina = Player.MAX_STAMINA;
        level = 1;
        speed = 5;
    }

    @Override
    public void update() {
        move();
        detectCollision();
    }

    public void updateClick(float mouse_x, float mouse_y) {
        for (Item item : Level.visible_components) {
            if (item.intersects(mouse_x, mouse_y) ) {
                putPotion(item);
                return;
            }
        }
        inventory.item_on_hand.onTouch(mouse_x, mouse_y);
        shoot_sound.play(1f);
    }

    private void putPotion(Item item) {
        boolean isPut;

        if (item instanceof Potion) {
            isPut = inventory.addPotionToBar((Potion) item);
        } else {
            isPut = inventory.main_inventory.addItem(item);
        }

        if (isPut) {
            Level.visible_components.remove(item);
        }
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
    public void onDie() {
        LevelManager.current_level.removeEntity(this);
    }

    public void addStamina(Integer points) {
        stamina = Math.min(MAX_STAMINA, stamina + points);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getStamina() {
        return stamina;
    }

}
