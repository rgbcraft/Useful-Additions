package com.rgbcraft.indeng.blocks;

import com.rgbcraft.indeng.tiles.TileSaltwaterExtractor;
import com.rgbcraft.indeng.utils.CreativeTab;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSaltwaterExtractor extends BlockContainer {

	protected BlockSaltwaterExtractor(int id) {
		super(id, Material.iron);
		
		setBlockName("saltwaterExtractor");
		setTextureFile(Blocks.blocksTextureFile);
		setCreativeTab(CreativeTab.IndEng);
	}
	
	@Override
	public int getBlockTextureFromSide(int side) {
		switch(side) {
			case 5:
				return 1;
			case 4:
				return 1;
			case 3:
				return 1;
			case 2:
				return 1;
			default:
				return 0;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileSaltwaterExtractor();
	}
	
}
