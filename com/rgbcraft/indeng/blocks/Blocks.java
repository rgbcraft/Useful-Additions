package com.rgbcraft.indeng.blocks;

import java.util.HashMap;

import com.rgbcraft.indeng.items.ItemBlockDenseOre;
import com.rgbcraft.indeng.tiles.TileSmartSafe;
import com.rgbcraft.indeng.utils.CreativeTab;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Blocks {

    private static HashMap<String, Block> blocks = new HashMap<String, Block>();
    private static String blocksTextureFile = "/com/rgbcraft/indeng/assets/textures/blocks.png";

    public static void init() {
    	Block blockSaltwaterExtractor = new BlockSaltwaterExtractor(3000).setTextureFile(blocksTextureFile).setCreativeTab(CreativeTab.IndEng);
		blocks.put(blockSaltwaterExtractor.getBlockName(), blockSaltwaterExtractor);
		GameRegistry.registerBlock(blockSaltwaterExtractor, blockSaltwaterExtractor.getBlockName());
		
		Block blockSmartSafe = new BlockSmartSafe(3001).setTextureFile("/com/rgbcraft/indeng/assets/textures/models/ModelSmartSafe.png").setCreativeTab(CreativeTab.IndEng);
		blocks.put(blockSmartSafe.getBlockName(), blockSmartSafe);
		GameRegistry.registerBlock(blockSmartSafe, blockSmartSafe.getBlockName());
		
		Block blockDenseOre = new BlockDenseOre(3002).setTextureFile(blocksTextureFile);
		blocks.put(blockDenseOre.getBlockName(), blockDenseOre);
		GameRegistry.registerBlock(blockDenseOre, ItemBlockDenseOre.class, blockDenseOre.getBlockName());
    }


    public static void initTileEntities() {
    	GameRegistry.registerTileEntity(TileSmartSafe.class, "smartSafeTileEntity");
    }


    public static void initLanguageNames() {
    	LanguageRegistry.addName(Blocks.get("blockSaltwaterExtractor"), "Saltwater Extractor");
    	
    	LanguageRegistry.addName(Blocks.get("blockSmartSafe"), "Smart Safe");
    	
    	LanguageRegistry.addName(new ItemStack(Blocks.get("blockDenseOre"), 1, 0), "Dense Coal Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("blockDenseOre"), 1, 1), "Dense Iron Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("blockDenseOre"), 1, 2), "Dense Lapis Lazuli Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("blockDenseOre"), 1, 3), "Dense Redstone Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("blockDenseOre"), 1, 4), "Dense Emerald Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("blockDenseOre"), 1, 5), "Dense Gold Ore");
    	LanguageRegistry.addName(new ItemStack(Blocks.get("blockDenseOre"), 1, 6), "Dense Diamond Ore");
    }


    public static Block get(String blockName) {
        return blocks.get("tile." + blockName);
    }

}
