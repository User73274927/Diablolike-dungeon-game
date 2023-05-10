package com.samsung.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.DGame;
import com.samsung.game.entities.Entity;
import com.samsung.game.entities.Monster;
import com.samsung.game.entities.player.Player;
import com.samsung.game.utils.TestAssets;

import java.util.HashSet;
import java.util.Set;

public class AsciiMap implements Map {
     private Tile[][] tile_map;
     private final char[][] char_map;
     private Player player;
     private Set<Entity> entities;
     private Texture w1;
     private Texture w2;
     private Texture f1;
     private Texture f2;
     private Texture ex;

     public AsciiMap(char[][] char_map, Player player) {
          this.char_map = char_map;
          entities = new HashSet<>();
          this.player = player;

          w1 = DGame.textures.getTexture("tiles/texture-pack1/wall-1.png");
          w2 = DGame.textures.getTexture("tiles/texture-pack1/wall-2.png");
          f1 = DGame.textures.getTexture("tiles/texture-pack1/floor-1.png");
          f2 = DGame.textures.getTexture("tiles/texture-pack1/floor-2.png");
          ex = DGame.textures.getTexture("tiles/next-level.png");
     }

     @Override
     public void load() {
          this.tile_map = new Tile[char_map.length][char_map[0].length];

          for (int f = char_map.length - 1, i = 0; f >= 0; f--, i++) {
               for (int j = 0; j < char_map[0].length; j++) {
                    if (char_map[i][j] == TestAssets.WALL) {
                         Wall wall = (Math.random() >= 0.5) ? new Wall(w1) : new Wall(w2);
                         wall.x = (int) (wall.getWidth() * j);
                         wall.y = (int) (wall.getHeight() * f);
                         tile_map[i][j] = wall;
                    } else if (char_map[i][j] == TestAssets.FLOOR) {
                         Tile tile = (Math.random() >= 0.5) ? new Tile(f1) : new Tile(f2);
                         tile.x = Tile.SIZE * j;
                         tile.y = Tile.SIZE * f;
                         tile_map[i][j] = tile;
                    } else if (char_map[i][j] == '0') {
                         Tile tile = new Tile(ex);
                         tile.x = Tile.SIZE * j;
                         tile.y = Tile.SIZE * f;
                    } else if (char_map[i][j] == 'E') {
                         entities.add(new Monster(Tile.SIZE*j, Tile.SIZE*f));
                         Tile tile = (Math.random() >= 0.5) ? new Tile(f1) : new Tile(f2);
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

     public Set<Entity> getEntities() {
          return entities;
     }

     @Override
     public Tile[][] getTiledMap() {
          return tile_map;
     }

     public char[][] getCharMap() {
          return char_map;
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
