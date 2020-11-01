package com.rgbcraft.indeng.proxies;

import com.rgbcraft.indeng.handlers.SoundHandler;

public class ClientProxy extends CommonProxy {

    @Override
    public void initSounds() {
        new SoundHandler();
    }

}
