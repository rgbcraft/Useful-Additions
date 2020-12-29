package com.rgbcraft.indeng.blocks;

import com.rgbcraft.indeng.tiles.TileSaltwaterExtractor;
import com.rgbcraft.indeng.utils.CreativeTab;

import buildcraft.api.core.Position;
import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockSaltwaterExtractor extends BlockContainer {

	protected BlockSaltwaterExtractor(int id) {
		super(id, 0, Material.iron);
		
		setBlockName("saltwaterExtractor");
		setTextureFile(Blocks.blocksTextureFile);
		setCreativeTab(CreativeTab.IndEng);
		setStepSound(Block.soundMetalFootstep);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileSaltwaterExtractor();
	}
	
}
