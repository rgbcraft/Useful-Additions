// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.core;

import buildcraft.api.power.IPowerProvider;

public interface IPowerProviderAdv extends IPowerProvider
{
    void addEnergy(final float p0);
    
    void subtractEnergy(final float p0);
    
    void setEnergyStored(final float p0);
    
    void roundEnergyStored();
}
