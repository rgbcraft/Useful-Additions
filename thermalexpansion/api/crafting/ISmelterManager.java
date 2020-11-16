// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.crafting;

import net.minecraft.item.ItemStack;

public interface ISmelterManager
{
    boolean addRecipe(final int p0, final ItemStack p1, final ItemStack p2, final ItemStack p3, final boolean p4);
    
    @Deprecated
    boolean addRecipe(final int p0, final ItemStack p1, final ItemStack p2, final ItemStack p3);
    
    boolean addRecipe(final int p0, final ItemStack p1, final ItemStack p2, final ItemStack p3, final ItemStack p4, final boolean p5);
    
    @Deprecated
    boolean addRecipe(final int p0, final ItemStack p1, final ItemStack p2, final ItemStack p3, final ItemStack p4);
    
    boolean addRecipe(final int p0, final ItemStack p1, final ItemStack p2, final ItemStack p3, final ItemStack p4, final int p5, final boolean p6);
    
    @Deprecated
    boolean addRecipe(final int p0, final ItemStack p1, final ItemStack p2, final ItemStack p3, final ItemStack p4, final int p5);
    
    ISmelterRecipe[] getRecipeList();
}
