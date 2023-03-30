package com.samsung.game.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.samsung.game.items.Item;
import com.samsung.game.items.armor.Helmet;
import com.samsung.game.map.Tile;
import com.samsung.game.ui.PxNumber;
import com.samsung.game.ui.UIInventory;
import com.samsung.game.ui.UIItemBar;
import com.samsung.game.ui.UIButton;

public class UIPlayerPanel extends Group {
    private Player player;
    PxNumber health;
    UIInventory potion_bar;
    UIInventory main_inventory;

    public UIPlayerPanel(Player player) {
        this.player = player;
        potion_bar = new UIInventory(player.inventory.potion_bar);
        main_inventory = new UIInventory(player.inventory.inventory);
        potion_bar.indent = 5;
        main_inventory.indent = 5;
        potion_bar.setBounds(25, 25, 0, 0);
        main_inventory.setX(400 - main_inventory.getWidth());
        main_inventory.setY(25);
        main_inventory.setIsOpened(false);

        health = new PxNumber(player.getHealth(), Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50, 25);

        Helmet.UIView helmet_view = player.inventory.helmet.findUIView(Item.UILocation.ON_SCREEN);
        Item.UIView armor_view = player.inventory.armor.findUIView(Item.UILocation.ON_SCREEN);
        helmet_view.visible = true;
        armor_view.visible = true;

        helmet_view.setIconX(25);
        helmet_view.setIconY(Gdx.graphics.getHeight() - (Tile.SIZE + 25));

        armor_view.setIconX(25);
        armor_view.setIconY(Gdx.graphics.getHeight() - (Tile.SIZE * 2 + 35));

        addActor(main_inventory);
        addActor(potion_bar);
        addActor(health);
        addActor(helmet_view);
        addActor(armor_view);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        health.number = player.getHealth();
        main_inventory.update();
        potion_bar.update();
        super.draw(batch, parentAlpha);
    }


}
