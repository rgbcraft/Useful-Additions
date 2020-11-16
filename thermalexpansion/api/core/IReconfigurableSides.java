// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.core;

public interface IReconfigurableSides
{
    boolean decrSide(final int p0);
    
    boolean incrSide(final int p0);
    
    boolean setSide(final int p0, final int p1);
    
    int getNumSides();
}
