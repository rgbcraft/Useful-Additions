package com.rgbcraft.usefuladditions.proxies;

import java.util.HashMap;

import com.rgbcraft.usefuladditions.handlers.SoundHandler;
import com.rgbcraft.usefuladditions.items.Items;
import com.rgbcraft.usefuladditions.renderers.RenderSmartSafe;
import com.rgbcraft.usefuladditions.tiles.TileSmartSafe;
import com.rgbcraft.usefuladditions.utils.Liquids;
import com.rgbcraft.usefuladditions.utils.TextureLiquidFX;
import com.rgbcraft.usefuladditions.utils.Utils;

import cpw.mods.fml.client.TextureFXManager;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	private static HashMap<String, Integer> renderIds = new HashMap<String, Integer>();

	@Override
    public void initSounds() {
        new SoundHandler();
    }
	
	@Override
	public void preloadTextures() {
		MinecraftForgeClient.preloadTexture("/com/rgbcraft/usefuladditions/assets/textures/textures.png");
		MinecraftForgeClient.preloadTexture("/com/rgbcraft/usefuladditions/assets/textures/models/ModelSmartSafe.png");
		MinecraftForgeClient.preloadTexture("/com/rgbcraft/usefuladditions/assets/textures/blocks.png");
	}
    
	@Override
    public void applyLiquidFX() {
		// Salt Water
		Liquids.applyLiquidFx(5, 165, 252, 5, 223, 252, 0);

		// Town Gas
		Liquids.applyLiquidFx(5, 165, 252, 5, 226, 252, 1);
		
		// GPL
		Liquids.applyLiquidFx(5, 165, 252, 5, 227, 252, 2);
		
		// CO
		Liquids.applyLiquidFx(5, 165, 252, 5, 229, 252, 3);
		
		// Gasoline
		Liquids.applyLiquidFx(5, 165, 252, 5, 222, 252, 4);
		
		// Kerosene
		Liquids.applyLiquidFx(5, 165, 252, 5, 221, 252, 5);
		
		// HFO
		Liquids.applyLiquidFx(5, 165, 252, 5, 213, 252, 6);
		
		// Bunker C
		Liquids.applyLiquidFx(5, 165, 252, 5, 253, 252, 7);
		
		// Heated BunkerC
		Liquids.applyLiquidFx(5, 165, 252, 5, 223, 252, 8);
		
		// Crude residue
		Liquids.applyLiquidFx(5, 165, 252, 5, 123, 252, 9);
		
		// Asphalt
		Liquids.applyLiquidFx(5, 165, 252, 5, 223, 252, 10);
		
		// Paraffin
		Liquids.applyLiquidFx(5, 165, 252, 5, 213, 252, 11);
		
		// Lubricant
		Liquids.applyLiquidFx(5, 165, 252, 5, 125, 252, 12);
    }
    
    @Override
    public void initRenderers() {
    	RenderSmartSafe renderSmartSafe = new RenderSmartSafe();
    	renderIds.put("smartSafe", renderSmartSafe.getRenderId());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileSmartSafe.class, renderSmartSafe);
    	RenderingRegistry.registerBlockHandler(renderSmartSafe);
    }

    
    @Override
    public int getRenderId(String name) {
    	return renderIds.get(name);
    }

}
