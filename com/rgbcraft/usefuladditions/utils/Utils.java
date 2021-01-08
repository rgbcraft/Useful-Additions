package com.rgbcraft.usefuladditions.utils;

import java.text.NumberFormat;
import java.util.Locale;

import buildcraft.api.core.Position;
import buildcraft.api.tools.IToolWrench;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class Utils {
	
	public static enum ResourceType {
		TEXTURE, SOUND, GUI, MODEL;
	}
	
	public static String getResource(ResourceType resourceType, String name) {
		switch (resourceType) {
			case GUI:
				return "/com/rgbcraft/usefuladditions/assets/guis/" + name;
			case MODEL:
				return "/com/rgbcraft/usefuladditions/assets/textures/models/" + name;
			case TEXTURE:
				return "/com/rgbcraft/usefuladditions/assets/textures/" + name;
			default:
				return null;
		}
	}
	
	public static boolean isOperator(EntityPlayer entityPlayer) {
		MinecraftServer server = MinecraftServer.getServer();
		return entityPlayer.username.equalsIgnoreCase(server.getServerOwner()) || server.getConfigurationManager().areCommandsAllowed(entityPlayer.username);
	}
	
	public static String formatNumber(int number) {
		return NumberFormat.getNumberInstance(Locale.US).format(number);
	}
	
	public static NBTTagCompound getOrCreateNbtData(ItemStack itemStack) {
        NBTTagCompound tag = itemStack.getTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound("tag");
            itemStack.setTagCompound(tag);
        }
        return tag;
    }
	
	public static ForgeDirection get3dOrientation(Position pos1, Position pos2) {
		double Dx = pos1.x - pos2.x;
		double Dy = pos1.y - pos2.y;
		double angle = Math.atan2(Dy, Dx) / Math.PI * 180 + 180;

		if (angle > 45 && angle < 135)
			return ForgeDirection.UP;
		else if (angle > 225 && angle < 315)
			return ForgeDirection.DOWN;
		else
			return get2dOrientation(pos1, pos2);
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
	
	/**
     * Merge two values into a single one
     *
     * @param a first value, between 0 and 1
     * @param b second value, between 0 and 7
     * @return short int containing the two values
     */
    public static short mergeBits(byte a, byte b) {
        if(a < 0 || b < 0 || a > 1 || b > 7) {
            return 0;
        }

        short res = a;
        res = (byte) (res << 3);
        res += b;

        return res;
    }

    /**
     * Unmerge a single short int into two values
     *
     * @param a value between 0 and 15
     * @return two bytes as array
     */
    public static byte[] unmergeBits(short a) {

        byte r1 = (byte) ((a >>> 3) & 7);
        byte r2 = (byte) (a & 7);

        return new byte[]{r1, r2};
    }

	public static boolean isRedstonePowered(World world, int x, int y, int z) {
		return world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockGettingPowered(x, y, z);
	}
	
	public static boolean isHoldingWrench(EntityPlayer entityPlayer) {
        return entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem().getItem() instanceof IToolWrench;
    }
	
	public static void drawCenteredString(FontRenderer fontRenderer, String text, int x, int y, int color) {
        fontRenderer.drawString(text, x - fontRenderer.getStringWidth(text) / 2, y, color);
    }
    
//    public static int getBlockTextureFromSideAndMetadata(int side, int metadata) {
//    	// 0: Bottom
//    	// 1: Top
//    	// 2: Back
//    	// 3: Front
//    	// 4: Left
//    	// 5: Right
//    	switch (metadata) {
//    		case 1:
//    			switch (side) {
//    				case 1:
//    					return 210;
//    				case 2:
//    					return 211;
//    				case 3:
//    					return 216;
//    				case 4:
//    					return 209;
//    			}
//    		case 2:
//    			switch (side) {
//    				case 2:
//    					return 209;
//    				case 4:
//    					return 210;
//    				case 5:
//    					return 211;
//    			}
//    		case 3:
//    			switch (side) {
//    				case 3:
//    					return 209;
//    				case 4:
//    					return 211;
//    				case 5:
//    					return 210;
//    			}
//    		case 4:
//    			switch (side) {
//    				case 2:
//    					return 214;
//    				case 3:
//    					return 211;
//    				case 4:
//    					return 212;
//    				case 5:
//    					return 209;
//    			}
//    		case 5:
//    			switch (side) {
//    				case 2:
//    					return 212;
//    				case 4:
//    					return 208;
//    				case 5:
//    					return 214;
//    			}
//    		case 6:
//    			switch (side) {
//    				case 3:
//    					return 212;
//    				case 4:
//    					return 214;
//    				case 5:
//    					return 208;
//    			}
//    		case 7:
//    			switch (side) {
//    				case 1:
//    					return 215;
//    				case 2:
//    					return 217;
//    				case 3:
//    					return 214;
//    				case 4:
//    					return 215;
//    				case 5:
//    					return 212;
//    			}
//    		case 8:
//    			switch (side) {
//    				case 1:
//    					return 212;
//    				case 2:
//    					return 215;
//    				case 4:
//    					return 216;
//    				case 5:
//    					return 217;
//    			}
//    		case 9:
//    			switch (side) {
//    				case 1:
//    					return 209;
//    				case 3:
//    					return 215;
//    				case 4:
//    					return 217;
//    				case 5:
//    					return 216;
//    			}
//    		case 10:
//    			switch (side) {
//    				case 1:
//    					return 211;
//    				case 2:
//    					return 210;
//    				case 3:
//    					return 217;
//    				case 5:
//    					return 215;
//    			}
//    		case 12:
//    			switch (side) {
//    				case 3:
//    					return 210;
//    			}
//    		case 15:
//    			switch (side) {
//    				case 2:
//    					return 216;
//    			}
//    		default:
//    			return 208;
//		}
//    }

}
