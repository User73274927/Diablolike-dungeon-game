package com.samsung.game.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.Item;
import com.samsung.game.items.InventoryController;
import com.samsung.game.items.armor.Armour;
import com.samsung.game.items.armor.Helmet;
import com.samsung.game.items.potions.Potion;
import com.samsung.game.map.Tile;
import com.samsung.game.ui.DescriptionPanel;
import com.samsung.game.ui.DialogPanel;
import com.samsung.game.ui.PxFont;
import com.samsung.game.ui.UIInventory;

public class PlayerHUD extends Group {
    private PlayerController controller;
    private Player player;

    private InventoryController<Potion> potion_controller;
    private InventoryController<Item> item_controller;
    Helmet.UIView helmet_view;
    Armour.UIView armor_view;

    PxFont health;
    PxFont stamina;
    PxFont enemyHealth;

    DescriptionPanel item_info;
    public final DialogPanel dialogPanel;

    UIInventory potion_bar;
    UIInventory main_inventory;

    public PlayerHUD(PlayerController controller) {
        this.controller = controller;
        this.player = controller.getPlayer();

        this.item_controller = controller.itemInventoryController;
        item_controller.setOnTouchAction((item, col, row) -> {
            item_info.setTitle(item.getItemName());
            item_info.setText(item.info());
        });

        item_controller.setOnDoubleTouchAction((item, col, row) -> {
            if (item instanceof Equipable)
                player.inventory.setItemOnHand((Equipable) item);
            if (item instanceof Armour)
                player.inventory.setArmour((Armour) item);
            if (item instanceof Helmet)
                player.inventory.setHelmet((Helmet) item);
        });

        potion_controller = new InventoryController<>(player.inventory.potion_bar);
        potion_controller.setOnTouchAction((potion, col, row) -> {
            potion.use(player);
            potion_controller.getInventory().remove(col, row);
        });

        potion_bar = new UIInventory(potion_controller);
        potion_bar.indent = 5;
        potion_bar.setBounds(25, 25, 0, 0);

        main_inventory = new UIInventory(item_controller);
        main_inventory.indent = 5;
        main_inventory.setX(Gdx.graphics.getWidth() - main_inventory.getWidth() - 25);
        main_inventory.setY(25);
        main_inventory.setIsOpened(false);

        item_info = new DescriptionPanel();
        item_info.setBounds(50, potion_bar.getHeight() + potion_bar.getY() + 50f, 100, 150);
        item_info.margin = 5f;
        item_info.setVisible(false);

        health = new PxFont(player.getHealth(), 25);
        health.setBounds(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50, 0, 0);
        stamina = new PxFont(player.getStamina(), 25);
        stamina.setBounds(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100, 0, 0);

        helmet_view = player.inventory.helmet.findUIView(Item.UILocation.ON_SCREEN);
        armor_view = player.inventory.armor.findUIView(Item.UILocation.ON_SCREEN);
        helmet_view.visible = true;
        armor_view.visible = true;

        helmet_view.setIconX(25);
        helmet_view.setIconY(Gdx.graphics.getHeight() - (Tile.SIZE + 25));

        armor_view.setIconX(25);
        armor_view.setIconY(Gdx.graphics.getHeight() - (Tile.SIZE * 2 + 35));

//        enemyHealth = new PxFont(0, 25);
//        enemyHealth.setX(Gdx.graphics.getWidth() / 2f);
//        enemyHealth.setY(Gdx.graphics.getHeight() - 45);

        dialogPanel = new DialogPanel();
        dialogPanel.setBounds(Gdx.graphics.getWidth() / 2f - 125, Gdx.graphics.getHeight() - 175,  250, 150);
        dialogPanel.setVisible(false);

        addActor(dialogPanel);
        addActor(main_inventory);
        addActor(potion_bar);
        addActor(health);
        addActor(stamina);
        addActor(helmet_view);
        addActor(armor_view);
        addActor(item_info);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (PlayerController.current_entity != null) {
            enemyHealth.number = PlayerController.current_entity.getHealth();
            enemyHealth.draw(batch, parentAlpha);
        }
        health.number = player.getHealth();
        stamina.number = player.getStamina();
        main_inventory.update();
        potion_bar.update();

        super.draw(batch, parentAlpha);
    }

}
