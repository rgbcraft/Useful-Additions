package com.rgbcraft.indeng.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rgbcraft.indeng.items.ItemCanister;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;

public class Liquids {
	private static HashMap<String, LiquidStack> liquids = new HashMap<String, LiquidStack>();
	
	public static void addLiquid(String name, int liquidStackId, int liquidFxIndex, Item liquidContainer, Item liquidEmptyContainer) {
        LiquidStack liquidStack = new LiquidStack(new Item(liquidStackId).setTextureFile("/com/rgbcraft/indeng/assets/textures/liquidfx.png").setIconIndex(liquidFxIndex).setItemName(name), LiquidContainerRegistry.BUCKET_VOLUME);
        liquidStack = LiquidDictionary.getOrCreateLiquid(name, liquidStack);
        LiquidContainerRegistry.registerLiquid(new LiquidContainerData(liquidStack, new ItemStack(liquidContainer), new ItemStack(liquidEmptyContainer)));
        liquids.put(name, liquidStack);
	}
	
	 public static LiquidStack get(String liquidName) {
        return liquids.get(liquidName);
    }
}
