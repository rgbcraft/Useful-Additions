package com.rgbcraft.usefuladditions.blocks;

import java.util.HashMap;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.handlers.ConfigHandler;
import com.rgbcraft.usefuladditions.items.itemblocks.ItemBlockGeneric;
import com.rgbcraft.usefuladditions.items.itemblocks.ItemBlockRarity;
import com.rgbcraft.usefuladditions.tiles.TileFluidCounter;
import com.rgbcraft.usefuladditions.tiles.TileLoadBank;
import com.rgbcraft.usefuladditions.tiles.TileOsmosisGenerator;
import com.rgbcraft.usefuladditions.tiles.TileSaltwaterExtractor;
import com.rgbcraft.usefuladditions.tiles.TileSmartSafe;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;


public class Blocks {

    private static HashMap<String, Block> blocks = new HashMap<>();
    public static final String textureFile = Utils.getResource(ResourceType.TEXTURE, "blocks.png");

    public static void init(ConfigHandler config) {
        registerBlock(new BlockSaltwaterExtractor(config.getBlockId("SaltwaterExtractor", 1600)), null);
        registerBlock(new BlockOsmosisGenerator(config.getBlockId("OsmosisGenerator", 1601)), null);
        registerBlock(new BlockSmartSafe(config.getBlockId("SmartSafe", 1602)), ItemBlockRarity.class);
        registerBlock(new BlockFluidCounter(config.getBlockId("FluidCounter", 1603)), null);
        registerBlock(new BlockChassis(config.getBlockId("Chassis", 1604)), ItemBlockGeneric.class);
        registerBlock(new BlockLoadBank(config.getBlockId("LoadBank", 1605)), null);
        registerBlock(new BlockDenseOre(config.getBlockId("DenseOre", 149)), ItemBlockGeneric.class);

        UsefulAdditions.log.info("Initialized blocks.");
    }

    public static void initTileEntities() {
        GameRegistry.registerTileEntity(TileSaltwaterExtractor.class, "tileSaltawaterExtractor");
        GameRegistry.registerTileEntity(TileOsmosisGenerator.class, "tileSaltawaterGenerator");
        GameRegistry.registerTileEntity(TileSmartSafe.class, "tileSmartSafe");
        GameRegistry.registerTileEntity(TileFluidCounter.class, "tileFluidCounter");
        GameRegistry.registerTileEntity(TileLoadBank.class, "tileLoadBank");
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
