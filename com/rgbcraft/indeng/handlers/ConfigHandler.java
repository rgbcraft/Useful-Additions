package com.rgbcraft.indeng.handlers;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	public static void init(File file) {
		Configuration config = new Configuration(file);
		config.load();
		
		int test = config.get("main", "test", 0).getInt();
		
		config.save();
	}
}
