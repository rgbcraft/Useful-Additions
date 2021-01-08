package com.rgbcraft.usefuladditions.items.itemblocks;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockDenseOre extends ItemBlock {

	public ItemBlockDenseOre(int id) {
		super(id);
		setHasSubtypes(true);
	}
	
	@Override
	public String getItemNameIS(ItemStack itemStack) {
		return "denseOre" + itemStack.getItemDamage();
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	@Override
	public void getSubItems(int id, CreativeTabs creativeTab, List subBlocks) {
		for (int i = 0; i <= 6; i++) {
			subBlocks.add(new ItemStack(id, 1, i));
		}
	}

}
