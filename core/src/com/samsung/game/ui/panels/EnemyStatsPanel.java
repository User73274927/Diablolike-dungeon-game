package com.samsung.game.ui.panels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.engine.gdx.GroupWrapper;
import com.samsung.game.entities.Enemy;
import com.samsung.game.ui.UIScale;

public class EnemyStatsPanel extends GroupWrapper {
    private final String PATH = "player-stats-asset/";
    Texture health_unit = new Texture(PATH + "health-scale-unit.png");
    private UIScale scale;
    private int indent = 25;
    private Enemy enemy;

    public EnemyStatsPanel() {
        scale = new UIScale(health_unit);
        setHeight(20);
        addActor(scale);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (enemy != null) {
            scale.num = enemy.getHealth();
            scale.max_num = enemy.MAX_HEALTH;
            super.draw(batch, parentAlpha);
        }
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        scale.setCenterX(getWidth() / 2);
    }

}
