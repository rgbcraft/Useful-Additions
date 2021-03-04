package com.rgbcraft.usefuladditions.blocks;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.tiles.TileFluidCounter;
import com.rgbcraft.usefuladditions.utils.Utils;

import buildcraft.api.core.Position;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;


public class BlockFluidCounter extends BlockMachineBase {

    public BlockFluidCounter(int id) {
        super(id, "fluidCounter");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        if (super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ)) {
            entityPlayer.openGui(UsefulAdditions.instance, 3, world, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
        byte[] data = Utils.unmergeBits((byte) metadata);

        if (data[0] == 0 && side == 3)
            return 240;

        if (data[0] == 0 && side == 1)
            return 32;

        if (side == data[1])
            return 240;

        int[] OPPOSITES = {1, 0, 3, 2, 5, 4, 6};
        if (side == OPPOSITES[data[1]])
            return 241;

        return 32;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving) {
        super.onBlockPlacedBy(world, x, y, z, entityLiving);
        ForgeDirection orientation = Utils.get3dOrientation(new Position(entityLiving.posX, entityLiving.posY, entityLiving.posZ), new Position(x, y, z));
        world.setBlockMetadataWithNotify(x, y, z, Utils.mergeBits((byte) 1, (byte) orientation.getOpposite().ordinal()));
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileFluidCounter();
    }

}
