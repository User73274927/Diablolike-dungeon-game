package com.samsung.game.effects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.samsung.game.DGame;
import com.samsung.game.engine.Damage;
import com.samsung.game.engine.Drawable;
import com.samsung.game.entities.Enemy;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;

import static com.samsung.game.data.Textures.SPRITES;

public class Burst implements Drawable, Damage {
    private Texture burst_texture;
    private Vector2 pos;
    private int radius;
    private int radius_max;

    public Burst(Vector2 pos, int radius_max) {
        this.pos = pos;
        this.radius_max = radius_max;
        burst_texture = new Texture(SPRITES+"player-example1.png");
    }

    @Override
    public void acceptDamage(Enemy enemy) {
        enemy.putDamage(30);
    }

    @Override
    public void acceptDamage(Player player) {

    }

    @Override
    public void draw(Batch batch) {
        batch.draw(burst_texture, pos.x-radius, pos.y-radius, radius*2, radius*2);

        for (Entity entity : DGame.data.allEntity) {
            float delta_s = pos.sub(entity.getCenterX(), entity.getCenterY()).len();
            if (delta_s <= radius) {
                acceptDamage(entity);
            }
        }

        radius += 2;
    }

    @Override
    public float getX() {
        return pos.x;
    }

    @Override
    public float getY() {
        return pos.y;
    }
}
