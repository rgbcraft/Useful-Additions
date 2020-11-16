package com.rgbcraft.indeng.proxies;

import java.util.ArrayList;

import com.rgbcraft.indeng.utils.Liquids;

public class CommonProxy {
	
	public void initLiquids() {
		Liquids.addLiquid("Salt Water", 3804, 0, 21500, 0, new ArrayList<String>(){{add("H2O, NaCl");}});
	}
	
	public void initLiquidFX() {}

    public void initSounds() {}
    
    public void initRenderers() {}

	public int getRenderId(String name) {
		return 0;
	}
    
}
