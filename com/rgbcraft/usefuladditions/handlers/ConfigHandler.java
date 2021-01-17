package com.rgbcraft.usefuladditions.handlers;

import java.io.File;

import com.rgbcraft.usefuladditions.UsefulAdditions;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {

	private static Configuration config;
	
	public ConfigHandler(File file) {
		Configuration config = new Configuration(file);
		this.config = config;
	}
	
	public void init() {
		try {
			this.config.load();
			
			// Blocks
			this.config.addCustomCategoryComment("blocks", "Blocks IDs");
			
			this.config.get("blocks", "SaltwaterExtractor", 1000);
			this.config.get("blocks", "OsmosisGenerator", 1001);
			this.config.get("blocks", "SmartSafe", 1002);
			this.config.get("blocks", "FluidCounter", 1003);
			this.config.get("blocks", "Chassis", 1004);
			this.config.get("blocks", "DenseOre", 1005);

			// Items
			this.config.addCustomCategoryComment("items", "Items IDs");
			
			this.config.get("items", "Debugger", 1100);
	//		this.config.get("items", "FluidCounterSensorKit", 3002);
	//		this.config.get("items", "FluidCounterSensorCard", 3004);

			//   Components
			this.config.get("items", "Components", 1160);


			// Liquids
			this.config.addCustomCategoryComment("liquids", "Liquids and Canisters IDs");
			
			this.config.get("liquids", "Liquids", 1200);
			
			//   Canisters
			this.config.get("items", "Canisters", 1130);
			

	    } catch (Exception e) {
	    } finally {
	        config.save();
	    }
				
		UsefulAdditions.log.info("Loaded config file.");
	}
	
	public static Configuration getConfig() {
		return config;
	}
	
	public int getBlockId(String key, int defaultId) {
		return this.config.get("blocks", key, defaultId).getInt();
	}
	
	public int getItemId(String key, int defaultId) {
		return this.config.get("items", key, defaultId).getInt();
	}
	
	public int getLiquidId(String key, int defaultId) {
		return this.config.get("liquids", key, defaultId).getInt();
	}

}
