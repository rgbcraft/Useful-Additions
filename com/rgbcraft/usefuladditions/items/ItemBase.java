package com.rgbcraft.usefuladditions.items;

import com.rgbcraft.usefuladditions.UsefulAdditions;

import net.minecraft.item.Item;


public class ItemBase extends Item {

    public ItemBase(int id) {
        super(id);

        this.setTextureFile(Items.textureFile);
        this.setCreativeTab(UsefulAdditions.creativeTab);
    }

}
