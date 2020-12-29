package com.rgbcraft.indeng.tiles;

import cpw.mods.fml.common.registry.GameRegistry;

public class Tiles {

	public static void init() {
    	GameRegistry.registerTileEntity(TileSmartSafe.class, "tileSmartSafe");
    	GameRegistry.registerTileEntity(TileEnergyCompressor.class, "tileEnergyCompressor");
    	GameRegistry.registerTileEntity(TileAdvancedLiquidContainer.class, "tileAdvancedLiquidContainer");
		GameRegistry.registerTileEntity(TileSaltwaterExtractor.class, "tileSaltawaterExtractor");
		GameRegistry.registerTileEntity(TileSaltwaterGenerator.class, "tileSaltawaterGenerator");
	}
	
}
