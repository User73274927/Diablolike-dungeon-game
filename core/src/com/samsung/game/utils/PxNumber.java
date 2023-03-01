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

    private static TextureRegion[] numbers;
    private static Texture[] signs;

    static {
        Texture number_texture = new Texture("other/numbers.png");
        TextureRegion[][] split_number_texture = TextureRegion.split(
                number_texture, number_texture.getWidth() / DIGIT_COUNT, number_texture.getHeight()
        );
        numbers = new TextureRegion[DIGIT_COUNT];

        for (int i = 0; i < DIGIT_COUNT; i++) {
            numbers[i] = split_number_texture[0][i];
        }

        System.out.println(numbers[2]);
    }

    public int number;
    private Vector2 pos;
    private int height;

    public PxNumber(int number, int x, int y, int height) {
        this.pos = new Vector2();
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
            float width = GameUtils.relatedFrom(
                    digit_nm.getRegionHeight(), digit_nm.getRegionWidth(), height
            );

            batch.draw(digit_nm, x, pos.y, width, height);
            x += width + width / 5;
        }
    }
}
