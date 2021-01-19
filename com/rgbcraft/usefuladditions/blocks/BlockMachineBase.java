package com.rgbcraft.usefuladditions.blocks;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.api.IDebuggable;
import com.rgbcraft.usefuladditions.api.Items;
import com.rgbcraft.usefuladditions.compat.BuildCraftCompat;
import com.rgbcraft.usefuladditions.items.ItemDebugger;
import com.rgbcraft.usefuladditions.utils.ICardInfoProvider;
import com.rgbcraft.usefuladditions.utils.IRoteableTile;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import buildcraft.api.core.Position;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import thermalexpansion.api.core.IDismantleable;

public class BlockMachineBase extends BlockContainer implements IDismantleable {

	protected BlockMachineBase(int id, String blockName, Material material) {
		super(id, material);
		
		setBlockName(blockName);
		setCreativeTab(UsefulAdditions.creativeTab);
		setStepSound(Block.soundMetalFootstep);
		setHardness(15.0F);
		setTextureFile(Utils.getResource(ResourceType.TEXTURE, "blocks.png"));
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
		super.onBlockActivated(world, x, y, z, entityPlayer, side, hitX, hitY, hitZ);
		
		int metadata = world.getBlockMetadata(x, y, z);
		TileEntity te = world.getBlockTileEntity(x, y, z);
		ItemStack heldItem = entityPlayer.getCurrentEquippedItem();

		if (heldItem != null && (heldItem.getItem() == Items.get("debugger") && (ItemDebugger.isAdvancedModeActived(heldItem) || te instanceof IDebuggable)) 
				|| (te instanceof ICardInfoProvider && (heldItem != null && heldItem.getItem() == Items.get("IESensorKit"))))
			return false;
		
		if (BuildCraftCompat.isHoldingWrench(entityPlayer)) {
			if (entityPlayer.isSneaking() && this.canDismantle(entityPlayer, world, x, y, z)) {
				this.dismantleBlock(entityPlayer, world, x, y, z, false);
			} else {
				if (te instanceof IRoteableTile) {
					int newMetadata = ((IRoteableTile) te).getRotation(world, x, y, z, entityPlayer, side);
					if (newMetadata != metadata) {
						world.setBlockMetadataWithNotify(x, y, z, newMetadata);
					}
					return false;
				}
			}
		}

		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int metadata) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if (te != null && te instanceof IInventory) {
			IInventory inventory = (IInventory) te;
			
			for (int i = 0; i < inventory.getSizeInventory(); i++) {
				ItemStack stack = inventory.getStackInSlotOnClosing(i);
				
				if (stack != null) {
					float spawnX = x + world.rand.nextFloat();
					float spawnY = y + world.rand.nextFloat();
					float spawnZ = z + world.rand.nextFloat();
					
					EntityItem droppedItem = new EntityItem(world, spawnX, spawnY, spawnZ, stack);
					
					float mult = 0.05F;
					
					droppedItem.motionX = (-0.5F + world.rand.nextFloat()) * mult;
					droppedItem.motionY = (3 + world.rand.nextFloat()) * mult;
					droppedItem.motionZ = (-0.5F + world.rand.nextFloat()) * mult;

					world.spawnEntityInWorld(droppedItem);
				}
			}
		}

		super.breakBlock(world, x, y, z, id, metadata);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return null;
	}

	@Override
	public ItemStack dismantleBlock(EntityPlayer entityPlayer, World world, int x, int y, int z, boolean returnBlock) {
		int metadata = world.getBlockMetadata(x, y, z);
		
        ItemStack dropBlock = new ItemStack(this.blockID, 1, metadata);
        world.setBlockWithNotify(x, y, z, 0);
        if (dropBlock != null && !returnBlock) {
            float f = 0.3f;
            double x2 = world.rand.nextFloat() * f + (1.0f - f) * 0.5;
            double y2 = world.rand.nextFloat() * f + (1.0f - f) * 0.5;
            double z2 = world.rand.nextFloat() * f + (1.0f - f) * 0.5;
            world.spawnEntityInWorld(new EntityItem(world, x + x2, y + y2, z + z2, dropBlock));
            super.breakBlock(world, x, y, z, this.blockID, metadata);
        }

        return dropBlock;
	}

	@Override
	public boolean canDismantle(EntityPlayer entityPlayer, World world, int x, int y, int z) {
		return true;
	}

}
