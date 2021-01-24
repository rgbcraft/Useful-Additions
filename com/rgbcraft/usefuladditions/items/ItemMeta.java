package com.rgbcraft.usefuladditions.items;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemMeta extends ItemBase {

    public int[] icons;
    public ItemStack[] items;
    public String[] toolTips;
    public String[] itemNames;

    public ItemMeta(int id) {
        super(id);

        setMaxDamage(0);
        setHasSubtypes(true);

        this.icons = new int[256];
        this.toolTips = new String[256];
        this.items = new ItemStack[256];
        this.itemNames = new String[256];
    }

    @Override
    public int getIconFromDamage(int damage) {
        return this.icons[damage];
    }

    @Override
    public int getMetadata(int metadata) {
        return metadata;
    }

    @Override
    public String getItemNameIS(ItemStack itemStack) {
        return this.getItemName() + "." + this.itemNames[itemStack.getItemDamage()];
    }

    public ItemStack getSubItem(int metadata, int amount) {
        if (this.items[metadata] != null || metadata > 0 || metadata <= 256) {
            ItemStack itemStack = this.items[metadata].copy();
            itemStack.stackSize = amount;
            return itemStack;
        }
        return null;
    }

    public static ItemStack[] getStackList() {
        return null;
    }

    public boolean isSubItem(ItemStack itemStack, String itemName) {
        if (itemStack.itemID == this.itemID && String.format("%s.%s", this.getItemName(), this.itemNames[itemStack.getItemDamage()]).equals(String.format("%s.%s", this.getItemName(), itemName)))
            return true;
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs creativeTab, List subItems) {
        for (int i = 0; i < this.items.length; i++)
            if (this.items[i] != null)
                subItems.add(this.getSubItem(i, 1));
    }

}
