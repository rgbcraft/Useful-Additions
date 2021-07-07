package com.rgbcraft.usefuladditions.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;


public class ContainerBase extends Container {

    public TileEntity tileEntity;

    public ContainerBase(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return entityPlayer.getDistanceSq(this.tileEntity.xCoord + 0.5, this.tileEntity.yCoord + 0.5, this.tileEntity.zCoord + 0.5) <= 64;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.length;

            if (index < containerSlots) {
                if (!this.mergeItemStack(itemstack1, containerSlots, this.inventorySlots.size(), true))
                    return null;
            } else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false))
                return null;

            if (itemstack1.stackSize == 0)
                slot.putStack(null);
            else
                slot.onSlotChanged();

            if (itemstack1.stackSize == itemstack.stackSize)
                return null;

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

    public void bindPlayerInventory(IInventory inventory) {
        // main 9x3 slot inventory at 8, 84; inventory slots 9-35; container slots 4-30
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                this.addSlotToContainer(new Slot(inventory, 9 + i * 9 + j, 8 + j * 18, 84 + i * 18));

        // hotbar at 8, 142; inventory slots 0-8; container slots 31-39
        for (int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
    }

}
