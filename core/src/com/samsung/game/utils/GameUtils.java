package com.samsung.game.utils;

import com.badlogic.gdx.math.Rectangle;
import com.samsung.game.engine.Side;
import com.samsung.game.map.Tile;

import java.awt.geom.Line2D;

public class GameUtils {
    public static float relatedFrom(float a, float b, float an) {
        return b * (an / a);
    }

    /** north: x, y + height, x + width, y + height
     * <br>south: x, y, x + width, y</br>
     * <br>east: x + width, y, x + width, y + height</br>
     * <br>west: x, y, x, y + height</br>
    */
    public static Side defineSideFrom(float x, float y, Rectangle obj2) {
        float center_x = obj2.x + obj2.width / 2;
        float center_y = obj2.y + obj2.height / 2;

        float obx = obj2.x, oby = obj2.y;
        float obw = obj2.width, obh = obj2.height;

        if (isLinesIntersects(x, y, center_x, center_y,
                obx, oby + obh, obx + obw, oby + obh)) {
            return Side.NORTH;
        }
        else if (isLinesIntersects(x, y, center_x, center_y,
                obx, oby, obx + obw, oby)) {
            return Side.SOUTH;
        }
        else if (isLinesIntersects(x, y, center_x, center_y,
                obx + obw, oby, obx + obw, oby + obh)) {
            return Side.EAST;
        }
        else if (isLinesIntersects(x, y, center_x, center_y,
                obx, oby, obx, oby + obh)) {
            return Side.WEST;
        }
        return Side.INSIDE;
    }


    public static boolean isLinesIntersects(float l1x1, float l1y1, float l1x2, float l1y2,
                                            float l2x1, float l2y1, float l2x2, float l2y2) {
        return Line2D.linesIntersect(l1x1, l1y1, l1x2, l1y2, l2x1, l2y1, l2x2, l2y2);
    }

    public static <T> int[] findTileByCoords(T[][] map, int x, int y) {
        int row = 0, col = 0;

        if (x > 0 || y > 0) {
            row = x / Tile.SIZE;
            col = map.length - y / Tile.SIZE - 1;
        }
        return new int[] {col, row};
    }
}
