package com.rgbcraft.indeng.proxies;

import com.rgbcraft.indeng.items.Items;
import com.rgbcraft.indeng.utils.Liquids;

public class CommonProxy {
	
	public void initLiquids() {
//		Liquids.addLiquid("Salt Water", 3804, 0, 21500, 0, new ArrayList<String>(){{add("H2O, NaCl");}});
		Liquids.addLiquid("saltWater", 3008, 0, Items.get("saltWaterCanister"), Items.get("emptyCanister"));
	}
	
	public void preloadTextures() {}
	
	public void applyLiquidFX() {}

    public void initSounds() {}
    
    public void initRenderers() {}

	public int getRenderId(String name) {
		return 0;
	}
    
}
