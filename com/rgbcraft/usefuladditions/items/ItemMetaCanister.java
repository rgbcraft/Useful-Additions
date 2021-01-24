package com.rgbcraft.usefuladditions.items;

import java.util.List;

import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;


public class ItemMetaCanister extends ItemMeta {

    public static ItemMeta instance;

    public ItemMetaCanister(int id) {
        super(id);

        ItemMetaCanister.instance = this;

        this.setTextureFile(Utils.getResource(ResourceType.TEXTURE, "liquids.png"));
        this.setItemName("canister");
    }

    public static ItemStack[] getStackList() {
        return ItemMetaCanister.instance.items;
    }

    public static void addSubItem(int metadata, String itemName, int iconIndex, String toolTip) {
        ItemStack canister = new ItemStack(ItemMetaCanister.instance.itemID, 1, metadata);
        ItemMetaCanister.instance.items[metadata] = canister;
        ItemMetaCanister.instance.itemNames[metadata] = itemName;

        ItemMetaCanister.instance.icons[metadata] = iconIndex;
        ItemMetaCanister.instance.toolTips[metadata] = toolTip;

        if (metadata != 0)
            ic2.api.Ic2Recipes.addExtractorRecipe(canister, new ItemStack(ItemMetaCanister.instance.itemID, 1, 0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List toolTip, boolean showExtraInfos) {
        String itemToolTip = this.toolTips[itemStack.getItemDamage()];
        if (itemToolTip != null && itemStack.getItemDamage() >= 0 && itemStack.getItemDamage() < 256)
            toolTip.add("\247o" + itemToolTip);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (this.isSubItem(itemStack, "empty")) {
            MovingObjectPosition lookingBlock = this.getMovingObjectPositionFromPlayer(world, entityPlayer, true);
            if (lookingBlock != null && lookingBlock.typeOfHit == EnumMovingObjectType.TILE) {
                int x = lookingBlock.blockX;
                int y = lookingBlock.blockY;
                int z = lookingBlock.blockZ;

                int blockID = world.getBlockId(x, y, z);
                boolean valid = false;
                if (blockID == Block.waterStill.blockID) {
                    if (!entityPlayer.inventory.addItemStackToInventory(this.getSubItem(1, 1)))
                        return itemStack;
                    valid = true;
                } else if (blockID == Block.lavaStill.blockID) {
                    if (!entityPlayer.inventory.addItemStackToInventory(this.getSubItem(2, 1)))
                        return itemStack;
                    valid = true;
                }

                if (valid) {
                    if (!entityPlayer.capabilities.isCreativeMode)
                        itemStack.stackSize--;

                    world.setBlockWithNotify(x, y, z, 0);
                }
            }
        }

        if (itemStack.stackSize > 0)
            return itemStack;
        else
            return new ItemStack(0, 0, 0);
    }

}
