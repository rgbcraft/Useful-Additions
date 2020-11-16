// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.crafting;

import net.minecraft.item.ItemStack;

public interface IFurnaceRecipe
{
    ItemStack getInput();
    
    ItemStack getOutput();
    
    int getEnergy();
}
