package com.samsung.game.items.potions;

import com.samsung.game.items.Item;
import com.samsung.game.items.PlayerUsable;
import com.samsung.game.map.Tile;

public abstract class Potion extends Item implements PlayerUsable {
    public Potion() {
        texture = icon_texture;
        size.width = size.height = Tile.SIZE;
    }

    @Override
    public String getItemName() {
        return "Potion";
    }



}
