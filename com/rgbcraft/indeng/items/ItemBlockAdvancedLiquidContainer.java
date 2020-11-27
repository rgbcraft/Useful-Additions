package com.rgbcraft.indeng.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockAdvancedLiquidContainer extends ItemBlock {
	
	public ItemBlockAdvancedLiquidContainer(int id) {
		super(id);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List lines, boolean par4) {
		lines.add("§9Struttura Multiblocco");
		lines.add("3 x 3 x 3 (Vuoto)");
	}
}
