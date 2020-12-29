package com.rgbcraft.indeng.tiles;

import com.rgbcraft.indeng.IndustrialEngineering;
import com.rgbcraft.indeng.utils.Sounds;
import com.rgbcraft.indeng.utils.TileInventory;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.Player;
import ic2.api.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;

public class TileSmartSafe extends TileInventory implements ISidedInventory {
	
	private String passCode = "";
	private String owner = "";

	public TileSmartSafe() {
		super("Smart Safe", 54);
	}
	
	public TileSmartSafe getTile() {
		return (TileSmartSafe) this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
	}
	
	public String getPin() {
		return this.passCode;
	}
	
	public void setPin(String pin) {
		this.passCode = pin;
    	System.out.println("xX " + this.getTile().xCoord);
    	System.out.println("yY " + this.getTile().yCoord);
    	System.out.println("zZ " + this.getTile().zCoord);
	}
	
	public void setOwner(String owner) {
		System.out.println("C " + owner);
		System.out.println("F " + this.toString());
		this.owner = owner;
	}
	
	public String getOwner() {
		return this.owner;
	}

	@Override
    public void readFromNBT(final NBTTagCompound compound) {
        super.readFromNBT(compound);
        TileSmartSafe te = (TileSmartSafe) this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
        te.passCode = compound.getString("pin");
        te.owner = compound.getString("owner");
        this.passCode = compound.getString("pin");
        this.owner = compound.getString("owner");
//    	System.out.println("A " + this.getPin());
//    	System.out.println("B " + this.getOwner());
//    	System.out.println("O " + this.toString());
//    	System.out.println(compound.toString());
    }
    
    @Override
    public void writeToNBT(final NBTTagCompound compound) {
    	super.writeToNBT(compound);
//    	System.out.println("Xx " + this.xCoord);
//    	System.out.println("Yy " + this.yCoord);
//    	System.out.println("Zz " + this.zCoord);
//    	System.out.println("A " + this.passCode);
//    	System.out.println("B " + this.owner);
//    	System.out.println("S " + this.toString());
//    	System.out.println(compound.toString());
    	TileSmartSafe te = (TileSmartSafe) this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
    	compound.setString("pin", te.getPin());
        compound.setString("owner", te.getOwner());
        compound.setString("pin", this.getPin());
        compound.setString("owner", this.getOwner());
    }

	public void onButtonClick(byte buttonId, EntityPlayer player) {
		switch (buttonId) {
			case 11:
				FMLNetworkHandler.openGui(player, IndustrialEngineering.instance, 1, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		}
	}

	@Override
	public int getStartInventorySide(ForgeDirection side) {
		return -1;
	}

	@Override
	public int getSizeInventorySide(ForgeDirection side) {
		return -1;
	}

}
