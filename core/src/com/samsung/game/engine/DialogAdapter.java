package com.samsung.game.engine;

import com.samsung.game.entities.Npc;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.ui.DialogPanel;

public class DialogAdapter {
    private DialogPanel panel;
    private final PlayerController controller;
    private Npc npc;

    public DialogAdapter(PlayerController controller) {
        this.controller = controller;
        panel = controller.getIUPanel().dialogPanel;
    }

    public void setNpc(Npc npc) {
        this.npc = npc;
    }

    public void startDialog(String[] dialog_text) {
        panel.setVisible(true);
        panel.setDialogText(dialog_text);
    }
}
