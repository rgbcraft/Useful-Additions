package com.rgbcraft.usefuladditions.proxies;

import com.rgbcraft.usefuladditions.handlers.AchievementsHandler;
import com.rgbcraft.usefuladditions.handlers.ConfigHandler;
import com.rgbcraft.usefuladditions.items.Items;
import com.rgbcraft.usefuladditions.utils.Liquids;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class CommonProxy {
	
	public void initLiquids(ConfigHandler config) {
		// Salt Water
		Liquids.addLiquid("saltWater", "Salt Water", config.getLiquidId("SaltWater", 1200), 0, Items.get("saltWaterCanister"), Items.get("emptyCanister"));
		
		// CO
		Liquids.addLiquid("co", "CO", config.getLiquidId("CO", 1201), 3, Items.get("coCanister"), Items.get("emptyCanister"));
		
		// GPL
		Liquids.addLiquid("gpl", "GPL", config.getLiquidId("GPL", 1202), 2, Items.get("gplCanister"), Items.get("emptyCanister"));

		// Town Gas
		Liquids.addLiquid("townGas", "Town Gas", config.getLiquidId("TownGas", 1203), 1, Items.get("townGasCanister"), Items.get("emptyCanister"));
		
		// Gasoline
		Liquids.addLiquid("gasoline", "Gasoline", config.getLiquidId("Gasoline", 1204), 4, Items.get("gasolineCanister"), Items.get("emptyCanister"));
		
		// Kerosene
		Liquids.addLiquid("kerosene", "Kerosene", config.getLiquidId("Kerosene", 1205), 5, Items.get("keroseneCanister"), Items.get("emptyCanister"));
		
		// HFO
		Liquids.addLiquid("hfo", "HFO", config.getLiquidId("HFO", 1206), 6, Items.get("hfoCanister"), Items.get("emptyCanister"));
		
		// Bunker C
		Liquids.addLiquid("bunkerC", "Bunker-C", config.getLiquidId("BunkerC", 1207), 7, Items.get("bunkerCCanister"), Items.get("emptyCanister"));
		
		// Heated BunkerC
		Liquids.addLiquid("heatedBunkerC", "Heated Bunker-C", config.getLiquidId("HeatedBunkerC", 1208), 8, Items.get("heatedBunkerCCanister"), Items.get("emptyCanister"));
		
		// Crude residue
		Liquids.addLiquid("crudeResidue", "Crude Residue", config.getLiquidId("CrudeResidue", 1209), 9, Items.get("crudeResidueCanister"), Items.get("emptyCanister"));
		
		// Asphalt
		Liquids.addLiquid("asphalt", "Asphalt", config.getLiquidId("Asphalt", 1210), 10, Items.get("asphaltCanister"), Items.get("emptyCanister"));
		
		// Paraffin
		Liquids.addLiquid("paraffin", "Paraffin", config.getLiquidId("Paraffin", 1211), 11, Items.get("paraffinCanister"), Items.get("emptyCanister"));
		
		// Lubricant
		Liquids.addLiquid("lubricant", "Lubricant", config.getLiquidId("Lubricant", 1212), 12, Items.get("lubricantCanister"), Items.get("emptyCanister"));
	}
	
	public void preloadTextures() {}
	
	public void applyLiquidFX() {}

    public void initSounds() {}
    
    public void initRenderers() {}

	public int getRenderId(String name) {
		return 0;
	}
    
}
