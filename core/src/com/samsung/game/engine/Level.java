package com.samsung.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.samsung.game.DGame;
import com.samsung.game.engine.gdx.StageWrapper;
import com.samsung.game.entities.Monster;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.Npc;
import com.samsung.game.entities.player.Player;
import com.samsung.game.entities.player.PlayerControlField;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.items.Item;
import com.samsung.game.items.potions.HealthPotion;
import com.samsung.game.map.AsciiMap;
import com.samsung.game.screens.GameScreen;

import java.util.Iterator;

public class Level extends StageWrapper {
    private GameScreen game;

    private final float WORLD_WIDTH = 640; // 16
    private final float WORLD_HEIGHT = 320; // 9

    ShapeRenderer batch;
    final Viewport viewport;
    final Camera game_camera = new OrthographicCamera();
    final PlayerController controller;
    public final PlayerControlField controls;
    final Player player;
    final LevelData data;
    final AsciiMap map;

    public Level(GameScreen game, AsciiMap map) {
        this.game = game;
        this.map = map;
        map.load();
        data = new LevelData(map);

        this.controls = game.player_controls;
        this.controller = game.controller;
        this.player = controller.getPlayer();
        controller.setCamera(game_camera);
        player.setLocation(70, 70);

        viewport = new FitViewport(640, 640*DGame.getAspectRatio(), game_camera);
        setViewport(viewport);

        addActor(data.entityHandler);
        addActor(data.itemHandler);

        game.inputMultiplexer.addProcessor(this);
    }

    public Level create() {
        Monster monster1 = new Monster(player, 400, 200);
        Monster monster2 = new Monster(player, 150, 30);
        Monster monster3 = new Monster(player, 200, 100);
        Monster bandit4 = new Monster(player, 300, 75);
        Npc npc = new Npc(50, 350);

        data.addEntity(monster1);
        data.addEntity(monster2);
        data.addEntity(monster3);
        data.addEntity(bandit4);
        data.addEntity(npc);
        data.addEntity(controller.getPlayer());

        data.visible_items.add(new HealthPotion());
        data.visible_items.add(new HealthPotion());
        data.visible_items.add(new HealthPotion());
        data.visible_items.add(new HealthPotion());
        data.visible_items.add(new HealthPotion());
        data.visible_items.add(new HealthPotion());
        data.visible_items.add(new HealthPotion());



        DGame.data = data;
        return this;
    }

    boolean pause;

    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(0.04f, 0.31f, 0.40f, 1);

        game_camera.position.set(player.getCenterX(), player.getCenterY(), 0);
        data.field.update();

        Iterator<Entity> iterator = DGame.data.allEntity.iterator();

        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            entity.update();

            if (entity.isRemove) {
                iterator.remove();
            }
        }

        getBatch().begin();
        getBatch().setProjectionMatrix(game_camera.combined);
        //batch.setProjectionMatrix(game_camera.combined);

        map.draw(getBatch());
        for (Item item : data.visible_items) {
            item.draw(getBatch(), 0f);
        }
        for (Drawable d : data.effects) {
            d.draw(getBatch());
        }

        getBatch().end();
    }

    @Override
    public void act() {
        super.act();
        render();
    }

    @Override
    public void dispose() {
        data.removeAllEntity();
        super.dispose();
    }
}
