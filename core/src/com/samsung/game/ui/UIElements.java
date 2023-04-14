package com.samsung.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class UIElements {
    public static void drawScale(Batch batch, int num, int max_num,
                           Texture tx1, Texture tx2,
                           int count, int scale_unit_size,
                           float x, float y) {

        int nm = max_num / count;
        for (int i = 0; i < count; i++) {
            if (num >= 0) {
                batch.draw(tx1, x, y, scale_unit_size, scale_unit_size);
                num -= nm;
            } else {
                batch.draw(tx2, x, y, scale_unit_size, scale_unit_size);
            }
            x += scale_unit_size;
        }
    }
}
