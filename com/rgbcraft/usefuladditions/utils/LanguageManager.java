package com.rgbcraft.usefuladditions.utils;

import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraftforge.common.Configuration;

public class LanguageManager {
	
    private static Configuration languageFile;
    private static Map<String, String> translations = new HashMap<String, String>();
    
    public LanguageManager(File file) {
        languageFile = new Configuration(file);
    }
    
    public void init() {
        languageFile.load();
    	
    	this.initBlocksTranslations();
    	this.initItemsTranslations();
    	this.initLiquidsTranslations();
    	this.initGuiTranslations();
    	this.initAchievementsTranslations();
    	this.initMiscTraslations();
    	
        languageFile.save();
    }
    
    private void addTranslation(String category, String name, String text) {
    	languageFile.getCategory(category);
        text = languageFile.get(category, name, text).value;
        translations.put(name, text);

    	LanguageRegistry.instance().addStringLocalization(name, text);
    }
    
    public static String getTranslation(String key) {
    	String text = translations.get(key);
    	if (text == null || text.equals(""))
    		text = key;

		return text.replace("&", "\247");
    }
    
    public static String getFormattedTranslation(String key, Object...objects) {
		return MessageFormat.format(getTranslation(key), objects);
    }
    
    private void initBlocksTranslations() {
        this.addTranslation("blocks", "tile.saltwaterExtractor.name", "Salt Water Extractor");
        this.addTranslation("blocks", "tile.osmosisGenerator.name", "Osmosis Generator");
        this.addTranslation("blocks", "tile.smartSafe.name", "Smart Safe");
        this.addTranslation("blocks", "tile.fluidCounter.name", "Fluid Counter");
    }
    
    private void initItemsTranslations() {
        this.addTranslation("items", "item.debugger.name", "Debugger");
        this.addTranslation("items", "item.debugger.debug.header", "&8&m+---------------[&r &eDEBUG &8&m]---------------+");
        this.addTranslation("items", "item.debugger.debug.footer", "&8&m+--------------------------------------+");
        this.addTranslation("items", "item.debugger.advanced.id", "&eID:");
        this.addTranslation("items", "item.debugger.advanced.metadata", "&eMetadata:");
        this.addTranslation("items", "item.debugger.advanced.enabled", "&aAdvanced mode enabled.");
        this.addTranslation("items", "item.debugger.advanced.disabled", "&cAdvanced mode disabled.");
        this.addTranslation("items", "item.debugger.desc.line1.enabled", "&aEnabled");
        this.addTranslation("items", "item.debugger.desc.line1.disabled", "&cDisabled");
        this.addTranslation("items", "item.debugger.desc.line2", "&oSHIFT - Right Click &r&7to change mode.");
        
        this.addTranslation("items", "item.emptyCanister.name", "Empty Canister");
        this.addTranslation("items", "item.waterCanister.name", "Water Canister");
        this.addTranslation("items", "item.lavaCanister.name", "Lava Canister");
        this.addTranslation("items", "item.oilCanister.name", "Oil Canister");
        this.addTranslation("items", "item.dieselCanister.name", "Diesel Canister");
        
        this.addTranslation("items", "item.saltwaterCanister.name", "Salt Water Canister");
        this.addTranslation("items", "item.coCanister.name", "CO Canister");
        this.addTranslation("items", "item.gplCanister.name", "GPL Canister");
        this.addTranslation("items", "item.townGasCanister.name", "Town Gas Canister");
        this.addTranslation("items", "item.gasolineCanister.name", "Gasoline Canister");
        this.addTranslation("items", "item.keroseneCanister.name", "Kerosene Canister");
        this.addTranslation("items", "item.hfoCanister.name", "HFO Canister");
        this.addTranslation("items", "item.bunkerCCanister.name", "Bunker-C Canister");
        this.addTranslation("items", "item.heatedBunkerCCanister.name", "Heated Bunker-C Canister");
        this.addTranslation("items", "item.crudeResidue.name", "Crude Residue Canister");
        this.addTranslation("items", "item.asphaltCanister.name", "Asphalt Canister");
        this.addTranslation("items", "item.paraffinCanister.name", "Paraffin Canister");
        this.addTranslation("items", "item.lubricantCanister.name", "Lubricant Canister");
        
        this.addTranslation("items", "item.lcdScreen.name", "LCD Screen");
        this.addTranslation("items", "item.keypad.name", "Keypad");
        this.addTranslation("items", "item.basicPlating.name", "Basic Plating");
        this.addTranslation("items", "item.advancedPlating.name", "Advanced Plating");
        this.addTranslation("items", "item.basicProcessor.name", "Basic Processor");
        this.addTranslation("items", "item.advancedProcessor.name", "Advanced Processor");
    }
    
