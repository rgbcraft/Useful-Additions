package com.rgbcraft.usefuladditions.liquids;

import java.util.HashMap;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.handlers.ConfigHandler;
import com.rgbcraft.usefuladditions.items.ItemMetaLiquid;
import com.rgbcraft.usefuladditions.items.Items;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import cpw.mods.fml.client.TextureFXManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;


public class Liquids {

    public static HashMap<String, LiquidStack> liquids = new HashMap<String, LiquidStack>();

    public static void init(ConfigHandler config) {
        Items.registerItem(new ItemMetaLiquid(config.getBlockId("Liquids", 1700)));

        ItemMetaLiquid.addItem(0, "saltWater", "Salt Water", new ItemStack(Items.get("canister"), 1, 5), new ItemStack(Items.get("canister"), 1, 0));

        UsefulAdditions.log.info("Initialized liquids.");
    }

    public static void registerVanillaLiquidsCompatibility() {
        LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(Block.waterStill.blockID, 1000), new ItemStack(Items.get("canister"), 1, 1), new ItemStack(Items.get("canister"), 1, 0)));
        LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(Block.lavaStill.blockID, 1000), new ItemStack(Items.get("canister"), 1, 2), new ItemStack(Items.get("canister"), 1, 0)));
        UsefulAdditions.log.info("Added Vanilla liquids compatibility.");
    }

    @SideOnly(Side.CLIENT)
    public static void applyLiquidFx(int redMin, int greenMin, int blueMin, int redMax, int greenMax, int blueMax, int index) {
        TextureFXManager.instance().addAnimation(new TextureLiquidsFX(redMin, greenMin, blueMin, redMax, greenMax, blueMax, index, Utils.getResource(ResourceType.TEXTURE, "liquidsfx.png")));
    }

    public static LiquidStack get(String liquidName) {
        return liquids.get(liquidName);
    }

}
