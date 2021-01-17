package com.rgbcraft.usefuladditions.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.handlers.ConfigHandler;
import com.rgbcraft.usefuladditions.utils.CreativeTab;
import com.rgbcraft.usefuladditions.utils.LanguageManager;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class Items {

    private static HashMap<String, Item> items = new HashMap<String, Item>();
    public static final String textureFile = Utils.getResource(ResourceType.TEXTURE, "items.png");

    public static void init(ConfigHandler config) {
    	registerItem(new ItemDebugger(config.getItemId("Debugger", 1100)));

//    	Item IESensorKit = new ItemIESensorKit(config.getItemId("IESensorKit", 3002));
//    	items.put(IESensorKit.getItemName(), IESensorKit);
//    	
//    	Item IESensorCard = new ItemIESensorCard(config.getItemId("IESensorCard", 3004));
//    	items.put(IESensorCard.getItemName(), IESensorCard);
    	
    	// Liquids
    	registerItem(new ItemMetaCanister(config.getItemId("Canisters", 1130)));
    	
    	// Legacy Liquids
    	ItemMetaCanister.addSubItem(0, "empty", "Empty Canister", 0, null);
    	ItemMetaCanister.addSubItem(1, "water", "Water Canister", 1, "H2O");
    	ItemMetaCanister.addSubItem(2, "lava", "Lava Canister", 2, null);
    	ItemMetaCanister.addSubItem(3, "oil", "Oil Canister", 4, null);
    	ItemMetaCanister.addSubItem(4, "diesel", "Diesel Canister", 5, null);
    	
    	// Custom Liquids
    	ItemMetaCanister.addSubItem(5, "saltWater", "Salt Water Canister", 16, "H2O, NaCl");

//    	registerItem(new ItemCanister(config.getItemId("SaltWaterCanister", 1136), 16, "H2O, NaCl", false).setItemName("saltwaterCanister"));
//    	registerItem(new ItemCanister(config.getItemId("COCanister", 1136), 17, "CO", true).setItemName("coCanister"));
//    	registerItem(new ItemCanister(config.getItemId("GPLCanister", 1137), 18, "C3H8, C4", false).setItemName("gplCanister"));
//    	registerItem(new ItemCanister(config.getItemId("TownGasCanister", 1138), 19, "CO, H2", false).setItemName("townGasCanister"));
//    	registerItem(new ItemCanister(config.getItemId("GasolineCanister", 1139), 20, "C8", false).setItemName("gasolineCanister"));
//    	registerItem(new ItemCanister(config.getItemId("KeroseneCanister", 1140), 21, "C15", false).setItemName("keroseneCanister"));
//    	registerItem(new ItemCanister(config.getItemId("HFOCanister", 1141), 22, "C24", false).setItemName("hfoCanister"));
//    	registerItem(new ItemCanister(config.getItemId("BunkerCCanister", 1142), 23, "Category H", false).setItemName("bunkerCCanister"));
//    	registerItem(new ItemCanister(config.getItemId("HeatedBunkerCCanister", 1143), 24, "Category H", false).setItemName("heatedBunkerCCanister"));
//    	registerItem(new ItemCanister(config.getItemId("CrudeResidueCanister", 1144), 25, "> C24", false).setItemName("crudeResidueCanister"));
//    	registerItem(new ItemCanister(config.getItemId("AsphaltCanister", 1145), 26, "> C90", false).setItemName("asphaltCanister"));
//    	registerItem(new ItemCanister(config.getItemId("ParaffinCanister", 1146), 27, "> C20", false).setItemName("paraffinCanister"));
//    	registerItem(new ItemCanister(config.getItemId("LubricantCanister", 1147), 27, "> H18, O35", false).setItemName("lubricantCanister"));
    	
    	// Components
    	registerItem(new ItemMetaComponent(config.getItemId("Components", 1160)));
    	
    	ItemMetaComponent.addSubItem(0, "lcdScreen", "LCD Screen", 14);
    	ItemMetaComponent.addSubItem(1, "keypad", "Keypad", 15);
    	ItemMetaComponent.addSubItem(2, "basicPlating", "Basic Plating", 30);
    	ItemMetaComponent.addSubItem(3, "advancedPlating", "Advanced Plating", 31);
    	ItemMetaComponent.addSubItem(4, "basicASIC", "Basic ASIC", 46);
    	ItemMetaComponent.addSubItem(5, "advancedASIC", "Advanced ASIC", 47);
    	ItemMetaComponent.addSubItem(6, "membraneHousing", "Membrane Housing", 62);
    	ItemMetaComponent.addSubItem(7, "membrane", "Membrane", 63);
    	
    	// Other
    	registerItem(new ItemBase(config.getItemId("T.P.", 1161)).setIconIndex(239).setItemName("toiletPaper").setCreativeTab(CreativeTab.tabMaterials));
    	registerItem(new ItemTPS(config.getItemId("T.P.S.", 1162)));
    	registerItem(new ItemFood(10000, 1, 1.5f, false).setAlwaysEdible().setPotionEffect(17, 2, 255, 100.0f).setItemName("bogusCibo").setCreativeTab(CreativeTab.tabBrewing));
    	
    	UsefulAdditions.log.info("Initialized items.");
    }
    
    public static void initTranslations() {
    	// Basic items / tools
    	LanguageManager.addTranslation("items", "item.debugger.name", "Debugger");
    	
    	// Other items
    	LanguageManager.addTranslation("items", "item.toiletPaper.name", "Toilet Paper");
    	LanguageManager.addTranslation("items", "item.toiletPaperSandwich.name", "Toilet Paper Sandwich");
    }
    
    public static void registerItem(Item item) {
    	items.put(item.getItemName(), item);
    }

    public static Item get(String itemName) {
        return items.get("item." + itemName);
    }

}
