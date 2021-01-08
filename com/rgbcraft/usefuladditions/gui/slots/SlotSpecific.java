package com.rgbcraft.usefuladditions.gui.slots;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotSpecific extends Slot {

	private List<Item> whitelistedItems;

	public SlotSpecific(IInventory inventory, int slotId, int x, int y, ArrayList<Item> arrayList) {
		super(inventory, slotId, x, y);
		
		this.whitelistedItems = arrayList;
	}
	
	@Override
    public boolean isItemValid(ItemStack itemStack) {
		if (itemStack != null)
			for (Item whitelistedItemStack : this.whitelistedItems)
				if (itemStack.isItemEqual(new ItemStack(whitelistedItemStack)))
					return true;

        return false;
    }

}
