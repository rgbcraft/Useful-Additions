package com.rgbcraft.indeng.containers;

import com.rgbcraft.indeng.tiles.TileSmartSafe;
import com.rgbcraft.indeng.utils.Sounds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSmartSafeInventory extends Container {
	
	private TileSmartSafe tileSmartSafe;
	private int numRows;

	public ContainerSmartSafeInventory(InventoryPlayer playerInventory, TileSmartSafe tileSmartSafe) {
		this.tileSmartSafe = tileSmartSafe.getTile();
		
		this.numRows = this.tileSmartSafe.getSizeInventory() / 9;
        int var3 = (this.numRows - 4) * 18;
        for (int x = 0; x < this.numRows; ++x) {
            for (int y = 0; y < 9; ++y) {
                this.addSlotToContainer(new Slot(tileSmartSafe, y + x * 9, 8 + y * 18, 18 + x * 18));
            }
        }

        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 9; ++y) {
                this.addSlotToContainer(new Slot(playerInventory, y + x * 9 + 9, 8 + y * 18, 104 + x * 18 + var3));
            }
        }

        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, 162 + var3));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return tileSmartSafe.isUseableByPlayer(entityPlayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 < this.numRows * 9) {
                if (!this.mergeItemStack(var5, this.numRows * 9, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(var5, 0, this.numRows * 9, false)) {
                return null;
            }

            if (var5.stackSize == 0) {
                var4.putStack((ItemStack)null);
            } else {
                var4.onSlotChanged();
            }
        }

        return var3;
    }
}
