package com.rgbcraft.usefuladditions.utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public interface IRarityBlock {
	
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack itemStack);

}
