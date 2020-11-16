package com.rgbcraft.indeng.tiles;

import com.rgbcraft.indeng.utils.TileInventory;

import ic2.api.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileSmartSafe extends TileInventory {
	
	private String passCode = "0000";

	public TileSmartSafe(String pin) {
		super("smartSafeInventory", 54);
		
		this.passCode = pin;
	}
	
	public String getPin() {
		return this.passCode;
	}
	
	public void setPin(String pin) {
		this.passCode = pin;
	}
	
	@Override
    public void readFromNBT(final NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.passCode = compound.getString("PIN");
    }
    
    @Override
    public void writeToNBT(final NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("PIN", this.passCode);
    }

}
