package com.samsung.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.samsung.game.DGame;
import com.samsung.game.ai.Agent2;
import com.samsung.game.engine.ItemGenerator;
import com.samsung.game.entities.player.Player;
import com.samsung.game.entities.player.PlayerController;
import com.samsung.game.entities.player.PlayerObserver;
import com.samsung.game.items.Item;
import com.samsung.game.items.potions.Potion;

public abstract class Enemy extends Entity implements PlayerObserver {
    protected Agent2 agent;
    protected Player player;
    private String name;
    private Item drop_item;
    private ItemGenerator item_generator;

    public Enemy(Player player, float x, float y) {
        super(x, y);
        current_frame = new TextureRegion(new Texture("sprites/player-example1.png"));
        item_generator = new ItemGenerator();
        Potion potion = item_generator.generatePotion();
        drop_item = potion;
        this.player = player;

        agent = new Agent2(this, player) {
            @Override
            public boolean activate() {
                return false;
            }

            @Override
            public boolean deactivate() {
                return false;
            }
        };
//        agent.maxLinearSpeed = body.MAX_VEL;
//        agent.maxAngularSpeed = 30;
//        agent.maxAngularAcceleration = 5;

//        Arrive<Vector2> arrive = new Arrive<>(agent, new Agent(player))
//                .setTimeToTarget(0.01f)
//                .setArrivalTolerance(2f)
//                .setDecelerationRadius(10);
//        agent.setBehavior(arrive);

        String name = getClass().getName();
        setEnemyName(name.substring(name.lastIndexOf('.')+1));
        onCreate();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(current_frame, getX(), getY(), getWidth(), getHeight());
    }

    public String getEnemyName() {
        return name;
    }

    public void setEnemyName(String name) {
        this.name = name;
    }

    @Override
    public void onDestroy() {
        DGame.data.visible_items.add(drop_item);
        drop_item.drop(getX(), getY());
        DGame.data.removeEntity(this);
    }

    @Override
    public void execute(PlayerController controller) {
        controller.current_entity = this;
        System.out.println(getEnemyName() + " attacks player");
    }


}
