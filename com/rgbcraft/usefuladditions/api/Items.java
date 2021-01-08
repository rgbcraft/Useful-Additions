package com.rgbcraft.usefuladditions.api;

import net.minecraft.item.Item;

public class Items {
	
	/*
	* Get an Item for a specific name, example: Items.get("emptyCanister")
	*
	* @param itemName Item name
	* @return The item or null if the item does not exist or an error occurred
	*/
	public static Item get(String itemName) {
        try {
			return (Item) Class.forName("com.rgbcraft.usefuladditions.items.Items").getDeclaredMethod("get", String.class).invoke(null, itemName);
		} catch (Exception e) {
			System.out.println("Useful Additions API - ERROR: Cannot get item: " + itemName);
			return null;
		}
    }
}
