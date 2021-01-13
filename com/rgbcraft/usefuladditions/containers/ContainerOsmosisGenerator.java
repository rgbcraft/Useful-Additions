package com.rgbcraft.usefuladditions.containers;

import java.util.ArrayList;

import com.rgbcraft.usefuladditions.gui.slots.SlotSpecific;
import com.rgbcraft.usefuladditions.items.Items;
import com.rgbcraft.usefuladditions.tiles.TileOsmosisGenerator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerOsmosisGenerator extends Container {
	
	public TileOsmosisGenerator tileOsmosisGenerator;

	public ContainerOsmosisGenerator(InventoryPlayer inventory, TileOsmosisGenerator tileOsmosisGenerator) {
		this.tileOsmosisGenerator = tileOsmosisGenerator;
		
		this.addSlotToContainer(new SlotSpecific((IInventory) tileOsmosisGenerator, 0, 98, 20, new ArrayList<Item>(){{add(Items.get("saltwaterCanister"));}}));
		this.addSlotToContainer(new SlotSpecific((IInventory) tileOsmosisGenerator, 1, 98, 51, new ArrayList<Item>()));
		
		this.bindPlayerInventory((IInventory) inventory);
	}
	
	public void bindPlayerInventory(IInventory inventory) {
        // main 9x3 slot inventory at 8, 84; inventory slots 9-35; container slots 4-30
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventory, 9 + (i * 9) + j, 8 + (j * 18), 84 + (i * 18)));
            }
        }

        // hotbar at 8, 142; inventory slots 0-8; container slots 31-39
        for(int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventory, i, 8 + (i * 18), 142));
        }
    }

//	@Override
//    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
//        Slot slot = (Slot) this.inventorySlots.get(index);
//
//        if (slot == null || (!slot.getHasStack())) {
//            return null;
//        }
//
//        ItemStack originalStack = slot.getStack();
//        ItemStack workStack = originalStack.copy();
//
//        // Slots 0, 1 and 2 are perfectly normal:
//        if ((index >= 0) && (index <= 1)) {
//        	if (workStack.isItemEqual(new ItemStack(Items.get("saltwaterCanister"))) || workStack.isItemEqual(new ItemStack(Items.get("emptyCanister"))))
//	    		if (!mergeItemStack(workStack, 4, inventorySlots.size(), false))
//	    			return null;
//
//        } else if (index >= 2) { // From player inventory
//        	if (workStack.isItemEqual(new ItemStack(Items.get("saltwaterCanister"))))
//        		if (!mergeItemStack(workStack, 0, 1, false))
//        			return null;
//        }
//
//        if (workStack.stackSize == 0) {
//            slot.putStack(null);
//        } else if (workStack.stackSize == originalStack.stackSize) {
//            return null;
//        } else {
//            slot.putStack(workStack);
//            slot.onSlotChanged();
//        }
//
//        slot.onPickupFromSlot(player, workStack);
//
//        return originalStack;
//    }
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(index);
	
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
	
			int containerSlots = inventorySlots.size() - player.inventory.mainInventory.length;
	
			if (index < containerSlots) {
				if (itemstack.isItemEqual(new ItemStack(Items.get("saltwaterCanister"))) || itemstack.isItemEqual(new ItemStack(Items.get("emptyCanister"))))
					if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true))
						return null;
			} else if (itemstack.isItemEqual(new ItemStack(Items.get("saltwaterCanister")))) {
				if (!this.mergeItemStack(itemstack1, 0, containerSlots, false))
					return null;
			}
	
			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
	
			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}
	
			slot.onPickupFromSlot(player, itemstack1);
		}
	
		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return this.tileOsmosisGenerator.isUseableByPlayer(entityPlayer);
	}
	
	@Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for(int i = 0; i < crafters.size(); i++) {
            this.tileOsmosisGenerator.sendGUINetworkData(this, (ICrafting) crafters.get(i));
        }
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int key, int value) {
        this.tileOsmosisGenerator.getGUINetworkData(key, value);
    }

}
