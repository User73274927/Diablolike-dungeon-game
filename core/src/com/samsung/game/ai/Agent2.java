package com.samsung.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.samsung.game.engine.RigidBody;
import com.samsung.game.entities.Enemy;
import com.samsung.game.entities.player.Player;

public abstract class Agent2 {
    private Enemy enemy;
    private Player target;

    private RigidBody enemy_body;
    private RigidBody target_body;

    private boolean isActive;

    public Agent2(Enemy enemy, Player target) {
        this.enemy = enemy;
        this.target = target;

        enemy_body = enemy.getBody();
        target_body = target.getBody();
    }

    public void discover(float deltaTime) {
        enemy_body.getPos().add(calculateVelocity());
    }

    public Vector2 getSpaceVectorToTarget() {
        return new Vector2(target_body.getPos()).sub(enemy_body.getPos());
    }

    public Vector2 calculateVelocity() {
        Vector2 space_nor = getSpaceVectorToTarget().nor();
        return space_nor.scl((float) (enemy_body.MAX_VEL*Math.random()), (float) (enemy_body.MAX_VEL*Math.random()));
    }

    public abstract boolean activate();

    public abstract boolean deactivate();

}
