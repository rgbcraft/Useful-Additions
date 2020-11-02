package com.rgbcraft.indeng.handlers;

import java.io.File;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	private int saltwaterExtractorID;

	public ConfigHandler(File file) {
		Configuration config = new Configuration(file);
		config.load();

		int saltwaterExtractorID = config.get("blockIDs", "saltwaterExtractor", 3000).getInt();

		config.save();
	}
	
	public int getSaltwaterExtractorID() {
		return saltwaterExtractorID;
	}
}
