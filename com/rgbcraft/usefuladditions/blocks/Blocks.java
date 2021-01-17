package com.rgbcraft.usefuladditions.blocks;

import java.util.HashMap;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.handlers.ConfigHandler;
import com.rgbcraft.usefuladditions.items.itemblocks.ItemBlockGeneric;
import com.rgbcraft.usefuladditions.items.itemblocks.ItemBlockRarity;
import com.rgbcraft.usefuladditions.utils.LanguageManager;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class Blocks {

    private static HashMap<String, Block> blocks = new HashMap<String, Block>();
    public static final String textureFile = Utils.getResource(ResourceType.TEXTURE, "blocks.png");

    public static void init(ConfigHandler config) {
    	registerBlock(new BlockSaltwaterExtractor(config.getBlockId("SaltwaterExtractor", 1000)), null);
		registerBlock(new BlockOsmosisGenerator(config.getBlockId("OsmosisGenerator", 1001)), null);
		registerBlock(new BlockSmartSafe(config.getBlockId("SmartSafe", 1002)), ItemBlockRarity.class);
		registerBlock(new BlockFluidCounter(config.getBlockId("FluidCounter", 1003)), null);
		registerBlock(new BlockChassis(config.getBlockId("Chassis", 1004)), ItemBlockGeneric.class);
		registerBlock(new BlockDenseOre(config.getBlockId("DenseOre", 1005)), ItemBlockGeneric.class);
		
		UsefulAdditions.log.info("Initialized blocks.");
    }

    public static void initTranslations() {
    	LanguageManager.addTranslation("blocks", "tile.saltwaterExtractor.name", "Salt Water Extractor");
    	LanguageManager.addTranslation("blocks", "tile.osmosisGenerator.name", "Osmosis Generator");
    	LanguageManager.addTranslation("blocks", "tile.smartSafe.name", "Smart Safe");
    	LanguageManager.addTranslation("blocks", "tile.fluidCounter.name", "Fluid Counter");
    	
    	LanguageManager.addTranslation("blocks", "tile.chassis.0.name", "Basic Chassis");
    	LanguageManager.addTranslation("blocks", "tile.chassis.1.name", "Advanced Chassis");
    	
    	LanguageManager.addTranslation("blocks", "tile.denseOre.0.name", "Dense Coal Ore");
    	LanguageManager.addTranslation("blocks", "tile.denseOre.1.name", "Dense Iron Ore");
    	LanguageManager.addTranslation("blocks", "tile.denseOre.2.name", "Dense Lapis Lazuli Ore");
    	LanguageManager.addTranslation("blocks", "tile.denseOre.3.name", "Dense Redstone Ore");
    	LanguageManager.addTranslation("blocks", "tile.denseOre.4.name", "Dense Emerald Ore");
    	LanguageManager.addTranslation("blocks", "tile.denseOre.5.name", "Dense Gold Ore");
    	LanguageManager.addTranslation("blocks", "tile.denseOre.6.name", "Dense Diamond Ore");
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
