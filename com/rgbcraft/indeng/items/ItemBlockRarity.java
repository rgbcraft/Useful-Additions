package com.rgbcraft.indeng.items;

import com.rgbcraft.indeng.utils.IRarityBlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockRarity extends ItemBlock {
	public ItemBlockRarity(int id) {
        super(id);
    }
    
	@Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack) {
        if (Block.blocksList[itemStack.itemID] instanceof IRarityBlock) {
            return ((IRarityBlock)Block.blocksList[itemStack.itemID]).getRarity(itemStack);
        }
        return super.getRarity(itemStack);
    }
}
