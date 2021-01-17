package com.rgbcraft.usefuladditions.tiles;

import java.util.HashMap;
import java.util.Map;

import com.rgbcraft.usefuladditions.api.IDebuggable;
import com.rgbcraft.usefuladditions.utils.LanguageManager;

import buildcraft.api.core.Position;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileSaltwaterExtractor extends TileEntity implements ITankContainer, IDebuggable {

	private LiquidTank tank = new LiquidTank(2000);
	private boolean istantiated = false;
	
	private int checkWater() {
		int waterSide = 0;
		
		for (int i = 0; i < 6; ++i) {
			Position p = new Position(xCoord, yCoord, zCoord, ForgeDirection.values()[i]);
			p.moveForwards(1);
			
			if (worldObj.getBlockId((int) p.x, (int) p.y, (int) p.z) == Block.waterMoving.blockID || 
					worldObj.getBlockId((int) p.x, (int) p.y, (int) p.z) == Block.waterStill.blockID) {
				waterSide++;
			}
		}
		
		return waterSide;
	}

	@Override
	public void updateEntity() {
		if (!this.worldObj.isRemote) {
			if (this.yCoord <= 55)
				this.istantiated = this.checkWater() >= 5;

			if (this.istantiated) {
				if (this.tank.getLiquid() != null) {
					if ((this.tank.getLiquid().amount + 100) <= this.tank.getCapacity())
						this.tank.fill(LiquidDictionary.getLiquid("usefuladditions.saltWater", 100), true);
				} else {
					this.tank.fill(LiquidDictionary.getLiquid("usefuladditions.saltWater", 100), true);
				}
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

	@Override
	public Map<String, Boolean> getRequirements(EntityPlayer player, HashMap<String, Boolean> requirements) {
		requirements.put(LanguageManager.addFormattedTranslation("misc", "misc.saltwaterExtractor.debug.requirement1", "At least 5 sides in contact with water. ({0})", checkWater()), checkWater() >= 5);
		requirements.put(LanguageManager.addFormattedTranslation("misc", "misc.saltwaterExtractor.debug.requirement2", "Vertical value must be equal or lower than 55. ({0})", this.yCoord), this.yCoord <= 55);
		return requirements;
	}

	@Override
	public Map<String, String> getAdditionalAdvancedInfos(EntityPlayer player, HashMap<String, String> additionalInfos) {
		return null;
	}

}
