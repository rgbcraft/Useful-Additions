package com.rgbcraft.usefuladditions.api;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.item.Item;

public class Items {
	
	/*
	* Get an ItemStack for a specific item name, example: Items.getItem("emptyCanister")
	* Make sure to copy() the ItemStack if you want to modify it.
	*
	* @param itemName Item name
	* @return The item or null if the item does not exist or an error occurred
	*/
	public static Item get(String itemName) {
        try {
			return (Item) Class.forName("com.rgbcraft.usefuladditions.items.Items").getDeclaredMethod("get", String.class).invoke(null, itemName);
		} catch (Exception e) {
			System.out.println("Industrial Engineering API - ERROR: Cannot get item: " + itemName);
			return null;
		}
    }
}
