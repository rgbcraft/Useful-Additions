package com.rgbcraft.usefuladditions.utils;

import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraftforge.common.Configuration;

public class LanguageManager {
	
    public static Configuration languageFile;
    
    public LanguageManager(File file) {
        languageFile = new Configuration(file);
        languageFile.load();

    	this.initAchievementsTranslations();
    }
    
    public static String addTranslation(String category, String name, String text) {
    	text = text.replace("&", "\247");
    	
    	languageFile.getCategory(category);
        text = languageFile.get(category, name, text).value;

    	LanguageRegistry.instance().addStringLocalization(name, text);
    	
    	return text;
    }
    
    public static String addFormattedTranslation(String category, String name, String text, Object...objects) {
    	return addTranslation(category, name, MessageFormat.format(text, objects));
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

}
