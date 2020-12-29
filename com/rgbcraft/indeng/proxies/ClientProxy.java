package com.rgbcraft.indeng.proxies;

import java.util.HashMap;

import com.rgbcraft.indeng.handlers.SoundHandler;
import com.rgbcraft.indeng.renderers.RenderSmartSafe;
import com.rgbcraft.indeng.tiles.TileSmartSafe;
import com.rgbcraft.indeng.utils.TextureLiquidFX;
import com.rgbcraft.indeng.utils.Utils;

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
		MinecraftForgeClient.preloadTexture("/com/rgbcraft/indeng/assets/textures/textures.png");
		MinecraftForgeClient.preloadTexture("/com/rgbcraft/indeng/assets/textures/models/ModelSmartSafe.png");
		MinecraftForgeClient.preloadTexture("/com/rgbcraft/indeng/assets/textures/blocks.png");
	}
    
	@Override
    public void applyLiquidFX() {
    	Utils.applyLiquidFx(5, 165, 252, 5, 223, 252, 0);
    }
    
    @Override
    public void initRenderers() {
    	RenderSmartSafe renderSmartSafe = new RenderSmartSafe();
    	renderIds.put("blockSmartSafe", renderSmartSafe.getRenderId());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileSmartSafe.class, renderSmartSafe);
    	RenderingRegistry.registerBlockHandler(renderSmartSafe);
    }

    
    @Override
    public int getRenderId(String name) {
    	return renderIds.get(name);
    }

}
