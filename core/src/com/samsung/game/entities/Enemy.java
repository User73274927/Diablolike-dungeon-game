package com.samsung.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.samsung.game.engine.ItemGenerator;
import com.samsung.game.engine.Level;
import com.samsung.game.engine.LevelManager;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.items.Item;
import com.samsung.game.items.potions.Potion;
import com.samsung.game.map.Map;

public abstract class Enemy extends Entity {
    private String name;
    private Item drop_item;
    private boolean isDead;
    private ItemGenerator item_generator;

    public Enemy(Map map, float x, float y) {
        super(map, x, y);
        current_frame = new TextureRegion(new Texture("sprites/player-example1.png"));
        item_generator = new ItemGenerator();
        Potion potion = item_generator.generatePotion();
        drop_item = potion;

        isDead = false;
        pos.x = x;
        pos.y = y;

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("dddddd");
                PlayerController.current_entity = getEntity();
            }
        });
        onSpawn();
    }

    private Entity getEntity() {
        return this;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!isDead) {
            batch.draw(current_frame, getX(), getY(), getWidth(), getHeight());
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
        Level.visible_components.add(drop_item);
        drop_item.drop(getX(), getY());
        isDead = true;
        LevelManager.current_level.removeEntity(this);
    }

}
