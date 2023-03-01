package com.samsung.game.items.weapon;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.Knight;
import com.samsung.game.items.Item;
import com.samsung.game.map.Map;

import java.util.HashSet;

public class FireWeapon extends Item {
    private Map map;
    private HashSet<Shell> shells;
    public float x, y;

    public FireWeapon(Map map) {
        shells = new HashSet<>();
        this.map = map;
    }

    @Override
    public void draw(Batch batch) {
        Loop:
        for (Shell sh : shells) {
            sh.draw(batch);

            for (Entity entity : Entity.all()) {
                if (entity.getClass() == Knight.class) {
                    continue;
                }
                if (sh.overlaps(entity)) {
                    sh.acceptDamage(entity);
                    shells.remove(sh);
                    break Loop;
                }
            }
        }
    }

    public void shoot(Shell shell) {
        shells.add(shell);
    }
}
