package com.rgbcraft.usefuladditions.blocks;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.utils.CreativeTab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockChassis extends Block {

	public BlockChassis(int id) {
		super(id, Material.iron);
		
		setBlockName("chassis");
		setTextureFile(Blocks.blocksTextureFile);
		setCreativeTab(UsefulAdditions.creativeTab);
		setStepSound(Block.soundMetalFootstep);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		switch(metadata) {
			case 1:
				return 255;
			default:
				return 1;
		}
	}
	
	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		int metadata = world.getBlockMetadata(x, y, z);
		if (metadata == 1)
			return 3.0F;
		else
			return 2.0F;
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}

}
