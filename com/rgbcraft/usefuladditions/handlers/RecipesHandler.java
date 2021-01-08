package com.rgbcraft.usefuladditions.handlers;

import com.rgbcraft.usefuladditions.blocks.Blocks;
import com.rgbcraft.usefuladditions.items.Items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class RecipesHandler {

    public static void init() {
    	// Dense Ore Furnace Recipes
		FurnaceRecipes.smelting().addSmelting(Blocks.get("denseOre").blockID, 0, new ItemStack(Block.oreCoal, 3, 0), 4);
		FurnaceRecipes.smelting().addSmelting(Blocks.get("denseOre").blockID, 1, new ItemStack(Block.oreIron, 3, 0), 3);
		FurnaceRecipes.smelting().addSmelting(Blocks.get("denseOre").blockID, 2, new ItemStack(Block.oreLapis, 3, 0), 7);
		FurnaceRecipes.smelting().addSmelting(Blocks.get("denseOre").blockID, 3, new ItemStack(Block.oreRedstone, 3, 0), 5);
		FurnaceRecipes.smelting().addSmelting(Blocks.get("denseOre").blockID, 4, new ItemStack(Block.oreEmerald, 3, 0), 9);
		FurnaceRecipes.smelting().addSmelting(Blocks.get("denseOre").blockID, 5, new ItemStack(Block.oreGold, 3, 0), 8);
		FurnaceRecipes.smelting().addSmelting(Blocks.get("denseOre").blockID, 6, new ItemStack(Block.oreDiamond, 3, 0), 9);
		
		// Crafting Things
		GameRegistry.addRecipe(new ItemStack(Items.get("lcdScreen")), new Object[] { "  X", " / ", "/  ", 'X', new ItemStack(Block.cloth, 1, 9), '/', new ItemStack(Item.stick, 1, 0)});
    }
}
