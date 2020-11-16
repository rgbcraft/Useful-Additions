// 
// Decompiled by Procyon v0.5.36
// 

package dan200.turtle.api;

import dan200.computer.api.IHostedPeripheral;
import net.minecraft.item.ItemStack;

public interface ITurtleUpgrade
{
    int getUpgradeID();
    
    String getAdjective();
    
    TurtleUpgradeType getType();
    
    ItemStack getCraftingItem();
    
    boolean isSecret();
    
    String getIconTexture(final ITurtleAccess p0, final TurtleSide p1);
    
    int getIconIndex(final ITurtleAccess p0, final TurtleSide p1);
    
    IHostedPeripheral createPeripheral(final ITurtleAccess p0, final TurtleSide p1);
    
    boolean useTool(final ITurtleAccess p0, final TurtleSide p1, final TurtleVerb p2, final int p3);
}
