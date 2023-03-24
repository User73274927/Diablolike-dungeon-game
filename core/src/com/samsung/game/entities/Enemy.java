package com.samsung.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.samsung.game.engine.LevelManager;
import com.samsung.game.items.Item;
import com.samsung.game.items.armor.Armor;

public abstract class Enemy extends Entity {
    private String name;
    private Item drop_item;

    private boolean isDead;

    public Enemy(float x, float y) {
        super(x, y);
        setView(new EnemyView());
        current_frame = new TextureRegion(new Texture("tiles/player-example1.png"));
        drop_item = new Armor(this);
        LevelManager.visible_components.add(drop_item);
        isDead = false;
        pos.x = x;
        pos.y = y;

    }

    public class EnemyView extends View {
        @Override
        public void draw(Batch batch) {
            if (!isDead) {
                batch.draw(current_frame, getX(), getY(), getWidth(), getHeight());
            }
        }
    }

    public void drop(Item item) {
        item.itemVisible(true);
    }

    public String getEnemyName() {
        return name;
    }

    public void setEnemyName(String name) {
        this.name = name;
    }

    @Override
    public void onDie() {
        synchronized (this) {
            drop(drop_item);
        }
        isDead = true;
        Entity.remove(this);
    }

}
