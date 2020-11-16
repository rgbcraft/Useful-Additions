// 
// Decompiled by Procyon v0.5.36
// 

package dan200.computer.api;

import net.minecraft.item.ItemStack;

public interface IMedia
{
    String getLabel(final ItemStack p0);
    
    boolean setLabel(final ItemStack p0, final String p1);
    
    String getAudioTitle(final ItemStack p0);
    
    String getAudioRecordName(final ItemStack p0);
    
    String mountData(final ItemStack p0, final IComputerAccess p1);
}
