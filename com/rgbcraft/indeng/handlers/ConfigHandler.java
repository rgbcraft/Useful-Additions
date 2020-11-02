package com.rgbcraft.indeng.handlers;

import java.io.File;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	private static ConfigHandler instance;
	
	private int saltwaterExtractorID;
	
	public static ConfigHandler createInstance(File file) {
		if(instance == null) {
			instance = new ConfigHandler(file);
		}
		return instance;
	}
	
	public static ConfigHandler getInstance() {
		return instance;
	}

	private ConfigHandler(File file) {
		Configuration config = new Configuration(file);
		config.load();
		
		this.saltwaterExtractorID =  config.get("blockids", "saltwaterextractor", 3000).getInt();
		
		config.save();
	}
	
	public int getSaltwaterExtractorID() {
		return this.saltwaterExtractorID;
	}
}
