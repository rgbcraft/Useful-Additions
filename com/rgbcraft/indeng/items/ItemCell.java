package com.rgbcraft.indeng.items;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCell extends Item {
	
	List<String> toolTip = new ArrayList<String>();
	
	public ItemCell(int par1, List tip) {
		super(par1);
		this.toolTip = tip;
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack parItemStack, EntityPlayer parPlayer, List parList, boolean parBool) {
		for(int i=0; i < this.toolTip.size(); i++) {
			parList.add(this.toolTip.get(i));
		}
	}

}