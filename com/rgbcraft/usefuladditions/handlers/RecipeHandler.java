package com.rgbcraft.usefuladditions.handlers;

import com.rgbcraft.usefuladditions.blocks.Blocks;
import com.rgbcraft.usefuladditions.compat.BuildcraftCompat;
import com.rgbcraft.usefuladditions.compat.GregTechCompat;
import com.rgbcraft.usefuladditions.items.Items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class RecipeHandler {

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
				'T', BuildcraftCompat.getBuildcraftTank(),
				'P', BuildcraftCompat.getBuildcraftPipe("pipeLiquidsIron"),
				'M', new ItemStack(Blocks.get("chassis"), 1, 0)
		});
		
		// Osmosis Generator
		GameRegistry.addRecipe(new ItemStack(Blocks.get("osmosisGenerator")),
			new Object[] {
				" M ",
				"TBT",
				"CWC",
				'M', Items.get("membraneHousing"),
				'T', BuildcraftCompat.getBuildcraftTank(),
				'B', new ItemStack(Blocks.get("chassis"), 1, 0),
				'C', Items.get("emptyCanister"),
				'W', ic2.api.Items.getItem("waterMill")
		});
		
		// Smart Safe
		GameRegistry.addRecipe(new ItemStack(Blocks.get("smartSafe")),
			new Object[] {
				" L ",
				"AMA",
				"CKC",
				'L', Items.get("lcdScreen"),
				'A', Items.get("advancedASIC"),
				'M', new ItemStack(Blocks.get("chassis"), 1, 1),
				'C', Block.chest,
				'K', Items.get("keypad")
		});
		
		// Fluid Counter
		GameRegistry.addRecipe(new ItemStack(Blocks.get("fluidCounter")),
			new Object[] {
				" T ",
				"PMP",
				" B ",
				'T', BuildcraftCompat.getBuildcraftTank(),
				'P', BuildcraftCompat.getBuildcraftPipe("pipeLiquidsStone"),
				'M', new ItemStack(Blocks.get("chassis"), 1, 1),
				'B', Items.get("basicASIC")
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
				'L', Items.get("lcdScreen"),
				'A', Items.get("advancedASIC"),
				'P', Items.get("advancedPlating"),
				'B', Items.get("basicASIC")
		});
		
		// Empty Canister
		GameRegistry.addRecipe(new ItemStack(Items.get("emptyCanister"), 8), new Object[] { " R ", "R R", " R ", 'R', ic2.api.Items.getItem("refinedIronIngot")});
		
		// LCD Screen
		GameRegistry.addRecipe(new ItemStack(Items.get("lcdScreen")),
			new Object[] {
				"III",
				"RGR",
				"III",
				'I', Item.ingotIron,
				'R', ic2.api.Items.getItem("refinedIronIngot"),
				'G', Item.lightStoneDust
		});
		
		// Keypad
		GameRegistry.addRecipe(new ItemStack(Items.get("keypad")),
			new Object[] {
				"BBB",
				"BBB",
				"BBB",
				'B', Block.stoneButton,
		});
		
		// Basic Plating
		GameRegistry.addRecipe(new ItemStack(Items.get("basicPlating")),
			new Object[] {
				"R R",
				" I ",
				"R R",
				'R', ic2.api.Items.getItem("refinedIronIngot"),
				'I', Item.ingotIron
		});
		
		// Advanced Plating
		GameRegistry.addRecipe(new ItemStack(Items.get("advancedPlating")),
			new Object[] {
				"C C",
				" C ",
				"C C",
				'C', ic2.api.Items.getItem("carbonPlate")
		});
		
		// Basic ASIC
		GregTechCompat.addAssemblerRecipe(new ItemStack(ic2.api.Items.getItem("electronicCircuit").getItem(), 1, 2), null, new ItemStack(Items.get("basicASIC"), 1), 400, 20);
		
		// Advanced ASIC
		GregTechCompat.addAssemblerRecipe(new ItemStack(ic2.api.Items.getItem("advancedCircuit").getItem(), 1, 2), null, new ItemStack(Items.get("advancedASIC"), 1), 400, 20);
		
		// Membrane
		GameRegistry.addRecipe(new ItemStack(Items.get("membrane")),
			new Object[] {
				"RRR",
				"RPR",
				"RRR",
				'R', ic2.api.Items.getItem("rubber"),
				'P', BuildcraftCompat.getBuildcraftPipe("pipeLiquidsIron"),
		});
		
		// Membrane Housing
		GameRegistry.addRecipe(new ItemStack(Items.get("membraneHousing")),
			new Object[] {
				"III",
				"IMI",
				"III",
				'I', Item.ingotIron,
				'M', Items.get("membrane"),
		});
		
		
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
