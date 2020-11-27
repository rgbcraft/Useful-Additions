package com.rgbcraft.indeng.tiles;

import com.rgbcraft.indeng.utils.TileInventory;

import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import net.minecraft.tileentity.TileEntity;

public class TileEnergyCompressor extends TileInventory implements IPowerReceptor {

	public TileEnergyCompressor() {
		super("inventoryEnergyCompressor", 2);
	}

	@Override
	public void setPowerProvider(IPowerProvider provider) {}

	@Override
	public IPowerProvider getPowerProvider() {
		return null;
	}

	@Override
	public void doWork() {
		System.out.println("ciao");
		
	}

	@Override
	public int powerRequest() {
		return 2;
	}

}
