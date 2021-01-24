package com.rgbcraft.usefuladditions.items;

import com.rgbcraft.usefuladditions.liquids.Liquids;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;


public class ItemMetaLiquid extends ItemMeta {

    public static ItemMeta instance;

    public ItemMetaLiquid(int id) {
        super(id);

        ItemMetaLiquid.instance = this;

        this.setTextureFile(Utils.getResource(ResourceType.TEXTURE, "liquidsfx.png"));
        this.setCreativeTab(null);
        this.setItemName("liquid");
    }

    public static ItemStack[] getStackList() {
        return ItemMetaLiquid.instance.items;
    }

    public static ItemStack addItem(int metadata, String name, String unlocalizedDisplayName, ItemStack fullContainer, ItemStack emptyContainer) {
        ItemMetaLiquid.instance.items[metadata] = LiquidDictionary.getOrCreateLiquid("usefuladditions." + name, new LiquidStack(ItemMetaLiquid.instance.itemID, LiquidContainerRegistry.BUCKET_VOLUME, metadata)).asItemStack();
        ItemMetaLiquid.instance.itemNames[metadata] = name;

        LiquidStack liquidStack = new LiquidStack(ItemMetaLiquid.instance.items[metadata].itemID, LiquidContainerRegistry.BUCKET_VOLUME, ItemMetaLiquid.instance.items[metadata].getItemDamage());
        LiquidContainerRegistry.registerLiquid(new LiquidContainerData(liquidStack, fullContainer, emptyContainer));
        Liquids.liquids.put(name, liquidStack);

        return ItemMetaLiquid.instance.getSubItem(metadata, LiquidContainerRegistry.BUCKET_VOLUME);
    }

}
