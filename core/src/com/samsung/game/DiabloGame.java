package com.samsung.game;

import com.badlogic.gdx.Game;
import com.samsung.game.data.Animations;
import com.samsung.game.data.Fonts;
import com.samsung.game.data.Textures;
import com.samsung.game.screens.GameScreen;
import com.samsung.game.screens.MenuScreen;
import com.samsung.game.screens.SaveLoadScreen;

public class DiabloGame extends Game {
    private MenuScreen menu_screen;
    private SaveLoadScreen save_load_screen;
    private GameScreen main_game;

    @Override
    public void create() {
        DGame.textures = new Textures();
        DGame.animations = new Animations();
        DGame.fonts = new Fonts();
        main_game = new GameScreen(this);
        setScreen(main_game);
    }

    @Override
    public void dispose() {
        main_game.dispose();
        save_load_screen.dispose();
        main_game.dispose();
    }

    public GameScreen getMainGame() {
        return main_game;
    }

    public MenuScreen getMenuGame() {
        return menu_screen;
    }
}
