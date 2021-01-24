package com.rgbcraft.usefuladditions.items.itemblocks;

import java.util.List;

import com.rgbcraft.usefuladditions.blocks.Blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockGeneric extends ItemBlock {

    public ItemBlockGeneric(int id) {
        super(id);

        setHasSubtypes(true);
    }

    @Override
    public String getItemNameIS(ItemStack itemStack) {
        String prefix = "block";

        if (itemStack.itemID == Blocks.get("chassis").blockID)
            prefix = "chassis";
        else if (itemStack.itemID == Blocks.get("denseOre").blockID)
            prefix = "denseOre";

        return "tile." + prefix + "." + itemStack.getItemDamage();
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public void getSubItems(int id, CreativeTabs creativeTab, List subBlocks) {
        int iterations = 0;

        if (id == Blocks.get("chassis").blockID)
            iterations = 1;
        else if (id == Blocks.get("denseOre").blockID)
            iterations = 6;

        for (int i = 0; i <= iterations; i++) {
            subBlocks.add(new ItemStack(id, 1, i));
        }
    }

}
