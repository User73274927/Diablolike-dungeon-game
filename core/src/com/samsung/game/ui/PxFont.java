package com.samsung.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.samsung.game.utils.GameUtils;

public class PxFont extends UIComponent {
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

    public Integer number;
    private int width, height;

    public PxFont(Integer number, int height) {
        this.width = (int) GameUtils.relatedFrom(
                NUM_HEIGHT, NUM_WIDTH, height
        );
        this.number = number;
        this.height = height;
    }

    @Override
    public void draw(Batch batch, float n) {
        String str_num = "" + number;
        int x = (int) getX();

        if (number < 0) {
            batch.draw(numbers[0], getX(), getY(), width, height);
            return;
        }

        for (char digit : str_num.toCharArray()) {
            TextureRegion digit_nm = numbers[Character.getNumericValue(digit)];
            batch.draw(digit_nm, x, getY(), width, height);
            x += width + width / 5;
        }
    }

}
