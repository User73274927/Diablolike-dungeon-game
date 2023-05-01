package com.samsung.game.items.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.samsung.game.DGame;
import com.samsung.game.effects.Burst;
import com.samsung.game.engine.RigidBody;
import com.samsung.game.engine.Side;
import com.samsung.game.entities.Enemy;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.player.Player;
import com.samsung.game.map.Wall;

public class BouncedProjectile extends Projectile {

    public BouncedProjectile(Entity owner) {
        super(owner);
        texture = DGame.textures.getTexture("projectile-1.png");
        body.flag_wallIgnore = true;

        body.width = 10;
        body.height = 10;
        limit = 10;

        body.addWallTouchedListener(new RigidBody.WallTouchedListener() {
            @Override
            public void touched(Wall wall) {
                Side side = wall.defineSideFrom(body.getCenterX(), body.getCenterY());
                System.out.println(side);

                switch (side) {
                    case NORTH:
                    case SOUTH:
                        angle = -angle;
                        break;
                    case EAST:
                    case WEST:
                        angle = 3f - angle;
                        break;
                }
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setDamageBounds(10, 20);
        body.flag_wallIgnore = true;
    }

    @Override
    public void acceptDamage(Enemy enemy) {
        enemy.putDamage(getDamage());
        DGame.data.effects.add(new Burst(new Vector2(body.getCenterX(), body.getCenterY()), 30));
    }

    @Override
    public void acceptDamage(Player player) {
        player.putDamage(0);
    }

    @Override
    public Projectile clone() {
        return new BouncedProjectile(owner);
    }
}
