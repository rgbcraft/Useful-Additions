package com.rgbcraft.usefuladditions.handlers;

import com.rgbcraft.usefuladditions.blocks.Blocks;
import com.rgbcraft.usefuladditions.compat.BuildCraftCompat;
import com.rgbcraft.usefuladditions.compat.BuildCraftCompat.BuildCraftModule;
import com.rgbcraft.usefuladditions.compat.GregTechCompat;
import com.rgbcraft.usefuladditions.items.Items;
import com.rgbcraft.usefuladditions.liquids.Liquids;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import thermalexpansion.api.crafting.CraftingManagers;

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
		
		// Blocks
		
		// Saltwater Extractor
		GameRegistry.addRecipe(new ItemStack(Blocks.get("saltwaterExtractor")),
			new Object[] {
				" T ",
				"PMP",
				" P ",
				'T', BuildCraftCompat.getBlock(BuildCraftModule.Factory, "tankBlock"),
				'P', BuildCraftCompat.getItem(BuildCraftModule.Transport, "pipeLiquidsIron"),
				'M', new ItemStack(Blocks.get("chassis"), 1, 0)
		});
		
		// Osmosis Generator
		GameRegistry.addRecipe(new ItemStack(Blocks.get("osmosisGenerator")),
			new Object[] {
				" M ",
				"TBT",
				"CWC",
				'M', new ItemStack(Items.get("component"), 1, 7),
				'T', BuildCraftCompat.getBlock(BuildCraftModule.Factory, "tankBlock"),
				'B', new ItemStack(Blocks.get("chassis"), 1, 0),
				'C', new ItemStack(Items.get("canister"), 1, 0),
				'W', ic2.api.Items.getItem("waterMill")
		});
		
		// Smart Safe
		GameRegistry.addRecipe(new ItemStack(Blocks.get("smartSafe")),
			new Object[] {
				" L ",
				"AMA",
				"CKC",
				'L', new ItemStack(Items.get("component"), 1, 0),
				'A', new ItemStack(Items.get("component"), 1, 5),
				'M', new ItemStack(Blocks.get("chassis"), 1, 1),
				'C', Block.chest,
				'K', new ItemStack(Items.get("component"), 1, 1)
		});
		
		// Fluid Counter
		GameRegistry.addRecipe(new ItemStack(Blocks.get("fluidCounter")),
			new Object[] {
				" T ",
				"PMP",
				" B ",
				'T', BuildCraftCompat.getBlock(BuildCraftModule.Factory, "tankBlock"),
				'P', BuildCraftCompat.getItem(BuildCraftModule.Transport, "pipeLiquidsStone"),
				'M', new ItemStack(Blocks.get("chassis"), 1, 1),
				'B', new ItemStack(Items.get("component"), 1, 4)
		});

		// Basic Chassis
		GameRegistry.addRecipe(new ItemStack(Blocks.get("chassis"), 1, 0),
			new Object[] {
				"RIR",
				"IRI",
				"RIR",
				'R', ic2.api.Items.getItem("refinedIronIngot"),
				'I', Item.ingotIron
		});
		
		// Advanced Chassis
		GameRegistry.addRecipe(new ItemStack(Blocks.get("chassis"), 1, 1),
			new Object[] {
				"CCC",
				"CCC",
				"CCC",
				'C', ic2.api.Items.getItem("carbonPlate")
		});
		
		// Items
		
		// Debugger
		GameRegistry.addRecipe(new ItemStack(Items.get("debugger"), 1, Items.get("debugger").getMaxDamage()),
			new Object[] {
				" L ",
				"APB",
				"   ",
				'L', new ItemStack(Items.get("component"), 1, 0),
				'A', new ItemStack(Items.get("component"), 1, 5),
				'P', new ItemStack(Items.get("component"), 1, 3),
				'B', new ItemStack(Items.get("component"), 1, 4)
		});
		
		// Empty Canister
		GameRegistry.addRecipe(new ItemStack(Items.get("canister"), 32, 0), new Object[] { " R ", "R R", " R ", 'R', ic2.api.Items.getItem("refinedIronIngot")});
		
		// LCD Screen
		GameRegistry.addRecipe(new ItemStack(Items.get("component"), 1, 0),
			new Object[] {
				"III",
				"RGR",
				"III",
				'I', Item.ingotIron,
				'R', ic2.api.Items.getItem("refinedIronIngot"),
				'G', Item.lightStoneDust
		});
		
		// Keypad
		GameRegistry.addRecipe(new ItemStack(Items.get("component"), 1, 1),
			new Object[] {
				"BBB",
				"BBB",
				"BBB",
				'B', Block.stoneButton,
		});
		
		// Basic Plating
		GameRegistry.addRecipe(new ItemStack(Items.get("component"), 1, 2),
			new Object[] {
				"R R",
				" I ",
				"R R",
				'R', ic2.api.Items.getItem("refinedIronIngot"),
				'I', Item.ingotIron
		});
		
		// Advanced Plating
		GameRegistry.addRecipe(new ItemStack(Items.get("component"), 1, 3),
			new Object[] {
				"C C",
				" C ",
				"C C",
				'C', ic2.api.Items.getItem("carbonPlate")
		});
		
		// Basic ASIC
		GregTechCompat.addAssemblerRecipe(new ItemStack(ic2.api.Items.getItem("electronicCircuit").getItem(), 1, 2), null, new ItemStack(Items.get("component"), 1, 4), 400, 20);
		
		// Advanced ASIC
		GregTechCompat.addAssemblerRecipe(new ItemStack(ic2.api.Items.getItem("advancedCircuit").getItem(), 1, 2), null, new ItemStack(Items.get("component"), 1, 5), 400, 20);
		
		// Membrane Housing
		GameRegistry.addRecipe(new ItemStack(Items.get("component"), 1, 6),
			new Object[] {
				"III",
				"IPI",
				"III",
				'I', Item.ingotIron,
				'P', BuildCraftCompat.getItem(BuildCraftModule.Transport, "pipeLiquidsIron"),
		});
		
		// Membrane
		GameRegistry.addShapelessRecipe(new ItemStack(Items.get("component"), 1, 7), new Object[] { new ItemStack(Items.get("component"), 1, 6), ic2.api.Items.getItem("rubber") });
		
		// Other
		GameRegistry.addRecipe(new ItemStack(Items.get("toiletPaper")),
			new Object[] {
				"PPP",
				"P  ",
				"   ",
				'P', Item.paper
		});
		
		GameRegistry.addRecipe(new ItemStack(Items.get("toiletPaperSandwich")),
				new Object[] {
					" B ",
					" T ",
					" B ",
					'B', Item.bread,
					'T', Items.get("toiletPaper")
			});
    }

}
