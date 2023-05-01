package com.samsung.game.data;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class Textures {
    public static final String BUTTONS = "buttons/";
    public static final String FONT = "buttons/";
    public static final String UI = "ui/";

    public static final String SPRITES = "buttons/";

    public static final String TILES = "tiles/";
    public static final String TEXTURE_PACK1 = TILES+"texture-pack1/";

    private final Map<String, Texture> allTextures;
    private final AssetManager manager;

    public Textures() {
        manager = new AssetManager();
        allTextures = new HashMap<>();

        addTexture(TEXTURE_PACK1 + "floor-1.png");
        addTexture(TEXTURE_PACK1 + "floor-2.png");
        addTexture(TEXTURE_PACK1 + "wall-1.png");
        addTexture(TEXTURE_PACK1 + "wall-2.png");

        addTexture(TILES+"wall-example1.png");
        addTexture(TILES+"next-level.png");

        addTexture("plain-button.png");
        addTexture("projectile-1.png");

        addTexture("sprites/player-sheet-left.png");
        addTexture("sprites/player-sheet-right.png");
        addTexture("sprites/player-sheet-attack.png");
    }


    private void addTexture(String filepath) {
        allTextures.put(filepath, load(filepath));
    }

    public Texture load(String path) {
        AssetDescriptor<Texture> descriptor = new AssetDescriptor<>(path, Texture.class);
        manager.load(descriptor);
        manager.finishLoadingAsset(descriptor);
        return manager.get(descriptor);
    }

    public Texture getTexture(String key) {
        return allTextures.get(key);
    }
}
