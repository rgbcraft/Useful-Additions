// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.crafting;

import net.minecraftforge.liquids.LiquidStack;
import net.minecraft.item.ItemStack;

public interface ICrucibleManager
{
    boolean addRecipe(final int p0, final ItemStack p1, final LiquidStack p2, final boolean p3);
    
    @Deprecated
    boolean addRecipe(final int p0, final ItemStack p1, final LiquidStack p2);
    
    ICrucibleRecipe[] getRecipeList();
}
