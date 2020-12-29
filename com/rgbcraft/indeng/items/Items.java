package com.rgbcraft.indeng.items;

import java.util.ArrayList;
import java.util.HashMap;

import com.rgbcraft.indeng.utils.CreativeTab;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class Items {

    private static HashMap<String, Item> items = new HashMap<String, Item>();

    public static void init() {
    	Item itemCodebreaker = new ItemCodebreaker(3004).setTextureFile("/com/rgbcraft/indeng/assets/textures/items.png");
    	items.put(itemCodebreaker.getItemName(), itemCodebreaker);
    	
    	// Liquids
    	Item emptyCanister = new Item(3005).setItemName("emptyCanister").setTextureFile("/com/rgbcraft/indeng/assets/textures/liquids.png").setIconIndex(0);
    	items.put(emptyCanister.getItemName(), emptyCanister);
    	
    	Item saltWaterCanister = new ItemCanister(3006, 0, "H2O, NaCl", true).setItemName("saltWaterCanister");
    	items.put(saltWaterCanister.getItemName(), saltWaterCanister);
    	
    	// Crafting Items
    	Item itemLCDScreen = new Item(3007).setIconIndex(15).setItemName("itemLCDScreen").setTextureFile("/com/rgbcraft/indeng/assets/textures/items.png");
    	items.put(itemLCDScreen.getItemName(), itemLCDScreen);
    	
        for (HashMap.Entry<String, Item> entry : items.entrySet()) {
            entry.getValue().setCreativeTab(CreativeTab.IndEng);
        }
    }

    public static void initLanguageNames() {
    	LanguageRegistry.addName(Items.get("itemCodebreaker"), "Codebreaker");
    	
    	// Liquids
    	LanguageRegistry.addName(Items.get("emptyCanister"), "Empty Canister");
    	LanguageRegistry.addName(Items.get("saltWaterCanister"), "Salt Water Canister");
    	
    	// Crafting Item
    	LanguageRegistry.addName(Items.get("itemLCDScreen"), "LCD Screen");
    }


    public static Item get(String itemName) {
        return items.get("item." + itemName);
    }

}
