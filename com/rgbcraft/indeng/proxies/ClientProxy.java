package com.rgbcraft.indeng.proxies;

import com.rgbcraft.indeng.handlers.SoundHandler;
import com.rgbcraft.indeng.utils.TextureLiquidsFX;

import cpw.mods.fml.client.TextureFXManager;

public class ClientProxy extends CommonProxy {

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

}
