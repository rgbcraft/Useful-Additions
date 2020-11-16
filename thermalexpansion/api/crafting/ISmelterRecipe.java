// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.crafting;

import net.minecraft.item.ItemStack;

public interface ISmelterRecipe
{
    ItemStack getPrimaryInput();
    
    ItemStack getSecondaryInput();
    
    ItemStack getPrimaryOutput();
    
    ItemStack getSecondaryOutput();
    
    int getSecondaryOutputChance();
    
    int getEnergy();
}
