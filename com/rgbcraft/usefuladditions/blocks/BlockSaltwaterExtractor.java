package com.rgbcraft.usefuladditions.blocks;

import com.rgbcraft.usefuladditions.tiles.TileSaltwaterExtractor;
import com.rgbcraft.usefuladditions.utils.CreativeTab;

import buildcraft.api.core.Position;
import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockSaltwaterExtractor extends BlockBase {

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
