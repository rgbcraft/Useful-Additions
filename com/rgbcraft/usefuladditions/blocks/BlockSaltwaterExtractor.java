package com.rgbcraft.usefuladditions.blocks;

import com.rgbcraft.usefuladditions.tiles.TileSaltwaterExtractor;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSaltwaterExtractor extends BlockMachineBase {

    protected BlockSaltwaterExtractor(int id) {
        super(id, "saltwaterExtractor", Material.iron);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        if (super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ))
            return false;
        return true;
    }

    @Override
    public int getBlockTextureFromSide(int side) {
        return 3;
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TileSaltwaterExtractor();
    }

}
