package com.rgbcraft.usefuladditions.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;


public class TileInventory extends TileEntity implements IInventory {

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
        ItemStack itemStack = this.getStackInSlot(i);

        if (itemStack != null)
            if (itemStack.stackSize <= count)
                this.setInventorySlotContents(i, null);
            else {
                itemStack = itemStack.splitStack(count);
                this.onInventoryChanged();
            }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        ItemStack item = this.getStackInSlot(i);
        this.setInventorySlotContents(i, null);
        return item;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        this.items[i] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
            itemStack.stackSize = this.getInventoryStackLimit();

        this.onInventoryChanged();
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
        return entityPlayer.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5) <= 64;
    }

    @Override
    public void openChest() {}

    @Override
    public void closeChest() {}

    public void addToSlot(int slot, ItemStack itemStack) {
        ItemStack stackInSlot = this.getStackInSlot(slot);
        if (stackInSlot != null && stackInSlot.isItemEqual(itemStack)) {
            if (stackInSlot.stackSize < 64)
                stackInSlot.stackSize += itemStack.stackSize;
            else
                return;
        } else
            stackInSlot = itemStack;
        this.setInventorySlotContents(slot, stackInSlot);
    }

    public boolean canAddToSlot(int slotID, int amount) {
        ItemStack slotContent = this.getStackInSlot(slotID);
        if (slotContent != null && slotContent.stackSize >= slotContent.getMaxStackSize())
            return false;
        return true;
    }

    public boolean isInventoryEmpty() {
        for (int i = 0; i < this.getSizeInventory(); i++) {
            ItemStack itemStack = this.getStackInSlot(i);
            if (itemStack != null)
                return false;
        }
        return true;
    }

    public boolean allowInventoryDrop() {
        return true;
    }

    @Override
    public void writeToNBT(final NBTTagCompound compound) {
        super.writeToNBT(compound);

        NBTTagList saveItems = new NBTTagList();

        for (int i = 0; i < this.getSizeInventory(); i++) {
            ItemStack itemStack = this.getStackInSlot(i);

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

            if (slot >= 0 && slot < this.getSizeInventory())
                this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
        }
    }

}