    private void initLiquidsTranslations() {
        this.addTranslation("items", "liquid.saltwater.name", "Salt Water");
        this.addTranslation("liquids", "liquid.co.name", "CO");
        this.addTranslation("liquids", "liquid.gpl.name", "GPL");
        this.addTranslation("liquids", "liquid.townGas.name", "Town Gas");
        this.addTranslation("liquids", "liquid.gasoline.name", "Gasoline");
        this.addTranslation("liquids", "liquid.kerosene.name", "Kerosene");
        this.addTranslation("liquids", "liquid.hfo.name", "HFO");
        this.addTranslation("liquids", "liquid.bunkerC.name", "Bunker-C");
        this.addTranslation("liquids", "liquid.heatedBunkerC.name", "Heated Bunker-C");
        this.addTranslation("liquids", "liquid.crudeResidue.name", "Crude Residue");
        this.addTranslation("liquids", "liquid.asphalt.name", "Asphalt");
        this.addTranslation("liquids", "liquid.paraffin.name", "Paraffin");
        this.addTranslation("liquids", "liquid.lubricant.name", "Lubricant");
    }
    
    private void initGuiTranslations() {
    	this.addTranslation("guis", "container.osmosisGenerator.title", "Osmosis Generator");
    	
    	this.addTranslation("guis", "container.smartSafe.inventory.title", "Smart Safe");
    	this.addTranslation("guis", "container.smartSafe.lock.title.not_initialized", "Create a new PIN:");
    	this.addTranslation("guis", "container.smartSafe.lock.title.initialized", "Insert the PIN:");
    	this.addTranslation("guis", "container.smartSafe.lock.tooltip", "&7Use SHIFT to see the PIN.");
    	this.addTranslation("guis", "container.smartSafe.lock.message.pin_changed", "&aPIN changed successfully.");
    	this.addTranslation("guis", "container.smartSafe.lock.message.cannot_change_pin", "&cCannot change the PIN of a Smart Safe that not belongs to you!");
    	this.addTranslation("guis", "container.smartSafe.lock.message.pin_created", "&aPIN created successfully.");
    	
    	this.addTranslation("guis", "container.fluidCounter.title", "Fluid Counter");
    }
    
    private void initAchievementsTranslations() {
    	this.addTranslation("achievements", "achievement.saltwaterPumping", "Some salt from the water...");
    	this.addTranslation("achievements", "achievement.saltwaterPumping.desc", "Craft a Salt Water Extractor");
    	
        this.addTranslation("achievements", "achievement.saltwaterEnergy", "Energy from Salt Water!");
        this.addTranslation("achievements", "achievement.saltwaterEnergy.desc", "Craft a Osmosis Generator");
        
        this.addTranslation("achievements", "achievement.safeSharing", "Safe Sharing!");
        this.addTranslation("achievements", "achievement.safeSharing.desc", "Craft a Smart Safe");
        
        this.addTranslation("achievements", "achievement.debugging", "Debugging!");
        this.addTranslation("achievements", "achievement.debugging.desc", "Craft a debugger");
    }
    
    private void initMiscTraslations() {
    	this.addTranslation("misc", "misc.lavaCanister.death", "{0} drank a lava canister.");
    	this.addTranslation("misc", "misc.coCanister.death", "{0} drank a CO canister.");
    }

}
