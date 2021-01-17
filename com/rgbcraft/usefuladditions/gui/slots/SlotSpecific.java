package com.rgbcraft.usefuladditions.gui.slots;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotSpecific extends Slot {

	private List<ItemStack> whitelistedItems;

	public SlotSpecific(IInventory inventory, int slotId, int x, int y, ArrayList<ItemStack> arrayList) {
		super(inventory, slotId, x, y);
		
		this.whitelistedItems = arrayList;
	}
	
	@Override
    public boolean isItemValid(ItemStack itemStack) {
		if (itemStack != null)
			for (ItemStack whitelistedItemStack : this.whitelistedItems)
				if (itemStack.isItemEqual(whitelistedItemStack))
					return true;

        return false;
    }

}
