package com.rgbcraft.indeng.blocks;

import java.util.HashMap;

import net.minecraft.block.Block;

public class Blocks {

    public static HashMap<String, Block> blocks = new HashMap<String, Block>();

    public static void init() {}


    public static void initTileEntities() {}


    public static void initLanguageNames() {}


    public static Block get(String blockName) {
        return blocks.get("tile." + blockName);
    }

}
