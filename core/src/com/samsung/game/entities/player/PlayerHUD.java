package com.samsung.game.entities.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.engine.PlayerViewPort;
import com.samsung.game.engine.gdx.GroupWrapper;
import com.samsung.game.entities.Enemy;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.InventoryController;
import com.samsung.game.items.Item;
import com.samsung.game.items.PlayerEquipable;
import com.samsung.game.items.PlayerUsable;
import com.samsung.game.items.armor.Armour;
import com.samsung.game.items.armor.Helmet;
import com.samsung.game.items.potions.Potion;
import com.samsung.game.map.Tile;
import com.samsung.game.ui.Picture;
import com.samsung.game.ui.UIInventory;
import com.samsung.game.ui.panels.DescriptionPanel;
import com.samsung.game.ui.panels.DialogPanel;
import com.samsung.game.ui.panels.EnemyStatsPanel;
import com.samsung.game.ui.panels.PlayerStatsPanel;
import com.samsung.game.utils.DebugConsole;

public class PlayerHUD extends GroupWrapper {
    private PlayerController controller;
    private Player player;

    private InventoryController<Potion> potion_controller;
    private InventoryController<Item> item_controller;
    Picture helmet_view;
    Picture armor_view;

    EnemyStatsPanel enemy_panel;
    PlayerStatsPanel stats_panel;

    DescriptionPanel item_info;
    DialogPanel dialogPanel;

    UIInventory potion_bar;
    UIInventory main_inventory;

    DebugConsole console;

    public PlayerHUD(PlayerViewPort viewPort, PlayerController controller) {
        this.controller = controller;
        this.player = controller.getPlayer();
        controller.setPlayerHUD(this);

        this.item_controller = controller.itemInventoryController;
        item_controller.addAction((item, col, row) -> {
            item_info.setItem(item);
        }, "info");

        item_controller.addAction((item, col, row) -> {
            if (item instanceof PlayerUsable) {
                ((PlayerUsable) item).use(player);
                item_controller.getInventory().remove(col, row);
            }
            else if (item instanceof PlayerEquipable) {
                player.inventory.setItemOnHand((PlayerEquipable<? super Entity>) item);
            }
            else if (item instanceof Armour) {
                player.inventory.setArmour((Armour) item);
                armor_view.setPicture(item.getIconTexture());
            }
            else if (item instanceof Helmet) {
                player.inventory.setHelmet((Helmet) item);
                helmet_view.setPicture(item.getIconTexture());
            }
        }, "equip");

        potion_controller = controller.potionInventoryController;

        potion_controller.addAction((potion, col, row) -> {
            potion.use(player);
            potion_controller.getInventory().remove(col, row);
        }, "use");

        potion_controller.addAction((potion, col, row) -> {
            item_info.setItem(potion);
        }, "info");

        potion_controller.setOnTouchAction("use");
        item_controller.setOnTouchAction("info");
        item_controller.setOnDoubleTouchAction("equip");

        potion_bar = new UIInventory(potion_controller);
        potion_bar.indent = 5;
        potion_bar.setY(25);
        potion_bar.setCenterX(viewPort.viewport_width / 2f);

        main_inventory = new UIInventory(item_controller);
        main_inventory.indent = 5;
        main_inventory.setX(viewPort.viewport_width - main_inventory.getWidth() - 25);
        main_inventory.setY(50);
        main_inventory.setIsOpened(false);

        item_info = new DescriptionPanel();
        item_info.setBounds(25, 150, 125, 150);
        item_info.margin = 5f;
        item_info.setVisible(false);

//        health = new PxFont(player.getHealth(), 25);
//        health.setBounds(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50, 0, 0);
//        stamina = new PxFont(player.getStamina(), 25);
//        stamina.setBounds(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100, 0, 0);
        stats_panel = new PlayerStatsPanel(controller.getPlayer());
        stats_panel.setBounds(viewPort.viewport_width - 200, viewPort.viewport_height - 125, 175, 100);
        //stats_panel.setPosition(Gdx.graphics.getWidth() - 225, Gdx.graphics.getHeight() - 125, Align.topLeft);

        enemy_panel = new EnemyStatsPanel();
        enemy_panel.setWidth(150);
        enemy_panel.setHeight(40);
        enemy_panel.setCenterX(viewPort.viewport_width / 2f);
        enemy_panel.setY(viewPort.viewport_height - enemy_panel.getHeight() - 25);

        helmet_view = new Picture(player.inventory.helmet.getIconTexture());
        armor_view = new Picture(player.inventory.armor.getIconTexture());

        helmet_view.setSize(30, 30);
        helmet_view.setX(25);
        helmet_view.setY(viewPort.viewport_height - (Tile.SIZE + 25));

        armor_view.setSize(30, 30);
        armor_view.setX(25);
        armor_view.setY(viewPort.viewport_height - (Tile.SIZE * 2 + 35));

//        enemyHealth = new PxFont(0, 25);
//        enemyHealth.setX(Gdx.graphics.getWidth() / 2f);
//        enemyHealth.setY(Gdx.graphics.getHeight() - 45);

        dialogPanel = new DialogPanel();
        dialogPanel.setBounds(viewPort.viewport_width / 2f - 125, viewPort.viewport_height - 125,  250, 100);
        dialogPanel.setVisible(false);

//        console = new DebugConsole();
//        console.setX(400);
//        console.setY(viewPort.viewport_height);
//
//        addActor(console);
        addActor(enemy_panel);
        addActor(stats_panel);
        addActor(dialogPanel);
        addActor(main_inventory);
        addActor(potion_bar);
//        addActor(health);
//        addActor(stamina);
        addActor(helmet_view);
        addActor(armor_view);
        addActor(item_info);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        stats_panel.setStats(player.getHealth(), player.stamina, player.level);
        if (controller.current_entity == null || controller.current_entity instanceof Enemy)
            enemy_panel.setEnemy((Enemy) controller.current_entity);
        main_inventory.update();
        potion_bar.update();
        super.draw(batch, parentAlpha);
    }

}
