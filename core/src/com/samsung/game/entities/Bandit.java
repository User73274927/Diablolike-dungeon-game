package com.samsung.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.DGame;
import com.samsung.game.entities.player.Player;
import com.samsung.game.items.weapon.FireWeapon;

public class Bandit extends Enemy {
    private FireWeapon f;
    private float angle;
    private float time;

    public Bandit(Player player, float x, float y) {
        super(player, x, y);
    }

    @Override
    public void onCreate() {
        animationDict.put("idle", DGame.animations.getAnimation("monster1-left"));
        body.MAX_VEL = 1;

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
        agent.discover(Gdx.graphics.getDeltaTime());
//        if (new Vector2(body.getPos()).sub(player.body.getPos()).len() <= player.getWidth() / 2 + 5) {
//            player.addHealth(-1);
//        }
        //f.shoot(angle += 0.01);
    }
}
