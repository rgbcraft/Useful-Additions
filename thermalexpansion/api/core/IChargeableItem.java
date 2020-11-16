// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.core;

import net.minecraft.item.ItemStack;

public interface IChargeableItem
{
    float receiveEnergy(final ItemStack p0, final float p1, final boolean p2);
    
    float transferEnergy(final ItemStack p0, final float p1, final boolean p2);
    
    float getEnergyStored(final ItemStack p0);
    
    float getMaxEnergyStored(final ItemStack p0);
}
