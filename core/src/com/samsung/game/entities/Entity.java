package com.samsung.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.samsung.game.data.ResourceManager;
import com.samsung.game.engine.Component;
import com.samsung.game.logic.data.DamageType;

import java.util.HashMap;

public abstract class Entity implements Component {
    private Vector2 pos;

    private int fire_resistance = 0;
    private int cold_resistance = 0;
    private int physical_resistance = 0;

    protected int health;
    protected int level;

    public Entity() {

        pos = new Vector2();
    }

    public boolean isAlive() {
        return health > 0;
    }

    public Vector2 getPos() {
        return pos;
    }

    public abstract void onSpawn();
    public abstract void update();
    public abstract void onDie();


}
