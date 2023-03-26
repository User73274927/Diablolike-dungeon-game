package com.samsung.game.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.samsung.game.map.Map;

public class PlayerController extends InputAdapter {
    private Player player;
    private UIPlayerPanel player_ui_panel;
    private float cam_x, cam_y;

    public PlayerController(Map map) {
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

    //Не работает
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            System.out.println(cam_x + screenX + " " + cam_y + screenY);
            player.updateClick(cam_x + screenX, cam_y + (Gdx.input.getY() - screenY));
        }
        return false;
    }


    public Player getPlayer() {
        return player;
    }

    public UIPlayerPanel getIUPanel() {
        return player_ui_panel;
    }

    public void setCamLocation(float cam_x, float cam_y) {
        this.cam_x = cam_x;
        this.cam_y = cam_y;
    }

}
