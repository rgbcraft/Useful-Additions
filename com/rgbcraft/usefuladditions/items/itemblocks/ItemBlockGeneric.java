package com.rgbcraft.usefuladditions.items.itemblocks;

import java.util.List;

import com.rgbcraft.usefuladditions.blocks.Blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockGeneric extends ItemBlock {

	public ItemBlockGeneric(int id) {
		super(id);
		
		setHasSubtypes(true);
	}
	
	@Override
	public String getItemNameIS(ItemStack itemStack) {
		if (itemStack.itemID == Blocks.get("chassis").blockID) {
			return "chassis" + itemStack.getItemDamage();
		}
		return null;
	}
	
	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs creativeTab, List subBlocks) {
		if (id == Blocks.get("chassis").blockID) {
			for (int i = 0; i <= 1; i++) {
				subBlocks.add(new ItemStack(id, 1, i));
			}
		}
	}

}
