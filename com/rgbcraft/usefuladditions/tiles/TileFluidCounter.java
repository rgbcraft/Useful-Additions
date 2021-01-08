package com.rgbcraft.usefuladditions.tiles;

import com.rgbcraft.usefuladditions.containers.ContainerBase;
import com.rgbcraft.usefuladditions.utils.IRotableBlock;
import com.rgbcraft.usefuladditions.utils.Utils;

import dan200.computer.api.IComputerAccess;
import dan200.computer.api.IPeripheral;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileFluidCounter extends TileBase implements ITankContainer, IPeripheral, IRotableBlock {

	public LiquidTank tank;
	private int amount = 0;
	private boolean filling = false;
	private boolean redstoneLocked = false;
	private boolean computerLocked = false;
	private String liquidName = null;

	public TileFluidCounter() {
		this.tank = new LiquidTank(1000);
	}
	
	@Override
	public void updateEntity() {
		if (!this.worldObj.isRemote) {
			if (Utils.isRedstonePowered(this.worldObj, this.xCoord, this.yCoord, this.zCoord))
				this.redstoneLocked = true;
			else
				this.redstoneLocked = false;
			
			if (this.tank.getLiquid() != null)
				this.liquidName = this.tank.getLiquid().asItemStack().getDisplayName();
			else
				this.liquidName = null;
		}
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public String getLiquidName() {
		return this.liquidName;
	}
	
	private ForgeDirection getSide() {
		byte[] data = Utils.unmergeBits((byte) this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
		switch (data[1]) {
			case 0:
				return ForgeDirection.DOWN;
			case 1:
				return ForgeDirection.UP;
			case 2:
				return ForgeDirection.NORTH;
			case 3:
				return ForgeDirection.SOUTH;
			case 4:
				return ForgeDirection.WEST;
			case 5:
				return ForgeDirection.EAST;
			default:
				return ForgeDirection.UNKNOWN;
		}
	}

	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		if (this.getSide() == from)
			return this.fill(amount, resource, doFill);
		return 0;
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		if (!this.redstoneLocked && !this.computerLocked) {
			if (this.tank.getLiquid() == null) {
				if (resource.amount <= this.tank.getCapacity()) {
					this.tank.fill(resource, doFill);
					this.amount += resource.amount;
					this.filling = true;
					return resource.amount;
				}
			}
	
			if ((this.tank.getLiquid().amount + resource.amount) <= this.tank.getCapacity()) {
				this.tank.fill(resource, doFill);
				this.amount += resource.amount;
				this.filling = true;
				return resource.amount;
			}
		}
		this.filling = false;
		return 0;
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if (this.getSide().getOpposite() == from)
			if (this.tank.getLiquid() != null)
				if (this.filling)
					return this.drain(0, maxDrain - 1, doDrain);
				else
					return this.drain(0, maxDrain, doDrain);
		return null;
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		if (tankIndex == 0)
			return this.tank.drain(maxDrain, doDrain);
		return null;
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		if (this.getSide() == direction.getOpposite() || this.getSide() == direction)
			return new ILiquidTank[] { this.tank };
			
		return new ILiquidTank[0];
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		if (this.getSide() == direction.getOpposite() || this.getSide() == direction)
			return this.tank;
		return null;
	}
	
	@Override
	public void getGUINetworkData(int key, int value) {
        switch(key) {
            case 0:
            	this.amount = value;
                break;
            default:
            	System.err.println("Industrial Engineering: " + this.toString() + " got unknown GUI network data for key " + key + ": " + value);
                break;
        }
    }
	
	@Override
    public void sendGUINetworkData(ContainerBase container, ICrafting iCrafting) {
        iCrafting.sendProgressBarUpdate(container, 0, this.amount);
    }
	
	@Override
	public void onButtonClick(int buttonId) {
		if (buttonId == 0)
			this.amount = 0;
	}

	@Override
	public String getType() {
		return "fluidCounter";
	}

	@Override
	public String[] getMethodNames() {
		return new String[] {"getAmount", "getLiquid", "reset", "setLocked", "isLocked"};
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, int method, Object[] arguments) throws Exception {
		switch (method) {
			case 0:
				return new Object[] {this.amount};
			case 1:
				return new Object[] {this.getLiquidName()};
			case 2:
				this.amount = 0;
				return new Object[] {true};
			case 3:
				if (arguments[0] instanceof Boolean) {
					this.computerLocked  = (Boolean) arguments[0];
					return new Object[] {this.computerLocked};
				} else {
					throw new IllegalArgumentException("The argument must be a boolean.");
				}
			case 4:
				return new Object[] {this.redstoneLocked || this.computerLocked};
		}
		return null;
	}

	@Override
	public boolean canAttachToSide(int side) {
		return true;
	}

	@Override
	public void attach(IComputerAccess computer) {}

	@Override
	public void detach(IComputerAccess computer) {}
	
//	@Override
//	public String getName() {
//		return "Fluid Counter";
//	}
//
//	@Override
//	public Map<String, String> getRows(Map<String, String> rows) {
//		rows.put("Amount", String.valueOf(Utils.formatNumber(this.getAmount())) + " mB");
//		rows.put("Liquid name", this.getLiquidName() != null ? this.getLiquidName() : "None");
//		return rows;
//	}
	
    @Override
    public void writeToNBT(final NBTTagCompound compound) {
        super.writeToNBT(compound);
        
        compound.setInteger("Amount", this.amount);
        
        if (this.tank.getLiquid() != null) {
            NBTTagCompound tag = new NBTTagCompound();
            this.tank.getLiquid().writeToNBT(tag);
            compound.setCompoundTag("Tank", tag);
        }
    }

    @Override
    public void readFromNBT(final NBTTagCompound compound) {
        super.readFromNBT(compound);
        
        this.amount = compound.getInteger("Amount");
        this.tank.setLiquid(LiquidStack.loadLiquidStackFromNBT(compound.getCompoundTag("Tank")));
    }

}
