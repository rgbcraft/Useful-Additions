package com.rgbcraft.usefuladditions.blocks;

import java.util.HashMap;

import com.rgbcraft.usefuladditions.handlers.ConfigHandler;
import com.rgbcraft.usefuladditions.items.itemblocks.ItemBlockDenseOre;
import com.rgbcraft.usefuladditions.items.itemblocks.ItemBlockGeneric;
import com.rgbcraft.usefuladditions.items.itemblocks.ItemBlockRarity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class Blocks {

    private static HashMap<String, Block> blocks = new HashMap<String, Block>();
    public static final String blocksTextureFile = "/com/rgbcraft/usefuladditions/assets/textures/blocks.png";

    public static void init(ConfigHandler config) {
    	registerBlock(new BlockSaltwaterExtractor(config.getBlockId("SaltWaterExtractor", 1000)), null);
		registerBlock(new BlockOsmosisGenerator(config.getBlockId("OsmosisGenerator", 1001)), null);
		registerBlock(new BlockSmartSafe(config.getBlockId("SmartSafe", 1002)), ItemBlockRarity.class);
		registerBlock(new BlockFluidCounter(config.getBlockId("FluidCounter", 1003)), null);
		registerBlock(new BlockChassis(config.getBlockId("Chassis", 1004)), ItemBlockGeneric.class);
		registerBlock(new BlockDenseOre(config.getBlockId("DenseOre", 1005)), ItemBlockDenseOre.class);
		
//		Block advancedLiquidLoader = new BlockAdvancedLiquidContainer(3004);
//		Block multiblock = new BlockHollow(3006);
//		GameRegistry.registerBlock(multiblock, multiblock.getBlockName());
    }


    public static void initLanguageNames() {
    	LanguageRegistry.addName(new ItemStack(Blocks.get("chassis"), 1, 0), "Basic Chassis");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("chassis"), 1, 1), "Advanced Chassis");
    	
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 0), "Dense Coal Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 1), "Dense Iron Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 2), "Dense Lapis Lazuli Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 3), "Dense Redstone Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 4), "Dense Emerald Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 5), "Dense Gold Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("denseOre"), 1, 6), "Dense Diamond Ore");
    }
    
    
    private static void registerBlock(Block block, Class itemBlock) {
    	blocks.put(block.getBlockName(), block);
    	
    	if (itemBlock == null)
    		GameRegistry.registerBlock(block, block.getBlockName());
    	else
    		GameRegistry.registerBlock(block, itemBlock, block.getBlockName());
    }


    public static Block get(String blockName) {
        return blocks.get("tile." + blockName);
    }

}
