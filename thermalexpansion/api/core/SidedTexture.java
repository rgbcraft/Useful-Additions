// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.core;

public class SidedTexture
{
    public String myTexture;
    public int myIndex;
    
    public SidedTexture(final String texture, final int index) {
        this.myTexture = "";
        this.myIndex = 0;
        this.myTexture = texture;
        this.myIndex = index;
    }
    
    public SidedTexture() {
        this.myTexture = "";
        this.myIndex = 0;
    }
}
