package com.rgbcraft.usefuladditions.compat;

import net.minecraft.item.ItemStack;


public class GregTechCompat {

    public static boolean addAssemblingMachineRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt) {
        try {
            Class.forName("gregtechmod.GT_Mod").getMethod("addAssemblerRecipe", ItemStack.class, ItemStack.class, ItemStack.class, int.class, int.class).invoke(null, aInput1, aInput2, aOutput1, aDuration, aEUt);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

}
