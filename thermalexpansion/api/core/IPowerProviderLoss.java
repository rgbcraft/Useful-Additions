// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.core;

import net.minecraftforge.common.ForgeDirection;

public interface IPowerProviderLoss extends IPowerProviderAdv
{
    void addEnergyWithoutLoss(final float p0);
    
    void receiveEnergyWithoutLoss(final float p0, final ForgeDirection p1);
}
