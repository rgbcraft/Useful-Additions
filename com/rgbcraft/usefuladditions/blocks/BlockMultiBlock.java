package com.rgbcraft.usefuladditions.blocks;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.tiles.TileMultiBlock;
import com.rgbcraft.usefuladditions.utils.CreativeTab;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMultiBlock extends BlockContainer {
    public BlockMultiBlock(int id, Material material) {
        super(id, material);
        this.setCreativeTab(UsefulAdditions.creativeTab);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockID) {
        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (tile != null && tile instanceof TileMultiBlock) {
            TileMultiBlock multiBlock = (TileMultiBlock) tile;
            if (multiBlock.hasMaster()) {
                if (multiBlock.isMaster()) {
                    if (!multiBlock.checkMultiBlockForm())
                        multiBlock.resetStructure();
                } else {
                    if (!multiBlock.checkForMaster()) {
                        TileMultiBlock master = (TileMultiBlock) world.getBlockTileEntity(multiBlock.getMasterX(), multiBlock.getMasterY(), multiBlock.getMasterZ());
                        master.resetStructure();
                    }
                }
            }
        }
        super.onNeighborBlockChange(world, x, y, z, blockID);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }

}
