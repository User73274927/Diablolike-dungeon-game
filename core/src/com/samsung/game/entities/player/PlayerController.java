package com.samsung.game.entities.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.samsung.game.DGame;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.Npc;
import com.samsung.game.items.InventoryController;
import com.samsung.game.items.Item;
import com.samsung.game.items.potions.Potion;
import com.samsung.game.screens.GameScreen;
import com.samsung.game.ui.JoyStick;
import com.samsung.game.ui.UIInventory;
import com.samsung.game.ui.panels.DescriptionPanel;
import com.samsung.game.ui.panels.DialogHUD;
import com.samsung.game.utils.DebugConsole;
import com.samsung.game.utils.GameUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlayerController extends InputAdapter {
    // Player tools
    private GameScreen process;
    private Map<ControlType, JoyStick> controls_map;

    public final InventoryController<Item> itemInventoryController;
    public final InventoryController<Potion> potionInventoryController;

    public enum ControlType {
        move, attack
    }

    // Player state
    private final Player player;
    public Entity current_entity;

    private boolean isTalking = false;

    private PlayerHUD playerHUD;
    private Camera camera;
    private Vector3 touch_pos;
    int i = 0;

    public PlayerController(GameScreen process) {
        this.process = process;
        controls_map = new HashMap<>();
        touch_pos = new Vector3();
        player = new Player();

        itemInventoryController = new InventoryController<>(player.inventory.main_inventory);
        potionInventoryController = new InventoryController<>(player.inventory.potion_bar);

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            touch_pos.set(screenX, screenY, 0);
            camera.unproject(touch_pos);
            player.updateClick(touch_pos.x, touch_pos.y);

            for (Entity entity : DGame.data.allEntity) {
                if (entity instanceof PlayerObserver) {
                    if (entity.intersects(touch_pos.x, touch_pos.y)) {
                        current_entity = entity;
                        ((PlayerObserver) entity).execute(this);
                        isTalking = playerHUD.dialogHUD.isVisible();
                        return false;
                    }
                }
            }
            current_entity = null;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            process.paused = !process.paused;
            return false;
        }
        if (keycode == Input.Keys.E) {
            UIInventory bar = playerHUD.main_inventory;
            DescriptionPanel list = playerHUD.item_info;
            bar.setIsOpened(!bar.isOpened);
            list.setVisible(!list.isVisible());
            potionInventoryController.setOnTouchAction(list.isVisible() ? "info" : "use");
            playerHUD.item_info.clean();
        }
        if (keycode == Input.Keys.R) {
            process.changeScene("level" + ((++i % 2) - 1));
        }
        return super.keyUp(keycode);
    }

    public void startTalk(Npc npc) {
        DialogHUD dpn = playerHUD.dialogHUD;
        npc.talk(getPlayer());
        dpn.setDialogText(npc.getDialog());
        dpn.setVisible(true);
    }

    public void addControl(ControlType type, JoyStick stick) {
        player.getBody().connectJoystick(stick);
        controls_map.put(type, stick);
    }

    public JoyStick getControl(ControlType type) {
        return controls_map.get(type);
    }

    public PlayerHUD getPlayerHUD() {
        return playerHUD;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayerHUD(PlayerHUD hud) {
        this.playerHUD = hud;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

}