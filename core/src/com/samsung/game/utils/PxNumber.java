package com.samsung.game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.samsung.game.engine.Component;

public class PxNumber implements Component {
    private static final int DIGIT_COUNT = 10;
    private static final int SIGH_COUNT = 5;
    private static final int NUM_WIDTH;
    private static final int NUM_HEIGHT;

    private static TextureRegion[] numbers;
    private static Texture[] signs;

    static {
        Texture number_texture = new Texture("other/numbers.png");
        NUM_WIDTH = number_texture.getWidth() / DIGIT_COUNT;
        NUM_HEIGHT = number_texture.getHeight();

        TextureRegion[][] split_number_texture = TextureRegion.split(
                number_texture, NUM_WIDTH, NUM_HEIGHT
        );

        numbers = new TextureRegion[DIGIT_COUNT];

        for (int i = 0; i < DIGIT_COUNT; i++) {
            numbers[i] = split_number_texture[0][i];
        }

        System.out.println(numbers[2]);
    }

    public int number;
    private Vector2 pos;
    private int width, height;

    public PxNumber(int number, int x, int y, int height) {
        this.pos = new Vector2();
        this.width = (int) GameUtils.relatedFrom(
                NUM_HEIGHT, NUM_WIDTH, height
        );
        this.number = number;
        this.height = height;
        this.pos.add(x, y);
    }

    @Override
    public void draw(Batch batch) {
        String str_num = "" + number;
        int x = (int) pos.x;

        for (char digit : str_num.toCharArray()) {
            TextureRegion digit_nm = numbers[Character.getNumericValue(digit)];
            batch.draw(digit_nm, x, pos.y, width, height);
            x += width + width / 5;
        }
    }

    @Override
    public float getX() {
        return pos.x;
    }

    @Override
    public float getY() {
        return pos.y;
    }
}
