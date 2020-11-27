package com.rgbcraft.indeng.items;

import java.util.HashMap;

import com.rgbcraft.indeng.utils.CreativeTab;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class Items {

    public static HashMap<String, Item> items = new HashMap<String, Item>();

    public static void init() {
    	Item itemCodebreaker = new ItemCodebreaker(3004);
    	items.put(itemCodebreaker.getItemName(), itemCodebreaker);
    	
    	// Crafting Items
    	Item itemLCDScreen = new Item(3005).setIconIndex(15).setItemName("itemLCDScreen");
    	items.put(itemLCDScreen.getItemName(), itemLCDScreen);
    	
        for (HashMap.Entry<String, Item> entry : items.entrySet()) {
            entry.getValue().setTextureFile("/com/rgbcraft/indeng/assets/textures/items.png").setCreativeTab(CreativeTab.IndEng);
        }
    }

    public static void initLanguageNames() {
    	LanguageRegistry.addName(Items.get("itemCodebreaker"), "Codebreaker");
    	
    	// Crafting Item
    	LanguageRegistry.addName(Items.get("itemLCDScreen"), "LCD Screen");
    }


    public static Item get(String itemName) {
        return items.get("item." + itemName);
    }

}
