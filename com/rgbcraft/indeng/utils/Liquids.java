package com.rgbcraft.indeng.utils;

import java.util.ArrayList;
import java.util.List;

import com.rgbcraft.indeng.items.ItemCell;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;

public class Liquids {
	public static List<LiquidStack> liquids = new ArrayList<LiquidStack>();
	public static List<Item> items = new ArrayList<Item>();
	
	public static void addLiquid(String name, int id, int index, int cellID, int cellIndex, List toolTip) {
		Item itemCell = new ItemCell(cellID, toolTip).setItemName(name.toLowerCase() + "Cell").setCreativeTab(CreativeTab.IndEng).setIconIndex(cellIndex).setTextureFile("/com/rgbcraft/indeng/assets/textures/items.png");
		items.add(itemCell);
		
		GameRegistry.registerItem(itemCell, name.toLowerCase() + "Cell");
		LanguageRegistry.addName(itemCell, name + " Cell");
		Item liquidItem = new Item(id).setIconIndex(index).setTextureFile("/com/rgbcraft/indeng/assets/textures/textures.png").setItemName("liquid" + name);
		
		ItemStack liquidItemStack = new ItemStack(liquidItem, 1);
		
		LiquidStack liquidStack = new LiquidStack(liquidItem, LiquidContainerRegistry.BUCKET_VOLUME);
		liquidStack = LiquidDictionary.getOrCreateLiquid(name, liquidStack);
		LanguageRegistry.addName(liquidItem, name);
		liquids.add(liquidStack);

		LiquidContainerData liquidData = new LiquidContainerData(liquidStack,
				new ItemStack(itemCell, 1), ic2.api.Items.getItem("cell"));
        LiquidContainerRegistry.registerLiquid(liquidData);
        System.out.println("Created " + name);
	}
	
	public static void addLiquid(String name, int id, int index, Item itemCell) {
		itemCell.setTextureFile("/com/rgbcraft/indeng/assets/textures/items.png");
		itemCell.setItemName(name.toLowerCase() + "Cell");
		itemCell.setCreativeTab(CreativeTab.IndEng);
		items.add(itemCell);
		GameRegistry.registerItem(itemCell, name.toLowerCase() + "Cell");
		LanguageRegistry.addName(itemCell, name + " Cell");
		Item liquidItem = new Item(id).setIconIndex(index).setTextureFile("/com/rgbcraft/indeng/assets/textures/textures.png").setItemName("liquid" + name);
		ItemStack liquidItemStack = new ItemStack(liquidItem, 1);
		
		LiquidStack liquidStack = new LiquidStack(liquidItem, LiquidContainerRegistry.BUCKET_VOLUME);
		liquidStack = LiquidDictionary.getOrCreateLiquid(name, liquidStack);
		LanguageRegistry.addName(liquidItem, name);
		liquids.add(liquidStack);
		LiquidContainerData liquidData = new LiquidContainerData(liquidStack,
                new ItemStack(itemCell, 1), ic2.api.Items.getItem("cell"));
        LiquidContainerRegistry.registerLiquid(liquidData);
        System.out.println("Created " + name);
	}
}
