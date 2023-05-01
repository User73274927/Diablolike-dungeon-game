package com.samsung.game.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.samsung.game.DGame;
import com.samsung.game.engine.ProjectileManager;
import com.samsung.game.engine.gdx.ActorWrapper;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Item;
import com.samsung.game.items.armor.Armour;
import com.samsung.game.items.armor.Helmet;
import com.samsung.game.items.potions.Potion;
import com.samsung.game.items.projectiles.BouncedProjectile;
import com.samsung.game.items.projectiles.Fireball;
import com.samsung.game.items.weapon.FireWeapon;
import com.samsung.game.items.weapon.Weapon;
import com.samsung.game.map.Wall;
import com.samsung.game.utils.DebugConsole;
import com.samsung.game.utils.TestAlgorithms;

public class Player extends Entity {
    public int MAX_STAMINA = 50;

    private TextureRegion[][] knight_frames;
    private Rectangle interaction_field;

    private ProjectileManager<Fireball> projectile_handler;
    private int speed;
    Sound shoot_sound;

    PlayerInventory inventory;
    Integer stamina;
    Integer level;
    Integer exp;

    public Player() {
        super(30, 30);
        onCreate();
    }

    @Override
    public void onCreate() {
        inventory = new PlayerInventory(this);
        final Animation<TextureRegion> knight_asset = DGame.animations.getAnimation("hero");
        walkAnimationDict.put("right", knight_asset);
        current_animation = walkAnimationDict.get("right");

        shoot_sound = Gdx.audio.newSound(Gdx.files.internal("shoot-example1.mp3"));
        interaction_field = new Rectangle();
        interaction_field.width = getWidth()*7;
        interaction_field.height = getWidth()*7;

        current_frame = knight_asset.getKeyFrames()[0];
        projectile_handler = new ProjectileManager<>();

        inventory.setArmour(new Armour(Armour.Type.Iron));
        inventory.setHelmet(new Helmet(Helmet.Type.Iron));

        inventory.main_inventory.addItem(new Armour(Armour.Type.Leather));
        inventory.main_inventory.addItem(new Armour(Armour.Type.Iron));
        inventory.main_inventory.addItem(new Armour(Armour.Type.Diamond));
        inventory.main_inventory.addItem(new FireWeapon());
        FireWeapon weapon = new FireWeapon();
        weapon.setProjectile(new BouncedProjectile(this));
        inventory.setItemOnHand(weapon);

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
        health = MAX_HEALTH;
        stamina = MAX_STAMINA;
        exp = 0;
        level = 1;
        body.MAX_VEL = speed = 5;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        update();
        batch.draw(current_frame, body.getX(), body.getY(),
                getWidth(), getHeight()
        );

        if (inventory.item_on_hand != null && inventory.item_on_hand instanceof Weapon) {
            ((Weapon) inventory.item_on_hand).draw(batch, 0f);
        }
        DebugConsole.addMessage("fff",
                "player x: " + Math.round(getCenterX()) +
                        "player y: " + Math.round(getCenterY())
        );
        if (DGame.data.map.getTiledMap()[10][4] instanceof Wall)
            TestAlgorithms.test1(this, (Wall) DGame.data.map.getTiledMap()[10][4]);
    }

    @Override
    public void update() {
        super.update();
        interaction_field.x = getCenterX() - interaction_field.width / 2;
        interaction_field.y = getCenterY() - interaction_field.height / 2;
        //detectCollision();
    }

    public void updateClick(float mouse_x, float mouse_y) {
        for (Item item : DGame.data.visible_items) {
            if (item.intersects(mouse_x, mouse_y) && interactsWith(item)) {
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
            DGame.data.visible_items.remove(item);
        }
    }

    @Override
    public void onDestroy() {
        DGame.data.removeEntity(this);
    }

    public boolean interactsWith(ActorWrapper actor) {
        return interaction_field.contains(actor.getCenterX(), actor.getCenterY());
    }

    public void addStamina(Integer points) {
        stamina = Math.min(MAX_STAMINA, stamina + points);
    }

    public int getStamina() {
        return stamina;
    }

}
