package com.rgbcraft.indeng.items;

import com.rgbcraft.indeng.blocks.Blocks;
import com.rgbcraft.indeng.utils.TileInventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCodebreaker extends Item {

	public ItemCodebreaker(int id) {
		super(id);
		
		setItemName("itemCodebreaker");
		setIconIndex(0);
		setMaxStackSize(1);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && world.getBlockId(x, y,  z) == Blocks.get("blockSmartSafe").blockID) {
			player.displayGUIChest(((TileInventory)world.getBlockTileEntity(x, y, z)).getInventory());
			return true;
		}
		return false;
	}

}
