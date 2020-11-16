// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.crafting;

import net.minecraft.item.ItemStack;

public interface IFurnaceManager
{
    boolean addRecipe(final int p0, final ItemStack p1, final ItemStack p2, final boolean p3);
    
    @Deprecated
    boolean addRecipe(final int p0, final ItemStack p1, final ItemStack p2);
    
    IFurnaceRecipe[] getRecipeList();
}
