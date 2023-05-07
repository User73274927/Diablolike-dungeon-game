package com.samsung.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.samsung.game.DGame;
import com.samsung.game.engine.Damage;
import com.samsung.game.entities.player.Player;
import com.samsung.game.items.weapon.FireWeapon;

public class Monster extends Enemy implements Damage {
    private FireWeapon f;
    private float angle;
    private float time;

    public Monster(Player player, float x, float y) {
        super(player, x, y);
    }

    @Override
    public void onCreate() {
        animationDict.put("idle", DGame.animations.getAnimation("monster1-left"));
        body.MAX_VEL = 1;
        setLevel(1);

        f = new FireWeapon();
        f.setOwner(this);
        f.hit_chance = 0.2;
        health = 100;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        f.draw(batch, 0f);
    }

    @Override
    public void update() {
        super.update();
        time += Gdx.graphics.getDeltaTime();

        agent.discover(Gdx.graphics.getDeltaTime());
        if (new Vector2(body.getPos()).sub(player.body.getPos()).len() <= player.getWidth() / 2 + 8) {
            acceptDamage(player);
        }
        //f.shoot(angle += 0.01);
    }

    @Override
    public void acceptDamage(Enemy enemy) {}

    @Override
    public void acceptDamage(Player player) {
        if (time >= 2) {
            player.addHealth(-10);
            time = 0;
        }
    }
}
