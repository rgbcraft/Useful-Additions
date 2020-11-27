package com.rgbcraft.indeng.blocks;

import java.util.HashMap;

import com.rgbcraft.indeng.items.ItemBlockAdvancedLiquidContainer;
import com.rgbcraft.indeng.items.ItemBlockDenseOre;
import com.rgbcraft.indeng.items.ItemBlockRarity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class Blocks {

    private static HashMap<String, Block> blocks = new HashMap<String, Block>();
    public static String blocksTextureFile = "/com/rgbcraft/indeng/assets/textures/blocks.png";

    public static void init() {
    	Block saltwaterExtractor = new BlockSaltwaterExtractor(3000);
		blocks.put(saltwaterExtractor.getBlockName(), saltwaterExtractor);
		
		Block smartSafe = new BlockSmartSafe(3001);
		blocks.put(smartSafe.getBlockName(), smartSafe);
		
		Block energyCompressor = new BlockEnergyCompressor(3002);
		blocks.put(energyCompressor.getBlockName(), energyCompressor);
		
		Block denseOre = new BlockDenseOre(3003);
		blocks.put(denseOre.getBlockName(), denseOre);
		
		Block advancedLiquidLoader = new BlockAdvancedLiquidContainer(3005);
		blocks.put(advancedLiquidLoader.getBlockName(), advancedLiquidLoader);

		GameRegistry.registerBlock(denseOre, ItemBlockDenseOre.class, denseOre.getBlockName());
		GameRegistry.registerBlock(energyCompressor, energyCompressor.getBlockName());
		GameRegistry.registerBlock(smartSafe, ItemBlockRarity.class, smartSafe.getBlockName());
		GameRegistry.registerBlock(saltwaterExtractor, saltwaterExtractor.getBlockName());
		GameRegistry.registerBlock(advancedLiquidLoader, advancedLiquidLoader.getBlockName());
    }


    public static void initLanguageNames() {
    	LanguageRegistry.addName(Blocks.get("saltwaterExtractor"), "Saltwater Extractor");
    	LanguageRegistry.addName(Blocks.get("smartSafe"), "Smart Safe");
    	LanguageRegistry.addName(Blocks.get("energyCompressor"), "Energy Compressor");
    	LanguageRegistry.addName(Blocks.get("advancedLiquidContainer"), "Advanced Liquid Container");
    	
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 0), "Dense Coal Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 1), "Dense Iron Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 2), "Dense Lapis Lazuli Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 3), "Dense Redstone Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 4), "Dense Emerald Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 5), "Dense Gold Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 6), "Dense Diamond Ore");
    }


    public static Block get(String blockName) {
        return blocks.get("tile." + blockName);
    }

}
