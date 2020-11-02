package com.rgbcraft.indeng.blocks;

import java.util.HashMap;

import com.rgbcraft.indeng.IndustrialEngineering;
import com.rgbcraft.indeng.handlers.ConfigHandler;
import com.rgbcraft.indeng.items.Items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;

public class Blocks {

    public static HashMap<String, Block> blocks = new HashMap<String, Block>();

    public static void init() {
    	Block blockSaltwaterExtractor = new BlockSaltwaterExtractor(IndustrialEngineering.getConfig().getSaltwaterExtractorID());
		blocks.put(blockSaltwaterExtractor.getBlockName(), blockSaltwaterExtractor);
		
		for (HashMap.Entry<String, Block> entry : blocks.entrySet()) {
			entry.getValue().setTextureFile("/com/rgbcraft/indeng/assets/textures/blocks.png");
            GameRegistry.registerBlock(entry.getValue(), entry.getKey());
        }
    }


    public static void initTileEntities() {}


    public static void initLanguageNames() {
    	LanguageRegistry.addName(Blocks.get("blockSaltwaterExtractor"), "Saltwater Extractor");
    }


    public static Block get(String blockName) {
        return blocks.get("tile." + blockName);
    }

}
