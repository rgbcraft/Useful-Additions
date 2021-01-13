package com.rgbcraft.usefuladditions.compat;

import buildcraft.api.tools.IToolWrench;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class BuildcraftCompat {
	
	public static Block getBuildcraftTank() {
		Block tankBlock = null;
		
	    try {
	        tankBlock = (Block) Class.forName("buildcraft.BuildCraftFactory").getField("tankBlock").get(null);
	    } catch (Exception ex) {}
	    
	    return tankBlock;
	}
	
	public static Item getBuildcraftPipe(String name) {
		Item pipe = null;
		
	    try {
	    	pipe = (Item) Class.forName("buildcraft.BuildCraftTransport").getField(name).get(null);
	    } catch (Exception ex) {}
	    
	    return pipe;
	}
	
	public static boolean isHoldingWrench(EntityPlayer entityPlayer) {
        return entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem().getItem() instanceof IToolWrench;
    }
	
}
