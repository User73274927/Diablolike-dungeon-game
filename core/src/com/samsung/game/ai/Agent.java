package com.samsung.game.ai;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.samsung.game.engine.RigidBody;
import com.samsung.game.entities.Entity;

public class Agent implements Steerable<Vector2> {
    private Entity enemy;
    private RigidBody enemy_body;
    private SteeringBehavior<Vector2> behavior;
    private SteeringAcceleration<Vector2> output;

    public float maxLinearSpeed, maxLinearAcceleration;
    public float maxAngularSpeed, maxAngularAcceleration;

    public Agent(Entity enemy) {
        output = new SteeringAcceleration<>(new Vector2());

        this.enemy = enemy;
        enemy_body = enemy.getBody();
    }

    public void update(float deltaTime) {
        if (behavior != null) {
            behavior.calculateSteering(output);
            apply(deltaTime);
        }
    }

    private void apply(float deltaTime) {
        if (!output.linear.isZero()) {
            Vector2 vel = enemy_body.getVel();
            float speed_q = vel.len2();

            if (speed_q > maxLinearSpeed * maxAngularSpeed) {
                enemy_body.getVel().set(vel.scl(maxLinearSpeed / (float) Math.sqrt(speed_q)));
            }
        }
    }

    @Override
    public Vector2 getLinearVelocity() {
        return enemy_body.getVel();
    }

    @Override
    public float getAngularVelocity() {
        return 0;
    }

    @Override
    public float getBoundingRadius() {
        return 0;
    }

    @Override
    public boolean isTagged() {
        return false;
    }

    @Override
    public void setTagged(boolean tagged) {

    }

    @Override
    public float getZeroLinearSpeedThreshold() {
        return 0;
    }

    @Override
    public void setZeroLinearSpeedThreshold(float value) {

    }

    @Override
    public float getMaxLinearSpeed() {
        return maxLinearSpeed;
    }

    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {

    }

    @Override
    public float getMaxLinearAcceleration() {
        return maxLinearAcceleration;
    }

    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {

    }

    @Override
    public float getMaxAngularSpeed() {
        return maxAngularSpeed;
    }

    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {

    }

    @Override
    public float getMaxAngularAcceleration() {
        return maxAngularAcceleration;
    }

    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {

    }

    @Override
    public Vector2 getPosition() {
        return enemy_body.getPos();
    }

    @Override
    public float getOrientation() {
        return 0;
    }

    @Override
    public void setOrientation(float orientation) {

    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return (float) Math.atan2(-vector.x, vector.y);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return outVector.set(-(float) Math.sin(angle), (float) Math.cos(angle));
    }

    @Override
    public Location<Vector2> newLocation() {
        return null;
    }
    public void setBehavior(SteeringBehavior<Vector2> behavior) {
        this.behavior = behavior;
    }
}
