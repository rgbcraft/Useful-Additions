// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.crafting;

import net.minecraftforge.liquids.LiquidStack;
import net.minecraft.item.ItemStack;

public interface ICrucibleRecipe
{
    ItemStack getInput();
    
    LiquidStack getOutput();
    
    int getEnergy();
}
