// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.crafting;

import net.minecraftforge.liquids.LiquidStack;
import net.minecraft.item.ItemStack;

public interface ITransposerRecipe
{
    ItemStack getInput();
    
    ItemStack getOutput();
    
    LiquidStack getLiquid();
    
    int getEnergy();
    
    int getChance();
}
