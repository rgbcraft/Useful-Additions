// 
// Decompiled by Procyon v0.5.36
// 

package dan200.computer.api;

import net.minecraft.nbt.NBTTagCompound;

public interface IHostedPeripheral extends IPeripheral
{
    void update();
    
    void readFromNBT(final NBTTagCompound p0);
    
    void writeToNBT(final NBTTagCompound p0);
}
