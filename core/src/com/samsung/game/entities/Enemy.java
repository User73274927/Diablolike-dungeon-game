package com.samsung.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.samsung.game.engine.ItemGenerator;
import com.samsung.game.engine.LevelData;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.entities.player.PlayerObserver;
import com.samsung.game.items.Item;
import com.samsung.game.items.potions.Potion;

public abstract class Enemy extends Entity implements PlayerObserver {
    private String name;
    private Item drop_item;
    private boolean isDead;
    private ItemGenerator item_generator;

    public Enemy(LevelData data, float x, float y) {
        super(data, x, y);
        setEnemyName(getClass().getName());
        current_frame = new TextureRegion(new Texture("sprites/player-example1.png"));
        item_generator = new ItemGenerator();
        Potion potion = item_generator.generatePotion();
        drop_item = potion;

        isDead = false;
        pos.x = x;
        pos.y = y;

        onSpawn();
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
        data.visible_components.add(drop_item);
        drop_item.drop(getX(), getY());
        isDead = true;
        data.removeEntity(this);
    }

    @Override
    public void doAction(PlayerController controller) {
        controller.current_entity = this;
        System.out.println(getEnemyName() + " attacks player");
    }

}
