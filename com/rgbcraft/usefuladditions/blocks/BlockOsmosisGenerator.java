package com.rgbcraft.usefuladditions.blocks;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.tiles.TileOsmosisGenerator;
import com.rgbcraft.usefuladditions.utils.Utils;

import buildcraft.api.core.Position;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;


public class BlockOsmosisGenerator extends BlockMachineBase {

    public BlockOsmosisGenerator(int id) {
        super(id, "osmosisGenerator", Material.iron);

        this.setRequiresSelfNotify();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        if (super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ))
            entityPlayer.openGui(UsefulAdditions.instance, 2, world, x, y, z);
        return true;
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
        byte[] data = Utils.unmergeBits((byte) metadata);

        if (data[1] == 0 && side == 3)
            return 4;

        if (side == 0 || side == 1)
            return 1;

        if (side == data[1])
            return data[0] == (byte) 0 ? 4 : 5;

        return 0;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving) {
        super.onBlockPlacedBy(world, x, y, z, entityLiving);
        ForgeDirection orientation = Utils.get2dOrientation(new Position(entityLiving.posX, entityLiving.posY, entityLiving.posZ), new Position(x, y, z));
        world.setBlockMetadataWithNotify(x, y, z, Utils.mergeBits((byte) 0, (byte) orientation.getOpposite().ordinal()));
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileOsmosisGenerator();
    }

}
