package com.rgbcraft.usefuladditions.containers;

import com.rgbcraft.usefuladditions.tiles.TileBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;

public class ContainerBase extends Container {
	
	public TileBase tileEntity;

	public ContainerBase(TileBase tileEntity) {
		this.tileEntity = tileEntity;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return entityPlayer.getDistanceSq(this.tileEntity.xCoord + 0.5, this.tileEntity.yCoord + 0.5, this.tileEntity.zCoord + 0.5) <= 64;
	}
}
