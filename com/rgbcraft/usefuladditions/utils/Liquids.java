package com.rgbcraft.usefuladditions.utils;

import java.util.HashMap;

import cpw.mods.fml.client.TextureFXManager;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class Liquids {
	public static HashMap<String, LiquidStack> liquids = new HashMap<String, LiquidStack>();
	
	public static void addLiquid(String itemName, String displayName, int liquidStackId, int liquidFxIndex, Item liquidContainer, Item liquidEmptyContainer) {
		Item liquidItem = new Item(liquidStackId).setTextureFile("/com/rgbcraft/usefuladditions/assets/textures/liquidfx.png").setIconIndex(liquidFxIndex).setItemName(itemName);
		LanguageRegistry.addName(liquidItem, displayName);
		
        LiquidStack liquidStack = new LiquidStack(liquidItem, LiquidContainerRegistry.BUCKET_VOLUME);
        liquidStack = LiquidDictionary.getOrCreateLiquid("usefuladditions." + itemName, liquidStack);
        LiquidContainerRegistry.registerLiquid(new LiquidContainerData(liquidStack, new ItemStack(liquidContainer), new ItemStack(liquidEmptyContainer)));
        liquids.put(itemName, liquidStack);
	}
	
	@SideOnly(Side.CLIENT)
	public static void applyLiquidFx(int redMin, int greenMin, int blueMin, int redMax, int greenMax, int blueMax, int index) {
		TextureLiquidFX liquidFX = new TextureLiquidFX(redMin, greenMin, blueMin, redMax, greenMax, blueMax, index, "/com/rgbcraft/usefuladditions/assets/textures/liquidfx.png");
        liquidFX.tileImage = 3595;
        TextureFXManager.instance().addAnimation(liquidFX);
	}
	
	 public static LiquidStack get(String liquidName) {
        return liquids.get(liquidName);
    }
}
