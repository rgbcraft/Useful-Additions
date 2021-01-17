package com.rgbcraft.usefuladditions.items;

import com.rgbcraft.usefuladditions.utils.LanguageManager;

import net.minecraft.item.ItemStack;

public class ItemMetaComponent extends ItemMeta {
	
	public static ItemMeta instance;

	public ItemMetaComponent(int id) {
		super(id);

		ItemMetaComponent.instance = this;

		setItemName("component");
	}
	
	public static ItemStack[] getStackList() {
        return ItemMetaComponent.instance.items;
    }
	
	public static void addSubItem(int metadata, String itemName, String unlocalizedDisplayName, int iconIndex) {
		ItemStack canister = new ItemStack(ItemMetaComponent.instance.itemID, 1, metadata);
		ItemMetaComponent.instance.items[metadata] = canister;
		
		ItemMetaComponent.instance.itemNames[metadata] = itemName;
		LanguageManager.addTranslation("items", String.format("%s.%s.name", ItemMetaComponent.instance.getItemName(), itemName), unlocalizedDisplayName);
		
		ItemMetaComponent.instance.icons[metadata] = iconIndex;
	}

}
