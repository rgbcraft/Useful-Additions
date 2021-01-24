package com.rgbcraft.usefuladditions.items;

import com.rgbcraft.usefuladditions.utils.ICardInfoProvider;
import com.rgbcraft.usefuladditions.utils.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class ItemUASensorKit extends ItemBase {

    public ItemUASensorKit(int id) {
        super(id);

        setItemName("UASensorKit");
        setMaxStackSize(1);
        setIconIndex(78);
    }

    private ChunkCoordinates getTargetCoordinates(World world, int x, int y, int z, ItemStack stack) {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity != null && tileEntity instanceof ICardInfoProvider) {
            ChunkCoordinates coordinates = new ChunkCoordinates();
            coordinates.posX = x;
            coordinates.posY = y;
            coordinates.posZ = z;
            return coordinates;
        }
        return null;
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            ChunkCoordinates position = this.getTargetCoordinates(world, x, y, z, itemStack);

            if (position != null) {
                ItemStack card = new ItemStack(Items.get("UASensorCard"), 1);

                NBTTagCompound tag = Utils.getOrCreateNbtData(card);
                tag.setInteger("x", x);
                tag.setInteger("y", y);
                tag.setInteger("z", z);

                entityPlayer.inventory.mainInventory[entityPlayer.inventory.currentItem] = card;
                return true;
            }
        }
        return false;
    }

}
