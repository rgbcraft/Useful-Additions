// 
// Decompiled by Procyon v0.5.36
// 

package dan200.computer.api;

public interface IPeripheral
{
    String getType();
    
    String[] getMethodNames();
    
    Object[] callMethod(final IComputerAccess p0, final int p1, final Object[] p2) throws Exception;
    
    boolean canAttachToSide(final int p0);
    
    void attach(final IComputerAccess p0);
    
    void detach(final IComputerAccess p0);
}
