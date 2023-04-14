package com.samsung.game.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.samsung.game.engine.LevelData;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.Npc;
import com.samsung.game.items.InventoryController;
import com.samsung.game.items.Item;
import com.samsung.game.ui.panels.DescriptionPanel;
import com.samsung.game.ui.panels.DialogPanel;
import com.samsung.game.ui.UIInventory;
import com.samsung.game.utils.TestAlgorithms;

import java.util.Arrays;

public class PlayerController extends InputAdapter {
    public Entity current_entity;
    private boolean isTalking = false;
    LevelData data;
    private Player player;
    private PlayerHUD playerHUD;
    private Camera camera;
    private Vector3 touch_pos;

    public final InventoryController<Item> itemInventoryController;

    public PlayerController(LevelData data) {
        this.data = data;
        touch_pos = new Vector3();
        player = new Player(data);
        itemInventoryController = new InventoryController<>(player.inventory.main_inventory);
        playerHUD = new PlayerHUD(this);
    }

    public void keyHandler() {
        isTalking = playerHUD.dialogPanel.isVisible();
        if (isTalking) {
            player.setDirection(Entity.Direction.STOP);
            return;
        }

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
            //test
            for (Entity entity : data.allEntity) {
                if (entity instanceof PlayerObserver) {
                    if (entity.intersects(touch_pos.x, touch_pos.y)) {
                        current_entity = entity;
                        ((PlayerObserver)entity).doAction(this);
                        return false;
                    }
                }
            }
            current_entity = null;
            Gdx.app.log("tile define:",
                    Arrays.toString(TestAlgorithms.findTileByCoords(data.map.getTiledMap(), (int)touch_pos.x, (int)touch_pos.y))
            );
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
        npc.talk(getPlayer());
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
