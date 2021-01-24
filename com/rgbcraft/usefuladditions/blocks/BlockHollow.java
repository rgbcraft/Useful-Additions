package com.rgbcraft.usefuladditions.blocks;

import com.rgbcraft.usefuladditions.tiles.TileHollowMultiBlock;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockHollow extends BlockMultiBlock {

    public BlockHollow(int id) {
        super(id, Material.rock);

        this.setBlockName("multiblock");
    }

    // @Override
    // public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
    // return Utils.getBlockTextureFromSideAndMetadata(side, metadata);
    // }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileHollowMultiBlock();
    }


}
