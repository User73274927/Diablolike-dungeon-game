package com.samsung.game.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.samsung.game.engine.Quest;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.Npc;
import com.samsung.game.items.InventoryController;
import com.samsung.game.items.Item;
import com.samsung.game.map.Map;
import com.samsung.game.ui.DescriptionPanel;
import com.samsung.game.ui.DialogPanel;
import com.samsung.game.ui.UIInventory;

import java.util.Stack;

public class PlayerController extends InputAdapter {
    public static Entity current_entity;
    private Player player;
    private Stack<Quest> quest_list;
    private PlayerHUD playerHUD;
    private Camera camera;
    private Vector3 touch_pos;

    public final InventoryController<Item> itemInventoryController;

    public PlayerController(Map map) {
        touch_pos = new Vector3();
        player = new Player(map);
        itemInventoryController = new InventoryController<>(player.inventory.main_inventory);
        playerHUD = new PlayerHUD(this);
    }

    public void keyHandler() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.setDirection(Player.Direction.UP);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.setDirection(Player.Direction.LEFT);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.setDirection(Player.Direction.DOWN);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.setDirection(Player.Direction.RIGHT);
        } else {
            player.setDirection(Player.Direction.STOP);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            touch_pos.set(screenX, screenY, 0);
            camera.unproject(touch_pos);
            player.updateClick(touch_pos.x, touch_pos.y);
            Gdx.app.log("mouse coords", touch_pos.x + " " + touch_pos.y);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.E) {
            UIInventory bar = playerHUD.main_inventory;
            DescriptionPanel list = playerHUD.item_info;
            bar.setIsOpened(!bar.isOpened);
            list.setVisible(!list.isVisible());
        }
        return super.keyUp(keycode);
    }

    public void startTalk(Npc npc) {
        DialogPanel dpn = playerHUD.dialogPanel;
        npc.talk();
        dpn.setDialogText(npc.getDialog());
        dpn.setVisible(true);
    }

    public PlayerHUD getIUPanel() {
        return playerHUD;
    }

    public Player getPlayer() {
        return player;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

}
