package com.rgbcraft.indeng.tiles;

import buildcraft.api.core.Position;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileSaltwaterExtractor extends TileEntity implements ITankContainer {

	private LiquidTank tank = new LiquidTank(2000);
	private boolean istantiated = false;
	
	private boolean checkWater() {
		int waterSide = 0;
		
		for (int i = 0; i < 6; ++i) {
			Position p = new Position(xCoord, yCoord, zCoord, ForgeDirection.values()[i]);
			if (worldObj.getBlockId((int) p.x, (int) p.y, (int) p.z) == Block.waterMoving.blockID || 
					worldObj.getBlockId((int) p.x, (int) p.y, (int) p.z) == Block.waterStill.blockID) {
				waterSide++;
			}
		}
		
		return waterSide >= 5;
	}
	
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote && yCoord < 55) {
			istantiated = checkWater();
		}
		if (istantiated) {
			if(this.tank.getLiquid() != null) {
				if((this.tank.getLiquid().amount + 100) <= this.tank.getCapacity()) {
					tank.fill(LiquidDictionary.getLiquid("Salt Water", 100), true);
				}
			} else {
				tank.fill(LiquidDictionary.getLiquid("Salt Water", 100), true);
			}
		}
	}
	
	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return drain(0, maxDrain, doDrain);
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		if (tankIndex == 0)
			return tank.drain(maxDrain, doDrain);
		return null;
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		return new ILiquidTank[] { this.tank };
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		return tank;
	}

}
