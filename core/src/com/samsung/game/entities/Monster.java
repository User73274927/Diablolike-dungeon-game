package com.samsung.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.samsung.game.DGame;
import com.samsung.game.ai.Agent2;
import com.samsung.game.effects.IncreasingRectEffect;
import com.samsung.game.engine.Damage;
import com.samsung.game.entities.player.Player;
import com.samsung.game.items.weapon.FireWeapon;

import java.util.Random;

import static com.samsung.game.data.Textures.SPRITES;
import static com.samsung.game.data.Textures.TILES;

public class Monster extends Enemy implements Damage {
    private float time;
    private int[] damage;


    public Monster(float x, float y) {
        super(x, y);
        setLevel(1);
    }

    @Override
    public void onCreate() {
        body.MAX_VEL = new Random().nextFloat(0.2f, 4f);
        if (body.MAX_VEL < 2) {
            setEnemyName("monster baby");
            damage = new int[] {5, 7};
            body.box.width = 25;
            body.box.height = 25;
            init_health = 75;

            animationDict.put("left", DGame.animations.getAnimation("mini-monster-left"));
            animationDict.put("right", DGame.animations.getAnimation("mini-monster-right"));
        } else {
            damage = new int[] {10, 12};
            init_health = 100;

            animationDict.put("left", DGame.animations.getAnimation("monster-left"));
            animationDict.put("right", DGame.animations.getAnimation("monster-right"));
        }
    }

    @Override
    public void update() {
        super.update();
        time += Gdx.graphics.getDeltaTime();

        if (agent != null) {
            agent.discover(Gdx.graphics.getDeltaTime());
            if (new Vector2(body.getPos()).sub(player.body.getPos()).len() <= player.getWidth() / 2 + 5 &&
                    agent.getState() == Agent2.State.TARGET_NOTICED) {
                acceptDamage(player);
            }
        }
        //f.shoot(angle += 0.01);
    }

    @Override
    public void acceptDamage(Enemy enemy) {}

    @Override
    public void acceptDamage(Player player) {
        if (time >=  2 / body.MAX_VEL) {
            if (Math.random() <= 0.5) {
                Random r = new Random();
                player.putDamage(r.nextInt(damage[0], damage[1] + 2*level+1));
            }
            time = 0;
        }
    }

    @Override
    public void setAgent(Player target, int visible_distance) {
        super.setAgent(target, 125);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DGame.data.effects.add(new IncreasingRectEffect(
                DGame.textures.getTexture(SPRITES + "player-example1.png"),
                new Vector2(getCenterX(), getCenterY()), 25
        ));
    }
}
