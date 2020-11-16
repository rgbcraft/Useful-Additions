// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.core;

public interface IPaintableTile
{
    void setSideTexture(final String p0, final int p1, final int p2);
    
    SidedTexture getSideTexture(final int p0);
    
    void removeFrame();
    
    boolean isPaintable();
    
    boolean applyFrame();
}
