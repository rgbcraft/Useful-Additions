package com.rgbcraft.indeng.utils;

import com.rgbcraft.indeng.blocks.Blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {
	public static CreativeTabs IndEng = new CreativeTab(CreativeTabs.getNextID(), "Industrial Engeenering");

	private String name;

	public CreativeTab(int par1, String par2Str) {
		super(par1, par2Str);
		this.name = par2Str;
	}

	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {
		if (this.name == IndEng.getTabLabel()) {
			return new ItemStack(Blocks.get("blockSaltwaterExtractor").blockID, 1, 0);
		}

		return null;
	}

	public String getTranslatedTabLabel() {
		return this.name;
	}
}
