package com.samsung.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.samsung.game.DGame;
import com.samsung.game.DiabloGame;
import com.samsung.game.data.Json;
import com.samsung.game.ui.UIButton;
import org.w3c.dom.Text;

import static com.samsung.game.data.Textures.UI;

public class MenuScreen extends ScreenAdapter {
    private DiabloGame context;
    private Stage view;
    private SpriteBatch batch;
    private Texture background;
    private UIButton play;
    private UIButton exit;

    public MenuScreen(DiabloGame context) {
        this.context = context;
        batch = new SpriteBatch();
        view = new Stage(new FitViewport(DGame.getScreenWidth(), DGame.getScreenHeight()), batch) {
            @Override
            public void draw() {
                batch.begin();
                batch.draw(background,0, 0, getWidth(), getHeight());
                batch.end();
                super.draw();
            }
        };

        background = new Texture("background.png");
        play = new UIButton(new Texture(UI+"button-play-pressed.png"),
                new Texture(UI+"button-play-realized.png")
        );
        exit = new UIButton(new Texture(UI+"button-exit-pressed.png"),
                new Texture(UI+"button-exit-realized.png")
        );
        play.setSize(250, 100);
        play.press_once = true;
        exit.setSize(250, 100);

        play.setLocation(DGame.getScreenWidth()/2-play.getCenterX(), 200f);
        exit.setLocation(DGame.getScreenWidth()/2-exit.getCenterX(), 75f);
        view.addActor(play);
        view.addActor(exit);

        exit.setClickAction(new UIButton.ClickAction() {
            @Override
            public void action() {
                System.exit(0);
            }
        });

        play.setClickAction(new UIButton.ClickAction() {
            @Override
            public void action() {
                System.out.println("before");
                GameScreen game = new GameScreen(context);
                System.out.println("after");
                context.setGameScreen(game);
                context.setScreen(game);
            }
        });
    }

    @Override
    public void show() {
        super.show();
        play.resetCounter();
        context.multiplexer.addProcessor(view);
    }

    @Override
    public void hide() {
        super.hide();
        context.multiplexer.removeProcessor(view);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        view.act();
        view.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        view.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
