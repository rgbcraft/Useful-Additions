package com.rgbcraft.indeng.blocks;

import com.google.common.primitives.Ints;
import com.rgbcraft.indeng.tiles.TileSaltwaterGenerator;
import com.rgbcraft.indeng.utils.CreativeTab;
import com.rgbcraft.indeng.utils.Utils;

import buildcraft.api.core.Position;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockSaltwaterGenerator extends BlockContainer {

	public BlockSaltwaterGenerator(int id) {
		super(id, Material.iron);
		
		setBlockName("saltwaterGenerator");
		setTextureFile(Blocks.blocksTextureFile);
		setCreativeTab(CreativeTab.IndEng);
		setStepSound(Block.soundMetalFootstep);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		if (metadata / 2 == 0 && side == 3)
			return 2;

		if (metadata / 2 == side) {
			return metadata % 2 == 0 ? 3 : 2;
		}

		return 1;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityliving) {
		super.onBlockPlacedBy(world, x, y, z, entityliving);
		ForgeDirection orientation = Utils.get2dOrientation(new Position(entityliving.posX, entityliving.posY, entityliving.posZ), new Position(x, y, z));
		
		int meta = world.getBlockMetadata(x, y, z);
		int isWorking = meta % 2 == 0 ? 1 : 0;
		
		world.setBlockMetadataWithNotify(x, y, z, orientation.getOpposite().ordinal() * 2 + isWorking);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileSaltwaterGenerator();
	}

}
