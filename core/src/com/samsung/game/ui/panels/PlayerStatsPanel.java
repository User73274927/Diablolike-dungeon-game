package com.samsung.game.ui.panels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;;
import com.samsung.game.engine.gdx.GroupWrapper;
import com.samsung.game.entities.player.Player;
import com.samsung.game.ui.PxFont;
import com.samsung.game.ui.UIElements;

public class PlayerStatsPanel extends GroupWrapper {
    private final String PATH = "player-stats-asset/";
    private Player player;
    private Texture line;
    private Texture lvl;
    private Texture empty_unit;
    private Texture health_unit;
    private Texture mana_unit;

    private PxFont health_nm;
    private PxFont mana_nm;
    private PxFont lvl_nm;
    private int scale_unit_size = 20;
    private int scale_count = 5;
    private int indent = 15;

    public PlayerStatsPanel(Player player) {
        this.player = player;
        line = new Texture(PATH + "line.png");
        lvl = new Texture(PATH + "lvl.png");
        health_unit = new Texture(PATH + "health-scale-unit.png");
        mana_unit = new Texture(PATH + "mana-scale-unit.png");
        empty_unit = new Texture(PATH + "empty-scale-unit.png");

        health_nm = new PxFont(0, 20);
        mana_nm = new PxFont(0, 20);
        lvl_nm = new PxFont(0, 20);

        lvl_nm.setX(indent + 75);
        lvl_nm.setY(0);
        addActor(health_nm);
        addActor(mana_nm);
        addActor(lvl_nm);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(line, getX(), getY(), getWidth() / 33, getHeight());
        batch.draw(lvl, getX()+indent, getY(), (float) (20 * (lvl.getWidth() / lvl.getHeight())), 20);
        super.draw(batch, parentAlpha);
        UIElements.drawScale(batch, health_nm.number, player.MAX_HEALTH, health_unit, empty_unit, scale_count, scale_unit_size,
                getX() + indent*2 + health_nm.getWidth(), getY()+health_nm.getY());
        UIElements.drawScale(batch, mana_nm.number, player.MAX_STAMINA, mana_unit, empty_unit, scale_count, scale_unit_size,
                getX() + indent*2 + mana_nm.getWidth(), + getY()+mana_nm.getY());
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        health_nm.setX(indent);
        health_nm.setY(height - indent - health_nm.getHeight());
        mana_nm.setX(indent);
        mana_nm.setY(height - indent*2 - health_nm.getHeight() - mana_nm.getHeight());
        super.setBounds(x, y, width, height);
    }

    public void setStats(int health, int mana, int lvl) {
        health_nm.number = health;
        mana_nm.number = mana;
        lvl_nm.number = lvl;
    }
}
