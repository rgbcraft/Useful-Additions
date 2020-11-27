package com.rgbcraft.indeng.blocks;

import com.rgbcraft.indeng.tiles.TileAdvancedLiquidContainer;
import com.rgbcraft.indeng.utils.CreativeTab;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAdvancedLiquidContainer extends BlockContainer {

	public BlockAdvancedLiquidContainer(int id) {
		super(id, 208, Material.iron);
		
		setBlockName("advancedLiquidContainer");
		setTextureFile(Blocks.blocksTextureFile);
		setCreativeTab(CreativeTab.IndEng);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileAdvancedLiquidContainer();
	}

}
