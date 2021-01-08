package com.rgbcraft.usefuladditions.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class TileAdvancedLiquidContainer extends TileEntity implements ILiquidTank, ITankContainer {

	@Override
	public LiquidStack getLiquid() {
		return null;
	}

	@Override
	public int getCapacity() {
		return 20000;
	}

	@Override
	public int fill(LiquidStack resource, boolean doFill) {
//		if(this.getLiquid() == null) {
//			if(resource.amount <= this.getCapacity()) {
//				this.fill(resource, doFill);
//				return resource.amount;
//			}
//		}
//
//		if((this.getLiquid().amount + resource.amount) <= this.getCapacity()) {
//			this.fill(resource, doFill);
//			return resource.amount;
//		}
		return 0;
	}

	@Override
	public LiquidStack drain(int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public int getTankPressure() {
		return 100;
	}

	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		// TODO Auto-generated method stub
		return null;
	}

}
