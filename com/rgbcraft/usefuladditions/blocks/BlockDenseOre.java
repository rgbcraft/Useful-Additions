package com.rgbcraft.usefuladditions.blocks;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockDenseOre extends Block {

	public BlockDenseOre(int id) {
		super(id, Material.rock);
		
		setBlockName("denseOre");
		setTextureFile(Blocks.textureFile);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(4.5F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
		switch(metadata) {
			case 0:
				return 14;
			case 1:
				return 15;
			case 2:
				return 30;
			case 3:
				return 47;
			case 4:
				return 46;
			case 5:
				return 31;
			case 6:
				return 62;
			default:
				return 63;
		}
	}
	
	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		if (metadata != 1 || metadata != 5) {
			return true;
		}
		return false;
	}
	
	@Override
	public int quantityDropped(int metadata, int fortune, Random randomom) {
		if (fortune > 0) {
			if (metadata == 2) {
				return 7 + randomom.nextInt(fortune * 2);
			} else if (metadata == 3) {
				return 7 + randomom.nextInt(fortune);
			}
			return 3 + randomom.nextInt(fortune);
		} else {
			if (metadata == 2) {
				return 7 + randomom.nextInt(6);
			} else if (metadata == 3) {
				return 7 + randomom.nextInt(3);
			}
			return 3;
		}
	}
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		if (metadata == 2) {
			ArrayList<ItemStack> blocksDropped = new ArrayList<ItemStack>();
			blocksDropped.add(new ItemStack(Item.dyePowder, this.quantityDropped(metadata, fortune, world.rand), 4));
			return blocksDropped;
		}
		return super.getBlockDropped(world, x, y, z, metadata, fortune);
	}

	@Override
	public int idDropped(int metadata, Random randomom, int fortune) {
		switch (metadata) {
			case 0:
				return Item.coal.itemID;
			case 1:
				return Block.oreIron.blockID;
			case 3:
				return Item.redstone.itemID;
			case 4:
				return Item.emerald.itemID;
			case 5:
				return Block.oreGold.blockID;
			case 6:
				return Item.diamond.itemID;
			default:
				return 1;
		}
	}
	
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float par6, int fortune) {
        super.dropBlockAsItemWithChance(world, x, y, z, metadata, par6, fortune);

        int xpQuantity = 0;
        switch (metadata) {
        	case 0:
        		xpQuantity = MathHelper.getRandomIntegerInRange(world.rand, 0, 4);
        	case 2:
        		xpQuantity = MathHelper.getRandomIntegerInRange(world.rand, 0, 7);
        	case 3:
        		xpQuantity = 1 + world.rand.nextInt(7);
        	case 4:
        		xpQuantity = MathHelper.getRandomIntegerInRange(world.rand, 0, 9);
        	case 6:
        		xpQuantity = MathHelper.getRandomIntegerInRange(world.rand, 0, 9);
        }

        this.dropXpOnBlockBreak(world, x, y, z, xpQuantity);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if (world.getBlockMetadata(x, y, z) == 3) {
	        double multiplier = 0.0625D;
	        for (int side = 0; side < 6; ++side) {
	            double particleX = (double)((float) x + random.nextFloat());
	            double particleY = (double)((float) y + random.nextFloat());
	            double particleZ = (double)((float) z + random.nextFloat());

	            if (side == 0 && !world.isBlockOpaqueCube(x, y + 1, z)) {
	                particleY = (double)(y + 1) + multiplier;
	            }

	            if (side == 1 && !world.isBlockOpaqueCube(x, y - 1, z)) {
	                particleY = (double)(y + 0) - multiplier;
	            }

	            if (side == 2 && !world.isBlockOpaqueCube(x, y, z + 1)) {
	                particleZ = (double)(z + 1) + multiplier;
	            }

	            if (side == 3 && !world.isBlockOpaqueCube(x, y, z - 1)) {
	                particleZ = (double)(z + 0) - multiplier;
	            }

	            if (side == 4 && !world.isBlockOpaqueCube(x + 1, y, z)) {
	                particleX = (double)(x + 1) + multiplier;
	            }

	            if (side == 5 && !world.isBlockOpaqueCube(x - 1, y, z)) {
	                particleX = (double)(x + 0) - multiplier;
	            }

	            if (particleX < (double) x || particleX > (double)(x + 1) || particleY < 0.0D || particleY > (double)(y + 1) || particleZ < (double) z || particleZ > (double)(z + 1)) {
	                world.spawnParticle("reddust", particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D);
	            }
	        }
		}
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}
}
