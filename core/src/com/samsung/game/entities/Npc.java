package com.samsung.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.samsung.game.engine.Quest;
import com.samsung.game.map.Map;

public class Npc extends Entity {
    // Система торговли и диалоги, будут добавлены позже

    // private final String random_reply_command = "#random";
    // private HashMap<String, String[]> template_replies;
    // private final HashMap<String, String[]> dialogs;

    protected Quest quest;
    protected String name;
    private String[] dialog = {"Hello, my friend!", "I need your help", "Pleaseeeee!!!"};

//    public enum NpcState {
//        CANNOT_TALK,
//        CAN_TALK,
//        SELLING,
//        QUEST_GETTING,
//        QUEST_DONE,
//        QUEST_IN_PROCESS;
//
//        private final String title;
//
//        NpcState() {
//            title = name().toLowerCase();
//        }
//
//        @Override
//        public String toString() {
//            return title;
//        }
//    }

    public Npc(Map map, float x, float y) {
        super(map, x, y);
        initDialogs();

        current_frame = new TextureRegion(new Texture("sprites/player-example1.png"));
    }

    private void initDialogs() {

    }

    public void talk() {

    }

    public void setDialog(String... dialog) {
        this.dialog = dialog;
    }

    public String[] getDialog() {
        return dialog;
    }

    public Quest getQuest() {
        return quest;
    }

    @Override
    public void onSpawn() {
        current_frame = new TextureRegion(new Texture("sprites/player-example1.png"));
        health = 1;
    }

    @Override
    public void update() {

    }

    //невозможно убить
    @Override
    public void onDie() {}
}
