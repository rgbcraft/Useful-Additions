package com.rgbcraft.usefuladditions.utils;

import com.rgbcraft.usefuladditions.items.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {

    private String name;

    public CreativeTab(int id, String name) {
        super(id, name);

        this.name = name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        return new ItemStack(Items.get("debugger"));
    }

    @Override
    public String getTranslatedTabLabel() {
        return this.name;
    }

}
