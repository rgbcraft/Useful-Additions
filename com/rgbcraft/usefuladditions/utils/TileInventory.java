package com.rgbcraft.usefuladditions.utils;

import com.rgbcraft.usefuladditions.tiles.TileBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileInventory extends TileBase implements IInventory {

    private ItemStack[] items;
    private String inventoryName;
    
    public TileInventory(String inventoryName, int slots) {
        this.inventoryName = inventoryName;
        this.items = new ItemStack[slots];
    }

    @Override
    public int getSizeInventory() {
        return this.items.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return this.items[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int count) {
        ItemStack itemStack = getStackInSlot(i);
        
        if (itemStack != null) {
            if (itemStack.stackSize <= count) {
                setInventorySlotContents(i, null);
            } else {
                itemStack = itemStack.splitStack(count);
                onInventoryChanged();
            }
        }
        
        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        ItemStack item = getStackInSlot(i);
        setInventorySlotContents(i, null);
        return item;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        this.items[i] = itemStack;
        
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
        
        onInventoryChanged();
    }

    @Override
    public String getInvName() {
        return this.inventoryName;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return entityPlayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }
    
    public void addToSlot(int slot, ItemStack itemStack) {
    	ItemStack stackInSlot = this.getStackInSlot(slot);
    	if (stackInSlot != null && stackInSlot.isItemEqual(itemStack)) {
    		if (stackInSlot.stackSize < 64)
    			stackInSlot.stackSize += itemStack.stackSize;
    		else
    			return;
    	} else {
    		stackInSlot = itemStack;
    	}
    	this.setInventorySlotContents(slot, stackInSlot);
    }
    
    public boolean canAddToSlot(int slotID, int amount) {
    	ItemStack slotContent = this.getStackInSlot(slotID);
    	if (slotContent != null) {
    		if (slotContent.stackSize >= slotContent.getMaxStackSize())
    			return false;
    	}
    	return true;
    }

    @Override
    public void writeToNBT(final NBTTagCompound compound) {
        super.writeToNBT(compound);

        NBTTagList saveItems = new NBTTagList();
        
        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack itemStack = getStackInSlot(i);
            
            if (itemStack != null) {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("Slot", (byte) i);
                itemStack.writeToNBT(item);
                saveItems.appendTag(item);
            }
        }
        
        compound.setTag("Items", saveItems);
    }

    @Override
    public void readFromNBT(final NBTTagCompound compound) {
        super.readFromNBT(compound);

        NBTTagList saveItems = compound.getTagList("Items");
        
        for (int i = 0; i < saveItems.tagCount(); i++) {
            NBTTagCompound item = (NBTTagCompound) saveItems.tagAt(i);
            int slot = item.getByte("Slot");
            
            if (slot >= 0 && slot < getSizeInventory()) {
                setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
            }
        }
    }

}
