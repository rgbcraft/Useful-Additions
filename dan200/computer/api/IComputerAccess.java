// 
// Decompiled by Procyon v0.5.36
// 

package dan200.computer.api;

public interface IComputerAccess
{
    int createNewSaveDir(final String p0);
    
    String mountSaveDir(final String p0, final String p1, final int p2, final boolean p3, final long p4);
    
    String mountFixedDir(final String p0, final String p1, final boolean p2, final long p3);
    
    void unmount(final String p0);
    
    int getID();
    
    void queueEvent(final String p0);
    
    void queueEvent(final String p0, final Object[] p1);
    
    String getAttachmentSide();
}
