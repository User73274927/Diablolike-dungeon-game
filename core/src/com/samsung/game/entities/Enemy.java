package com.samsung.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.samsung.game.engine.ItemGenerator;
import com.samsung.game.engine.LevelManager;
import com.samsung.game.items.Item;
import com.samsung.game.items.potions.Potion;

public abstract class Enemy extends Entity {
    private String name;
    private Item drop_item;
    private boolean isDead;
    private ItemGenerator item_generator;


    public Enemy(float x, float y) {
        super(x, y);
        current_frame = new TextureRegion(new Texture("sprites/player-example1.png"));
        item_generator = new ItemGenerator();
        Potion potion = new Potion() {
            @Override
            public void onTouch(float screen_x, float screen_y) {
                owner.addHealth(30);
            }
        };
        potion.setOwner(this);
        drop_item = potion;

        isDead = false;
        pos.x = x;
        pos.y = y;

        onSpawn();
    }

    public class EnemyView extends View {
        @Override
        public void draw(Batch batch) {
            if (!isDead) {
                batch.draw(current_frame, getX(), getY(), getWidth(), getHeight());
            }
        }
    }

    public String getEnemyName() {
        return name;
    }

    public void setEnemyName(String name) {
        this.name = name;
    }

    @Override
    public void onDie() {
        LevelManager.visible_components.add(drop_item);
        drop_item.drop(getX(), getY());
        isDead = true;
        Entity.remove(this);
    }

}
