package com.samsung.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.utils.TestAssets;

public class AsciiMap implements Map {
     private Tile[][] tile_map;
     private Texture wall_tx1;
     private Texture wall_tx2;

     private Texture floor_tx1;
     private Texture floor_tx2;

     public AsciiMap() {
         wall_tx1 = new Texture("tiles/wall-1-1.png");
         wall_tx2 = new Texture("tiles/wall-1-2.png");
         floor_tx1 = new Texture("tiles/floor-1-1.png");
         floor_tx2 = new Texture("tiles/floor-1-2.png");
     }

     @Override
     public void load() {
          char[][] char_map = TestAssets.map1;
          this.tile_map = new Tile[char_map.length][char_map[0].length];

          for (int f = char_map.length - 1, i = 0; f >= 0; f--, i++) {
               for (int j = 0; j < char_map[0].length; j++) {
                    if (char_map[i][j] == TestAssets.WALL) {
                         Wall wall = (Math.random() > 0.5) ? new Wall(wall_tx1) : new Wall(wall_tx2);
                         wall.x = (int) (wall.getWidth() * j);
                         wall.y = (int) (wall.getHeight() * f);
                         tile_map[i][j] = wall;
                    } else if (char_map[i][j] == TestAssets.FLOOR) {
                         Tile tile = new Tile((Math.random() > 0.5) ? floor_tx1 : floor_tx2);
                         tile.x = Tile.SIZE * j;
                         tile.y = Tile.SIZE * f;
                         tile_map[i][j] = tile;
                    }
               }
          }
     }

     @Override
     public void draw(Batch batch) {
          for (int i = 0; i < tile_map.length; i++) {
               for (int j = 0; j < tile_map[0].length; j++) {
                    Tile tile = tile_map[i][j];

                    if (tile != null) {
                         tile.draw(batch);
                    }
               }
          }
     }

     @Override
     public Tile[][] getTiledMap() {
          return tile_map;
     }

     @Override
     public float getX() {
          return 0;
     }

     @Override
     public float getY() {
          return 0;
     }

}
