package com.rgbcraft.usefuladditions.items;

import net.minecraft.item.ItemStack;


public class ItemMetaComponent extends ItemMeta {

    public static ItemMeta instance;

    public ItemMetaComponent(int id) {
        super(id);

        ItemMetaComponent.instance = this;

        this.setItemName("component");
    }

    public static ItemStack[] getStackList() {
        return ItemMetaComponent.instance.items;
    }

    public static void addSubItem(int metadata, String itemName, int iconIndex) {
        ItemStack canister = new ItemStack(ItemMetaComponent.instance.itemID, 1, metadata);
        ItemMetaComponent.instance.items[metadata] = canister;
        ItemMetaComponent.instance.itemNames[metadata] = itemName;

        ItemMetaComponent.instance.icons[metadata] = iconIndex;
    }

}
