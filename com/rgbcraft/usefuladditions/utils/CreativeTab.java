package com.rgbcraft.usefuladditions.utils;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.blocks.Blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {
	private String name;

	public CreativeTab(int id, String name) {
		super(id, name);

		this.name = name;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		if (this.name == UsefulAdditions.creativeTab.getTabLabel()) {
			return new ItemStack(Blocks.get("smartSafe"), 1, 1);
		}

		return null;
	}
	
	@Override
	public String getTranslatedTabLabel() {
		return this.name;
	}

}
