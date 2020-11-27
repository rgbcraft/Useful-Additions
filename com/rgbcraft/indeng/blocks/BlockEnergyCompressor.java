package com.rgbcraft.indeng.blocks;

import com.rgbcraft.indeng.tiles.TileEnergyCompressor;
import com.rgbcraft.indeng.utils.CreativeTab;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnergyCompressor extends BlockContainer {

	public BlockEnergyCompressor(int id) {
		super(id, Material.rock);
		
		setBlockName("energyCompressor");
		setCreativeTab(CreativeTab.IndEng);
		setTextureFile(Blocks.blocksTextureFile);
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		switch (side) {
			case 0:
				return 225;
			default:
				return 224;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEnergyCompressor();
	}

}
