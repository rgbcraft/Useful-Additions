package com.rgbcraft.indeng.items;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import tarun1998.thirstmod.common.api.ThirstAPI;

public class ItemCanister extends ItemFood {

	private boolean allowDrink;
	private String toolTip;
	
	public ItemCanister(int id, int iconIndex, String toolTip, boolean allowDrink) {
		super(id, 0, true);

		this.allowDrink = allowDrink;
		this.toolTip = toolTip;
		
		setTextureFile("/com/rgbcraft/indeng/assets/textures/liquids.png");
		setIconIndex(16 + iconIndex);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List toolTip, boolean parBool) {
		toolTip.add("§o" + this.toolTip);
	}
	
	@Override
	public ItemStack onFoodEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
		itemStack.stackSize--;
		entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.get("emptyCanister")));
		return itemStack;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
		if (this.allowDrink) {
			entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
		}
        return itemStack;
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack itemStack) {
        return EnumAction.drink;
    }

}
