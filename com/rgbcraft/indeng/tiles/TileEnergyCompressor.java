package com.rgbcraft.indeng.tiles;

import com.rgbcraft.indeng.utils.TileInventory;

import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;

public class TileEnergyCompressor extends TileInventory implements IPowerReceptor {

	private boolean isActive;

	public TileEnergyCompressor() {
		super("inventoryEnergyCompressor", 2);
	}

	@Override
	public void setPowerProvider(IPowerProvider provider) {}

	@Override
	public IPowerProvider getPowerProvider() {
		return null;
	}

//	@Override
//	public void doWork() {
//		if (!worldObj.isRemote) {
//			return;
//		}
//		boolean curActive = isActive;
//
//		if (isActive) {
//			int energy = Math.min(1000, calcEnergy());
//
//			if (EnergyHelper.isEnergyContainerItem(inventory[0])) {
//				IEnergyContainerItem theItem = (IEnergyContainerItem) inventory[0].getItem();
//				energyStorage.extractEnergy(theItem.receiveEnergy(inventory[0], energy, false), false);
//
//				if (theItem.getEnergyStored(inventory[0]) >= theItem.getMaxEnergyStored(inventory[0]) || (energyStorage.getEnergyStored() > 0 && energy <= 0)) {
//					processFinish();
//					transferProducts();
//					if (!redstoneControlOrDisable() || !canStart()) {
//						isActive = false;
//						wasActive = true;
//						tracker.markTime(worldObj);
//					}
//				}
//			} else if (energyStorage.getEnergyStored() <= 0 || inventory[1] == null) {
//				isActive = false;
//				wasActive = true;
//				tracker.markTime(worldObj);
//			}
//		} else if (redstoneControlOrDisable()) {
//			if (timeCheck()) {
//				transferProducts();
//			}
//			if (canStart()) {
//				isActive = true;
//			}
//		}
//		updateIfChanged(curActive);
//	}

	@Override
	public int powerRequest() {
		return 2;
	}

	@Override
	public void doWork() {
		// TODO Auto-generated method stub
		
	}

}
