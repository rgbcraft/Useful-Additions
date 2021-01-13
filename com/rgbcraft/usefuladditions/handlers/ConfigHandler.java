package com.rgbcraft.usefuladditions.handlers;

import java.io.File;

import com.rgbcraft.usefuladditions.UsefulAdditions;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

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
			this.config.addCustomCategoryComment("blocks", "Blocks ID");
			
			this.config.get("blocks", "SaltWaterExtractor", 1000);
			this.config.get("blocks", "OsmosisGenerator", 1001);
			this.config.get("blocks", "SmartSafe", 1002);
			this.config.get("blocks", "FluidCounter", 1003);
			this.config.get("blocks", "Chassis", 1004);
			this.config.get("blocks", "DenseOre", 1005);
			
			// Items
			this.config.addCustomCategoryComment("items", "Items ID");
			
			this.config.get("items", "Debugger", 1100);
	//		this.config.get("items", "FluidCounterSensorKit", 3002);
	//		this.config.get("items", "FluidCounterSensorCard", 3004);
			
			//   Canisters
			this.config.get("items", "EmptyCanister", 1130);
			this.config.get("items", "WaterCanister", 1131);
			this.config.get("items", "LavaCanister", 1132);
			this.config.get("items", "MilkCanister", 1133);
			this.config.get("items", "OilCanister", 1134);
			this.config.get("items", "DieselCanister", 1135);
			this.config.get("items", "SaltWaterCanister", 1136);
	//		this.config.get("items", "COCanister", 1136);
	//		this.config.get("items", "GPLCanister", 1137);
	//		this.config.get("items", "TownGasCanister", 1138);
	//		this.config.get("items", "GasolineCanister", 1139);
	//		this.config.get("items", "KeroseneCanister", 1140);
	//		this.config.get("items", "HFOCanister", 1141);
	//		this.config.get("items", "BunkerCCanister", 1142);
	//		this.config.get("items", "HeatedBunkerCCanister", 1143);
	//		this.config.get("items", "CrudeResidueCanister", 1144);
	//		this.config.get("items", "AsphaltCanister", 1145);
	//		this.config.get("items", "ParaffinCanister", 1146);
	//		this.config.get("items", "LubricantCanister", 1147);
			
			//   Crafting
			this.config.get("items", "LCDScreen", 1160);
			this.config.get("items", "Keypad", 1161);
			this.config.get("items", "BasicPlating", 1162);
			this.config.get("items", "AdvancedPlating", 1163);
			this.config.get("items", "BasicASIC", 1164);
			this.config.get("items", "AdvancedASIC", 1165);
			this.config.get("items", "Membrane", 1166);
			this.config.get("items", "MembraneHousing", 1167);
	
			// Liquids
			this.config.addCustomCategoryComment("liquids", "Liquids ID");
			
			this.config.get("liquids", "SaltWater", 1200);
	//		this.config.get("liquids", "CO", 1201);
	//		this.config.get("liquids", "GPL", 1202);
	//		this.config.get("liquids", "TownGas", 1203);
	//		this.config.get("liquids", "Gasoline", 1204);
	//		this.config.get("liquids", "Kerosene", 1205);
	//		this.config.get("liquids", "HFO", 1206);
	//		this.config.get("liquids", "BunkerC", 1207);
	//		this.config.get("liquids", "HeatedBunkerC", 1208);
	//		this.config.get("liquids", "CrudeResidue", 1209);
	//		this.config.get("liquids", "Asphalt", 1210);
	//		this.config.get("liquids", "Paraffin", 1211);
	//		this.config.get("liquids", "Lubricant", 1212);

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
