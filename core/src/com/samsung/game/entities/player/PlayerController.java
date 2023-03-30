package com.samsung.game.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.samsung.game.map.Map;
import com.samsung.game.ui.UIInventory;

public class PlayerController extends InputAdapter {
    private Player player;
    private UIPlayerPanel player_ui_panel;
    private Camera camera;
    private Vector3 touch_pos;

    public PlayerController(Map map) {
        touch_pos = new Vector3();
        player = new Player(map);
        player_ui_panel = new UIPlayerPanel(player);
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
            System.out.println(touch_pos.x + " " + touch_pos.y);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.E) {
            UIInventory bar = player_ui_panel.main_inventory;
            bar.setIsOpened(!bar.isOpened);
        }
        return super.keyUp(keycode);
    }

    public UIPlayerPanel getIUPanel() {
        return player_ui_panel;
    }

    public Player getPlayer() {
        return player;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

}
