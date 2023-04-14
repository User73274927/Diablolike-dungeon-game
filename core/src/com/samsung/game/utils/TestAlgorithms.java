package com.samsung.game.utils;

import com.samsung.game.map.Tile;

public class TestAlgorithms {
    public static int[] findTileByCoords(Tile[][] map, int x, int y) {
        int row = 0, col = 0;

        if (x > 0 || y > 0) {
            row = x / Tile.SIZE;
            col = map.length - y / Tile.SIZE - 1;
        }
        return new int[] {col, row};
    }
}
