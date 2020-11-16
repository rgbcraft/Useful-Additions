// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.crafting;

import net.minecraft.item.ItemStack;

public interface IPulverizerRecipe
{
    ItemStack getInput();
    
    ItemStack getPrimaryOutput();
    
    ItemStack getSecondaryOutput();
    
    int getSecondaryOutputChance();
    
    int getEnergy();
}
