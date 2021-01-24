package com.rgbcraft.usefuladditions.tiles;

import cpw.mods.fml.common.registry.GameRegistry;

public class Tiles {

    public static void init() {
        // GameRegistry.registerTileEntity(TileHollowMultiBlock.class, "tileMultiblock");

        GameRegistry.registerTileEntity(TileSmartSafe.class, "tileSmartSafe");
        GameRegistry.registerTileEntity(TileFluidCounter.class, "tileFluidCounter");
        // GameRegistry.registerTileEntity(TileAdvancedLiquidContainer.class,
        // "tileAdvancedLiquidContainer");
        GameRegistry.registerTileEntity(TileSaltwaterExtractor.class, "tileSaltawaterExtractor");
        GameRegistry.registerTileEntity(TileOsmosisGenerator.class, "tileSaltawaterGenerator");
    }

}
