package com.rgbcraft.usefuladditions.items;

import java.util.List;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.utils.CustomDamage;
import com.rgbcraft.usefuladditions.utils.LanguageManager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemCanister extends ItemFood {

	private boolean allowDrink;
	private String toolTip;
	
	public ItemCanister(int id, int iconIndex, String toolTip, boolean allowDrink) {
		super(id, 0, false);

		this.allowDrink = allowDrink;
		this.toolTip = toolTip;
		
		setTextureFile("/com/rgbcraft/usefuladditions/assets/textures/liquids.png");
		setIconIndex(iconIndex);
		setCreativeTab(UsefulAdditions.creativeTab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List toolTip, boolean parBool) {
		if (this.toolTip.length() > 0) {
			toolTip.add("\247o" + this.toolTip);
		}
	}
	
	@Override
	public ItemStack onFoodEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
		if (entityPlayer.capabilities.isCreativeMode)
			itemStack.stackSize--;
		
		ItemStack emptyCanister = new ItemStack(Items.get("emptyCanister"));
		if (itemStack.stackSize > 0)
			entityPlayer.inventory.addItemStackToInventory(emptyCanister);
		else
			entityPlayer.inventory.mainInventory[entityPlayer.inventory.currentItem] = emptyCanister;

		
		if (itemStack.itemID == Items.get("milkCanister").itemID && !world.isRemote) {
			entityPlayer.curePotionEffects(new ItemStack(Item.bucketMilk));
		
		} else if (itemStack.itemID == Items.get("lavaCanister").itemID && !world.isRemote) {
			entityPlayer.setFire(10);
			entityPlayer.attackEntityFrom(new CustomDamage(LanguageManager.getFormattedTranslation("misc.lavaCanister.death", entityPlayer.username)), 999999999);
		}
//		} else if (itemStack.itemID == Items.get("coCanister").itemID) {
//			entityPlayer.attackEntityFrom(new CustomDamage(LanguageManager.getFormattedTranslation("misc.coCanister.death", entityPlayer.username)), 999999999);
//		}
	
		
		if (itemStack.stackSize > 0)
			return itemStack;
		else
			return new ItemStack(0, 0, 0);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
		if (this.allowDrink) {
			entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
		}
		
		if (this.itemID == Items.get("emptyCanister").itemID) {
	        MovingObjectPosition lookingBlock = this.getMovingObjectPositionFromPlayer(world, entityPlayer, true);
			if (lookingBlock != null) {	        
		        if (lookingBlock.typeOfHit == EnumMovingObjectType.TILE) {
		        	int x = lookingBlock.blockX;
		        	int y = lookingBlock.blockY;
		        	int z = lookingBlock.blockZ;
		        	
					int blockID = world.getBlockId(x, y, z);
					boolean valid = false;
					if (blockID == Block.waterStill.blockID) {
						if (!entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.get("waterCanister")))) {
							return itemStack;
						}
						valid = true;
					} else if (blockID == Block.lavaStill.blockID) {
						if (!entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.get("lavaCanister")))) {
							return itemStack;
						}
						valid = true;
					}
					
					if (valid) {
						if (!entityPlayer.capabilities.isCreativeMode) {
							itemStack.stackSize--;
						}
						
						world.setBlockWithNotify(x, y, z, 0);
					}
		        }
	        }
		}

		if (itemStack.stackSize > 0)
			return itemStack;
		else
			return new ItemStack(0, 0, 0);
    }
	
//	@Override
//	public boolean itemInteractionForEntity(ItemStack itemStack, EntityLiving entityLiving) {
//		if (entityLiving instanceof EntityCow) {
//			EntityPlayer entityPlayer = entityLiving.worldObj.getClosestPlayerToEntity(entityLiving, 5);
//			
//			if (entityPlayer != null) {
//				if (!entityPlayer.capabilities.isCreativeMode) {
//					if (itemStack.stackSize > 0)
//						itemStack.stackSize--;
//					else
//						itemStack = new ItemStack(0, 0, 0);
//				}
//
//				entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.get("milkCanister"), 1));
//			}
//
//			return true;
//		}
//		return false;
//	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack) {
        return EnumAction.drink;
    }

}
