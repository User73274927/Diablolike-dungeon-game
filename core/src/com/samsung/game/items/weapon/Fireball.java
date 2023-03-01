package com.samsung.game.items.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.samsung.game.engine.AnimationComponent;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.Knight;
import com.samsung.game.map.Map;

public class Fireball extends Shell implements AnimationComponent {
    private static Texture weapon_texture = new Texture("sprites/fireball-example1.png");
    private Map map;
    private Rectangle hitbox;
    private int angle;
    private Vector2 pos;
    private float speed;

    public Fireball(Map map, float damage_min, float damage_max, float x, float y, int angle) {
        super(damage_min, damage_max);
        this.map = map;
        this.hitbox = new Rectangle(x, y, 20, 20);
        this.pos = new Vector2();
        this.pos.add(x, y);
        this.angle = angle;
        this.speed = 5;
    }

    public void update() {
        for (Entity entity : Entity.all()) {
            if (entity.getClass() == Knight.class) continue;
            if (overlaps(entity)) {
                acceptDamage(entity);
            }
        }
        float sin_speed = (float) (speed * Math.sin(angle));
        float cos_speed = (float) (speed * Math.cos(angle));

        pos.x += sin_speed;
        pos.y += cos_speed;
    }

    @Override
    public void draw(Batch batch) {
        update();
        batch.draw(weapon_texture, pos.x, pos.y, 30,30);
    }

    @Override
    public void acceptDamage(Entity entity) {
        entity.getDamage(2);
    }

    @Override
    public void updateFrame() {

    }

    @Override
    public Rectangle getHitbox() {
        hitbox.x = pos.x;
        hitbox.y = pos.y;
        return hitbox;
    }

    public void setPos(float x, float y) {
        pos.x = x;
        pos.y = y;
    }

    @Override
    public Shell clone() {
        return new Fireball(map, 3, 4, 0, 0, 0);
    }

}
