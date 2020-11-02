package com.rgbcraft.indeng.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSaltwaterExtractor extends BlockContainer {

	protected BlockSaltwaterExtractor(int id) {
		super(id, Material.iron);
		
		setBlockName("blockSaltwaterExtractor");
	}
	
	@Override
	public int getBlockTextureFromSide(int side) {
		switch (side) {
			default:
				return 0;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return null;
	}
	
}
