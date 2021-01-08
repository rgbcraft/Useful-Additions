package com.rgbcraft.usefuladditions.items;

import java.util.HashMap;

import com.rgbcraft.usefuladditions.handlers.ConfigHandler;

import net.minecraft.item.Item;

public class Items {

    private static HashMap<String, Item> items = new HashMap<String, Item>();
    public static final String itemsTextureFile = "/com/rgbcraft/usefuladditions/assets/textures/items.png";

    public static void init(ConfigHandler config) {
    	registerItem(new ItemDebugger(config.getItemId("Debugger", 1100)));

//    	Item IESensorKit = new ItemIESensorKit(config.getItemId("IESensorKit", 3002));
//    	items.put(IESensorKit.getItemName(), IESensorKit);
//    	
//    	Item IESensorCard = new ItemIESensorCard(config.getItemId("IESensorCard", 3004));
//    	items.put(IESensorCard.getItemName(), IESensorCard);
    	
//    	Item adjustableBlockPlacer = new ItemAdjustableBlockPlacer(config.getItemId("AdjustableBlockPlacer", 3005));
//    	items.put(adjustableBlockPlacer.getItemName(), adjustableBlockPlacer);
    	
    	// Liquids
    	registerItem(new ItemCanister(config.getItemId("EmptyCanister", 1130), 0, "", false).setItemName("emptyCanister"));
    	registerItem(new ItemCanister(config.getItemId("WaterCanister", 1131), 1, "H2O", false).setItemName("waterCanister"));
    	registerItem(new ItemCanister(config.getItemId("LavaCanister", 1132), 2, "", true).setItemName("lavaCanister"));
    	registerItem(new ItemCanister(config.getItemId("OilCanister", 1133), 3, "", false).setItemName("oilCanister"));
    	registerItem(new ItemCanister(config.getItemId("DieselCanister", 1134), 4, "", false).setItemName("dieselCanister"));

    	registerItem(new ItemCanister(config.getItemId("SaltWaterCanister", 1135), 16, "H2O, NaCl", false).setItemName("saltWaterCanister"));
    	registerItem(new ItemCanister(config.getItemId("COCanister", 1136), 17, "CO", true).setItemName("coCanister"));
    	registerItem(new ItemCanister(config.getItemId("GPLCanister", 1137), 18, "C3H8, C4", false).setItemName("gplCanister"));
    	registerItem(new ItemCanister(config.getItemId("TownGasCanister", 1138), 19, "CO, H2", false).setItemName("townGasCanister"));
    	registerItem(new ItemCanister(config.getItemId("GasolineCanister", 1139), 20, "C8", false).setItemName("gasolineCanister"));
    	registerItem(new ItemCanister(config.getItemId("KeroseneCanister", 1140), 21, "C15", false).setItemName("keroseneCanister"));
    	registerItem(new ItemCanister(config.getItemId("HFOCanister", 1141), 22, "C24", false).setItemName("hfoCanister"));
    	registerItem(new ItemCanister(config.getItemId("BunkerCCanister", 1142), 23, "Category H", false).setItemName("bunkerCCanister"));
    	registerItem(new ItemCanister(config.getItemId("HeatedBunkerCCanister", 1143), 24, "Category H", false).setItemName("heatedBunkerCCanister"));
    	registerItem(new ItemCanister(config.getItemId("CrudeResidueCanister", 1144), 25, "> C24", false).setItemName("crudeResidueCanister"));
    	registerItem(new ItemCanister(config.getItemId("AsphaltCanister", 1145), 26, "> C90", false).setItemName("asphaltCanister"));
    	registerItem(new ItemCanister(config.getItemId("ParaffinCanister", 1146), 27, "> C20", false).setItemName("paraffinCanister"));
    	registerItem(new ItemCanister(config.getItemId("LubricantCanister", 1147), 27, "> H18, O35", false).setItemName("lubricantCanister"));
    	
    	// Crafting Items
    	registerItem(new ItemBase(config.getItemId("LCDScreen", 1160)).setIconIndex(14).setItemName("lcdScreen"));
    	registerItem(new ItemBase(config.getItemId("Keypad", 1161)).setIconIndex(15).setItemName("keypad"));
    	registerItem(new ItemBase(config.getItemId("BasicPlating", 1162)).setIconIndex(30).setItemName("basicPlating"));
    	registerItem(new ItemBase(config.getItemId("AdvancedPlating", 1163)).setIconIndex(31).setItemName("advancedPlating"));
    	registerItem(new ItemBase(config.getItemId("BasicProcessor", 1164)).setIconIndex(46).setItemName("basicProcessor"));
    	registerItem(new ItemBase(config.getItemId("AdvancedProcessor", 1165)).setIconIndex(47).setItemName("advancedProcessor"));
    }
    
    private static void registerItem(Item item) {
    	items.put(item.getItemName(), item);
    }

    public static Item get(String itemName) {
        return items.get("item." + itemName);
    }

}
