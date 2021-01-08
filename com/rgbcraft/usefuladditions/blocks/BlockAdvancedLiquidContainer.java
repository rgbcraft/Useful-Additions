package com.rgbcraft.usefuladditions.blocks;

import com.rgbcraft.usefuladditions.tiles.TileAdvancedLiquidContainer;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAdvancedLiquidContainer extends BlockBase {

	public BlockAdvancedLiquidContainer(int id) {
		super(id, "advancedLiquidContainer", Material.iron);
		
//		Texture: 208
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileAdvancedLiquidContainer();
	}

}
