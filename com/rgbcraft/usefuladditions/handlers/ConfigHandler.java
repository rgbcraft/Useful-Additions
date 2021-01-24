package com.rgbcraft.usefuladditions.handlers;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {

    public static Configuration configFile;

    public ConfigHandler(File file) {
        configFile = new Configuration(file);
        configFile.load();
    }

    public static Configuration getConfig() {
        return configFile;
    }

    public int getBlockId(String key, int defaultId) {
        return configFile.get("blocks", key, defaultId).getInt();
    }

    public int getItemId(String key, int defaultId) {
        return configFile.get("items", key, defaultId).getInt();
    }

}
