package com.rgbcraft.indeng.containers;

import com.rgbcraft.indeng.tiles.TileSmartSafe;
import com.rgbcraft.indeng.utils.Sounds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSmartSafeLock extends Container {
	
	private TileSmartSafe tileSmartSafe;

	public ContainerSmartSafeLock(InventoryPlayer inventory, TileSmartSafe tileSmartSafe) {
		this.tileSmartSafe = tileSmartSafe.getTile();
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return this.tileSmartSafe.isUseableByPlayer(entityPlayer);
	}
	
	public TileSmartSafe getTileSmartSafe() {
		return this.tileSmartSafe;
	}
}
