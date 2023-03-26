package com.samsung.game.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.samsung.game.items.Item;
import com.samsung.game.items.armor.Helmet;
import com.samsung.game.map.Tile;
import com.samsung.game.ui.PxNumber;
import com.samsung.game.ui.UIItemBar;
import com.samsung.game.ui.UIButton;

public class UIPlayerPanel extends Stage {
    private Player player;
    private UIButton button;
    private PxNumber health;
    private UIItemBar potion_bar;

    public UIPlayerPanel(Player player) {
        this.player = player;
        potion_bar = new UIItemBar(5, player.inventory.potion_bar);
        potion_bar.setBounds(50, 25, 100, 200);

        health = new PxNumber(player.getHealth(), Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50, 25);

        Helmet.UIView helmet_view = player.inventory.helmet.findUIView(Item.UILocation.ON_SCREEN);
        Item.UIView armor_view = player.inventory.armor.findUIView(Item.UILocation.ON_SCREEN);
        helmet_view.visible = true;
        armor_view.visible = true;

        helmet_view.setIconX(25);
        helmet_view.setIconY(Gdx.graphics.getHeight() - (Tile.SIZE + 25));

        armor_view.setIconX(25);
        armor_view.setIconY(Gdx.graphics.getHeight() - (Tile.SIZE * 2 + 35));

        addActor(potion_bar);
        addActor(health);
        addActor(helmet_view);
        addActor(armor_view);

    }

    @Override
    public void draw() {
        health.number = player.getHealth();
        potion_bar.update();
        super.draw();
    }

}
