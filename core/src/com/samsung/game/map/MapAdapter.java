package com.samsung.game.map;

public class MapAdapter {
    public final Map map;

    public MapAdapter() {
        this.map = new AsciiMap();
    }

    public void update() {

    }

    public boolean checkWall() {
        Tile[][] tile_map = map.getTiledMap();

        for (int i = 0; i < tile_map.length; i++) {
            for (int j = 0; j < tile_map[0].length; j++) {
                Tile tile = tile_map[i][j];

                if (tile instanceof Wall) {

                }
            }
        }
        return false;
    }
}
