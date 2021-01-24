package com.rgbcraft.usefuladditions.api;

import net.minecraft.block.Block;

public class Blocks {

    /*
     * Get a Block for a specific name, example: Blocks.get("emptyCanister")
     *
     * @param blockName Block name
     * 
     * @return The block or null if the item does not exist or an error occurred
     */
    public static Block get(String blockName) {
        try {
            return (Block) Class.forName("com.rgbcraft.usefuladditions.blocks.Blocks").getDeclaredMethod("get", String.class).invoke(null, blockName);
        } catch (Exception e) {
            System.out.println("Useful Additions API - ERROR: Cannot get block: " + blockName);
            return null;
        }
    }
}
