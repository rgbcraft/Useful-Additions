package com.rgbcraft.indeng.utils;

import buildcraft.api.core.Position;
import cpw.mods.fml.client.TextureFXManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class Utils {
	
	public static void applyLiquidFx(int redMin, int greenMin, int blueMin, int redMax, int greenMax, int blueMax, int index) {
		TextureLiquidFX liquidFX = new TextureLiquidFX(redMin, greenMin, blueMin, redMax, greenMax, blueMax, index, "/com/rgbcraft/indeng/assets/textures/liquidfx.png");
        liquidFX.tileImage = 3595;
        TextureFXManager.instance().addAnimation(liquidFX);
	}
	
	public static ForgeDirection get2dOrientation(Position pos1, Position pos2) {
        double Dx = pos1.x - pos2.x;
        double Dz = pos1.z - pos2.z;
        double angle = Math.atan2(Dz, Dx) / Math.PI * 180 + 180;

        if (angle < 45 || angle > 315)
            return ForgeDirection.EAST;
        else if (angle < 135)
            return ForgeDirection.SOUTH;
        else if (angle < 225)
            return ForgeDirection.WEST;
        else
            return ForgeDirection.NORTH;
    }
    
    public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
    	// 0: Bottom
    	// 1: Top
    	// 2: Back
    	// 3: Front
    	// 4: Left
    	// 5: Right
    	switch (metadata) {
    		case 1:
    			switch (side) {
    				case 1:
    					return 210;
    				case 2:
    					return 211;
    				case 3:
    					return 216;
    				case 4:
    					return 209;
    			}
    		case 2:
    			switch (side) {
    				case 2:
    					return 209;
    				case 4:
    					return 210;
    				case 5:
    					return 211;
    			}
    		case 3:
    			switch (side) {
    				case 3:
    					return 209;
    				case 4:
    					return 211;
    				case 5:
    					return 210;
    			}
    		case 4:
    			switch (side) {
    				case 2:
    					return 214;
    				case 3:
    					return 211;
    				case 4:
    					return 212;
    				case 5:
    					return 209;
    			}
    		case 5:
    			switch (side) {
    				case 2:
    					return 212;
    				case 4:
    					return 208;
    				case 5:
    					return 214;
    			}
    		case 6:
    			switch (side) {
    				case 3:
    					return 212;
    				case 4:
    					return 214;
    				case 5:
    					return 208;
    			}
    		case 7:
    			switch (side) {
    				case 1:
    					return 215;
    				case 2:
    					return 217;
    				case 3:
    					return 214;
    				case 4:
    					return 215;
    				case 5:
    					return 212;
    			}
    		case 8:
    			switch (side) {
    				case 1:
    					return 212;
    				case 2:
    					return 215;
    				case 4:
    					return 216;
    				case 5:
    					return 217;
    			}
    		case 9:
    			switch (side) {
    				case 1:
    					return 209;
    				case 3:
    					return 215;
    				case 4:
    					return 217;
    				case 5:
    					return 216;
    			}
    		case 10:
    			switch (side) {
    				case 1:
    					return 211;
    				case 2:
    					return 210;
    				case 3:
    					return 217;
    				case 5:
    					return 215;
    			}
    		case 12:
    			switch (side) {
    				case 3:
    					return 210;
    			}
    		case 15:
    			switch (side) {
    				case 2:
    					return 216;
    			}
    		default:
    			return 208;
		}
    }

}
