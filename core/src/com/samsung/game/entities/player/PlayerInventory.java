package com.samsung.game.entities.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.samsung.game.engine.Drawable;
import com.samsung.game.entities.Entity;
import com.samsung.game.items.Equipable;
import com.samsung.game.items.Inventory;
import com.samsung.game.items.Item;
import com.samsung.game.items.armor.Armour;
import com.samsung.game.items.armor.Helmet;
import com.samsung.game.items.potions.Potion;

public class PlayerInventory {
    private Player player;

    Inventory<Item> main_inventory;
    Equipable<?> item_on_hand;
    Inventory<Potion> potion_bar;
    Item helmet;
    Armour armor;

    PlayerInventory(Player player) {
        this.player = player;
        main_inventory = new Inventory<>(4, 6);
        potion_bar = new Inventory<>(1, 4);
    }

    public void setHelmet(Helmet helmet) {
        helmet.setOwner(player);
        this.helmet = helmet;
        this.helmet.findUIView(Item.UILocation.IN_INVENTORY).visible = true;
    }

    public void setArmour(Armour armor) {
        armor.setOwner(player);
        this.armor = armor;
        this.armor.findUIView(Item.UILocation.ON_SCREEN).visible = true;
        armor.visible = true;
    }

    public void setItemOnHand(Equipable<? super Entity> item_on_hand) {
        item_on_hand.setOwner(player);
        this.item_on_hand = item_on_hand;
    }

    public boolean addPotionToBar(Potion potion) {
        boolean isPut = potion_bar.addItem(potion);
        return isPut;
    }

    public void draw(Batch batch) {
        if (item_on_hand instanceof  Drawable) {
            ((Drawable) item_on_hand).draw(batch);
        }
    }

    public Inventory<Item> getMain_inventory() {
        return main_inventory;
    }
}
