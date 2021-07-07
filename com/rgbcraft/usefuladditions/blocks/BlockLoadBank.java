package com.rgbcraft.usefuladditions.blocks;

import java.util.Random;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.tiles.TileLoadBank;
import com.rgbcraft.usefuladditions.utils.Utils;

import buildcraft.api.core.Position;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;


public class BlockLoadBank extends BlockMachineBase {

    public BlockLoadBank(int id) {
        super(id, "loadBank");

        this.setRequiresSelfNotify();
        this.setTickRandomly(true);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        if (super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ))
            entityPlayer.openGui(UsefulAdditions.instance, 4, world, x, y, z);
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int metadata) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te != null && te instanceof TileLoadBank) {
            TileLoadBank loadBank = (TileLoadBank) te;
            loadBank.unloadTile();
        }

        super.breakBlock(world, x, y, z, id, metadata);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
        byte[] data = Utils.unmergeBits((byte) metadata);

        if ((data[1] == 0 && side == 3) || (data[0] == 0 && data[1] == side))
            return 6;

        if (data[0] == 1 && data[1] == side)
            return 7;

        if (side == 0)
            return 1;

        if (side == 1)
            return 5;

        return 0;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving) {
        super.onBlockPlacedBy(world, x, y, z, entityLiving);
        ForgeDirection orientation = Utils.get2dOrientation(new Position(entityLiving.posX, entityLiving.posY, entityLiving.posZ), new Position(x, y, z));
        world.setBlockMetadataWithNotify(x, y, z, Utils.mergeBits((byte) 0, (byte) orientation.getOpposite().ordinal()));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (tile instanceof TileLoadBank && !Utils.isRedstonePowered(world, x, y, z)) {
            TileLoadBank loadBank = (TileLoadBank) tile;
            if (random.nextInt(40) < 20 && loadBank.maxEnergy >= 1000) {
                float particleY = y + 1.1F;
                float fireMultiplier = 0.3F + random.nextFloat() * (0.7F - 0.3F);
                float smokeMultiplier = 0.3F + random.nextFloat() * (0.7F - 0.3F);

                if (loadBank.maxEnergy >= 1000) {
                    for (int i = 0; i < 10; i++)
                        world.spawnParticle("smoke", x + smokeMultiplier, particleY, z + smokeMultiplier, 0.0D, 0.0D, 0.0D);

                    if (loadBank.maxEnergy >= 10000)
                        world.spawnParticle("flame", x + fireMultiplier, particleY, z + fireMultiplier, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileLoadBank();
    }

}
