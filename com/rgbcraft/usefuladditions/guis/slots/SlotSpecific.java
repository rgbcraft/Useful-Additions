package com.rgbcraft.usefuladditions.guis.slots;

import java.util.ArrayList;
import java.util.List;

import com.rgbcraft.usefuladditions.utils.Utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class SlotSpecific extends Slot {

    private List<ItemStack> allowedItems;

    public SlotSpecific(IInventory inventory, int slotId, int x, int y, ArrayList<ItemStack> allowedItems) {
        super(inventory, slotId, x, y);

        this.allowedItems = allowedItems;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        if (itemStack != null)
            for (ItemStack whitelistedItemStack : this.allowedItems)
                if (Utils.isSameItem(itemStack, whitelistedItemStack))
                    return true;

        return false;
    }

}
