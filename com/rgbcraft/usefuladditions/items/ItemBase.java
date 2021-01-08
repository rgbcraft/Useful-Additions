package com.rgbcraft.usefuladditions.items;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.utils.CreativeTab;

import net.minecraft.item.Item;

public class ItemBase extends Item {

	public ItemBase(int id) {
		super(id);
		
		setTextureFile(Items.itemsTextureFile);
		setCreativeTab(UsefulAdditions.creativeTab);
	}

}
