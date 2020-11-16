// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.core;

public interface IRedstoneControl
{
    boolean getRedstoneDisable();
    
    boolean getRedstoneState();
    
    boolean redstoneControl();
    
    boolean redstoneControlOrDisable();
    
    boolean setRedstoneDisable(final boolean p0);
    
    boolean setRedstoneState(final boolean p0);
    
    boolean setRedstoneInfo(final boolean p0, final boolean p1);
}
