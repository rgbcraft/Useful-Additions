package com.rgbcraft.indeng.containers;

import com.rgbcraft.indeng.tiles.TileSmartSafe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSmartSafe extends Container {
	
	private TileSmartSafe tileSmartSafe;

	public ContainerSmartSafe(InventoryPlayer inventory, TileSmartSafe tileSmartSafe) {
		this.tileSmartSafe = tileSmartSafe;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return tileSmartSafe.isUseableByPlayer(entityPlayer);
	}
	
	public TileSmartSafe getTileSmartSafe() {
		return tileSmartSafe;
	}

}
