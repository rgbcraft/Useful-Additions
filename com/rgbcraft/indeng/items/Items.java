package com.rgbcraft.indeng.items;

import java.util.HashMap;

import net.minecraft.item.Item;

public class Items {

    public static HashMap<String, Item> items = new HashMap<String, Item>();

    public static void init() {
        for (HashMap.Entry<String, Item> entry : items.entrySet()) {
            entry.getValue().setTextureFile("/com/rgbcraft/indeng/assets/textures/items.png");
        }
    }


    public static void initLanguageNames() {}


    public static Item get(String itemName) {
        return items.get("item." + itemName);
    }

}
