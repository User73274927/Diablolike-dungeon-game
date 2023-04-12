package com.samsung.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.samsung.game.engine.Drawable;
import com.samsung.game.utils.TestAssets;

public class Map implements Drawable {
     private Tile[][] tile_map;
     private Texture wall_tx;
     public final World world;

     public Map() {
         wall_tx = new Texture("tiles/wall-example1.png");
         world = new World(new Vector2(0, 0), true);
     }

     public void load() {
          char[][] char_map = TestAssets.map1;
          this.tile_map = new Tile[char_map.length][char_map[0].length];

          for (int f = char_map.length - 1, i = 0; f >= 0; f--, i++) {
               for (int j = 0; j < char_map[0].length; j++) {
                    if (char_map[i][j] == TestAssets.WALL) {
                         Wall wall = new Wall(wall_tx);
                         wall.x = (int) (wall.getWidth() * j);
                         wall.y = (int) (wall.getHeight() * f);
                         tile_map[i][j] = wall;
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
                         batch.draw(tile.getTexture(), tile.x, tile.y, Tile.SIZE, Tile.SIZE);
                    }
               }
          }
     }

     public Tile[][] getTileMap() {
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
