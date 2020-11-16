package com.rgbcraft.indeng.proxies;

import java.util.HashMap;

import com.rgbcraft.indeng.handlers.SoundHandler;
import com.rgbcraft.indeng.renderers.RenderSmartSafe;
import com.rgbcraft.indeng.tiles.TileSmartSafe;
import com.rgbcraft.indeng.utils.TextureLiquidsFX;

import cpw.mods.fml.client.TextureFXManager;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	private static HashMap<String, Integer> renderIds = new HashMap<String, Integer>();

	@Override
    public void initSounds() {
        new SoundHandler();
    }
    
    @Override
    public void initLiquidFX() {
    	TextureLiquidsFX saltWaterFX = new TextureLiquidsFX(4, 2, 188, 249, 249, 249, 0, "/com/rgbcraft/indeng/assets/textures/textures.png");
        saltWaterFX.tileImage = 3595;
        TextureFXManager.instance().addAnimation(saltWaterFX);
    }
    
    @Override
    public void initRenderers() {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileSmartSafe.class, new RenderSmartSafe());
    	RenderSmartSafe renderSmartSafe = new RenderSmartSafe();
    	renderIds.put("blockSmartSafe", renderSmartSafe.getRenderId());
    	RenderingRegistry.registerBlockHandler(renderSmartSafe);
    }

    
    @Override
    public int getRenderId(String name) {
    	return renderIds.get(name);
    }

}
