// 
// Decompiled by Procyon v0.5.36
// 

package dan200.turtle.api;

import dan200.computer.api.IHostedPeripheral;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public interface ITurtleAccess
{
    World getWorld();
    
    Vec3 getPosition();
    
    Vec3 getVisualPosition(final float p0);
    
    int getFacingDir();
    
    int getInventorySize();
    
    int getSelectedSlot();
    
    ItemStack getSlotContents(final int p0);
    
    void setSlotContents(final int p0, final ItemStack p1);
    
    boolean storeItemStack(final ItemStack p0);
    
    boolean dropItemStack(final ItemStack p0, final int p1);
    
    boolean deployWithItemStack(final ItemStack p0, final int p1);
    
    boolean attackWithItemStack(final ItemStack p0, final int p1, final float p2);
    
    int getFuelLevel();
    
    boolean refuelWithItemStack(final ItemStack p0);
    
    boolean consumeFuel(final int p0);
    
    int issueCommand(final ITurtleCommandHandler p0);
    
    ITurtleUpgrade getUpgrade(final TurtleSide p0);
    
    IHostedPeripheral getPeripheral(final TurtleSide p0);
}
