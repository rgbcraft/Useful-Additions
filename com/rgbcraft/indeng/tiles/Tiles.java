package com.rgbcraft.indeng.tiles;

import cpw.mods.fml.common.registry.GameRegistry;

public class Tiles {

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileSaltwaterExtractor.class, "teSaltawaterExtractor");
	}
	
}
