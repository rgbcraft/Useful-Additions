package com.rgbcraft.usefuladditions.utils;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;

import buildcraft.api.core.Position;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidTank;


public class Utils {

    public enum ResourceType {
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
        if (!entityPlayer.worldObj.isRemote) {
            MinecraftServer server = MinecraftServer.getServer();
            return entityPlayer.username.equalsIgnoreCase(server.getServerOwner()) || server.getConfigurationManager().areCommandsAllowed(entityPlayer.username);
        }
        return false;
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

    // From BuildCraft
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

    // From BuildCraft
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

    public static int outputLiquidOnSide(LiquidTank tank, World world, Position position) {
        TileEntity tileEntity = world.getBlockTileEntity((int) position.x, (int) position.y, (int) position.z);
        if (tileEntity != null && tileEntity instanceof ITankContainer && tank.getLiquid().amount > 0) {
            int amount = ((ITankContainer) tileEntity).fill(position.orientation, tank.getLiquid(), true);
            tank.drain(amount, true);
            return amount;
        }
        return 0;
    }

    public static boolean isRedstonePowered(World world, int x, int y, int z) {
        return world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockGettingPowered(x, y, z);
    }

    public static void drawCenteredString(FontRenderer fontRenderer, String text, int x, int y, int color) {
        fontRenderer.drawString(text, x - fontRenderer.getStringWidth(text) / 2, y, color);
    }

    public static void bindTexture(Minecraft mc, String textureFile) {
        mc.renderEngine.bindTexture(mc.renderEngine.getTexture(textureFile));
    }

    // From Applied Energistics
    public static boolean sameStackStags(ItemStack a, ItemStack b) {
        if (a == null && b == null)
            return true;

        if (a == null || b == null)
            return false;

        if (a == b)
            return true;

        NBTTagCompound ta = a.getTagCompound();
        NBTTagCompound tb = b.getTagCompound();

        if (ta == tb)
            return true;

        if (ta == null && tb == null || ta != null && ta.hasNoTags() && tb == null || tb != null && tb.hasNoTags() && ta == null || ta != null && ta.hasNoTags() && tb != null && tb.hasNoTags())
            return true;

        if (ta == null && tb != null || ta != null && tb == null)
            return false;

        return NBTEqualityTest(ta, tb);
    }

    // From Applied Energistics
    public static boolean NBTEqualityTest(NBTBase A, NBTBase B) {
        byte id = A.getId();
        if (id != B.getId())
            return false;

        switch (id) {
            case 10: {
                NBTTagCompound ctA = (NBTTagCompound) A;
                NBTTagCompound ctB = (NBTTagCompound) B;
                Collection<NBTTagList> cA = ctA.getTags();
                Collection<NBTTagList> cB = ctB.getTags();

                if (cA.size() != cB.size())
                    return false;

                for (final NBTBase tag : cA) {
                    final NBTBase aTag = ctB.getTag(tag.getName());
                    if (aTag == null || !NBTEqualityTest(tag, aTag))
                        return false;
                }

                return true;
            }

            case 9: {
                final NBTTagList lA = (NBTTagList) A;
                final NBTTagList lB = (NBTTagList) B;
                if (lA.tagCount() != lB.tagCount())
                    return false;
                for (int x = 0; x < lA.tagCount(); ++x) {
                    final NBTBase tag2 = lA.tagAt(x);
                    final NBTBase aTag2 = lB.tagAt(x);
                    if (aTag2 == null || !NBTEqualityTest(tag2, aTag2))
                        return false;
                }
                return true;
            }

            case 1:
                return ((NBTTagByte) A).data == ((NBTTagByte) B).data;
            case 4:
                return ((NBTTagLong) A).data == ((NBTTagLong) B).data;
            case 8:
                return ((NBTTagString) A).data == ((NBTTagString) B).data || A.equals(B);
            case 6:
                return ((NBTTagDouble) A).data == ((NBTTagDouble) B).data;
            case 5:
                return ((NBTTagFloat) A).data == ((NBTTagFloat) B).data;
            case 3:
                return ((NBTTagInt) A).data == ((NBTTagInt) B).data;
            default:
                return A.equals(B);
        }
    }

    // From Applied Energistics
    public static boolean isSameItem(ItemStack a, ItemStack b) {
        if (a == null && b == null)
            return true;

        if (a == null || b == null)
            return false;

        if (a.getItemDamage() == -1 || b.getItemDamage() == -1)
            return a.itemID == b.itemID && sameStackStags(a, b);

        return a.isItemEqual(b) && sameStackStags(a, b);
    }

    /**
     * Merge two values into a single one
     *
     * @param a first value, between 0 and 1
     * @param b second value, between 0 and 7
     * @return short int containing the two values
     */
    public static short mergeBits(byte a, byte b) {
        if (a < 0 || b < 0 || a > 1 || b > 7)
            return 0;

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

        byte r1 = (byte) (a >>> 3 & 7);
        byte r2 = (byte) (a & 7);

        return new byte[] {r1, r2};
    }

}
