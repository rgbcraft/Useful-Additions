package com.rgbcraft.indeng.items;

import java.util.HashMap;

import com.rgbcraft.indeng.utils.CreativeTab;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class Items {

    public static HashMap<String, Item> items = new HashMap<String, Item>();

    public static void init() {
    	Item itemCodebreaker = new ItemCodebreaker(3003);
    	items.put(itemCodebreaker.getItemName(), itemCodebreaker);
    	
        for (HashMap.Entry<String, Item> entry : items.entrySet()) {
            entry.getValue().setTextureFile("/com/rgbcraft/indeng/assets/textures/items.png").setCreativeTab(CreativeTab.IndEng);
        }
    }

    public static void initLanguageNames() {
    	LanguageRegistry.addName(Items.get("itemCodebreaker"), "Codebreaker");
    }


    public static Item get(String itemName) {
        return items.get("item." + itemName);
    }

}
