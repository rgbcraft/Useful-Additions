// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.crafting;

import net.minecraftforge.liquids.LiquidStack;
import net.minecraft.item.ItemStack;

public interface ITransposerManager
{
    boolean addFillRecipe(final int p0, final ItemStack p1, final ItemStack p2, final LiquidStack p3, final boolean p4, final boolean p5);
    
    @Deprecated
    boolean addFillRecipe(final int p0, final ItemStack p1, final ItemStack p2, final LiquidStack p3, final boolean p4);
    
    boolean addExtractionRecipe(final int p0, final ItemStack p1, final ItemStack p2, final LiquidStack p3, final int p4, final boolean p5, final boolean p6);
    
    @Deprecated
    boolean addExtractionRecipe(final int p0, final ItemStack p1, final ItemStack p2, final LiquidStack p3, final int p4, final boolean p5);
    
    ITransposerRecipe[] getFillRecipeList();
    
    ITransposerRecipe[] getExtractionRecipeList();
}
