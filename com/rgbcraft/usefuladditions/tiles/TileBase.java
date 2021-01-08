package com.rgbcraft.usefuladditions.tiles;

import com.rgbcraft.usefuladditions.containers.ContainerBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.tileentity.TileEntity;

public abstract class TileBase extends TileEntity {
	
	public void onDataReceived(byte id, String value, EntityPlayer player) {};

	public void onButtonClick(int buttonId) {};

	public void getGUINetworkData(int key, int value) {};

	public void sendGUINetworkData(ContainerBase containerEmpty, ICrafting iCrafting) {};
	
}
