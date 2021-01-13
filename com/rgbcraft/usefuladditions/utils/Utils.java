package com.rgbcraft.usefuladditions.utils;

import java.text.NumberFormat;
import java.util.Locale;

import buildcraft.api.core.Position;
import buildcraft.api.tools.IToolWrench;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class Utils {
	
	public static enum ResourceType {
		TEXTURE, GUI, MODEL;
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
            tag = new NBTTagCompound();
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

	public static boolean isRedstonePowered(World world, int x, int y, int z) {
		return world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockGettingPowered(x, y, z);
	}
	
	public static void drawCenteredString(FontRenderer fontRenderer, String text, int x, int y, int color) {
        fontRenderer.drawString(text, x - fontRenderer.getStringWidth(text) / 2, y, color);
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

}
