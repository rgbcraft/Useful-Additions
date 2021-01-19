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
    	registerItem(new ItemDebugger(config.getItemId("Debugger", 6600)));

//    	Item IESensorKit = new ItemIESensorKit(config.getItemId("IESensorKit", 3002));
//    	items.put(IESensorKit.getItemName(), IESensorKit);
//    	
//    	Item IESensorCard = new ItemIESensorCard(config.getItemId("IESensorCard", 3004));
//    	items.put(IESensorCard.getItemName(), IESensorCard);
    	
    	// Liquids
    	registerItem(new ItemMetaCanister(config.getItemId("Canisters", 6696)));
    	
    	// Legacy Liquids
    	ItemMetaCanister.addSubItem(0, "empty", 0, null);
    	ItemMetaCanister.addSubItem(1, "water", 1, "H2O");
    	ItemMetaCanister.addSubItem(2, "lava", 2, null);
    	ItemMetaCanister.addSubItem(3, "oil", 4, null);
    	ItemMetaCanister.addSubItem(4, "diesel", 5, null);
    	
    	// Custom Liquids
    	ItemMetaCanister.addSubItem(5, "saltWater", 16, "H2O, NaCl");

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
    	registerItem(new ItemMetaComponent(config.getItemId("Components", 6697)));
    	
    	ItemMetaComponent.addSubItem(0, "lcdScreen", 14);
    	ItemMetaComponent.addSubItem(1, "keypad", 15);
    	ItemMetaComponent.addSubItem(2, "basicPlating", 30);
    	ItemMetaComponent.addSubItem(3, "advancedPlating", 31);
    	ItemMetaComponent.addSubItem(4, "basicASIC", 46);
    	ItemMetaComponent.addSubItem(5, "advancedASIC", 47);
    	ItemMetaComponent.addSubItem(6, "membraneHousing", 62);
    	ItemMetaComponent.addSubItem(7, "membrane", 63);
    	
    	// Other
    	registerItem(new ItemBase(config.getItemId("ToiletPaper", 6698)).setIconIndex(239).setItemName("toiletPaper").setCreativeTab(CreativeTab.tabMaterials));
    	registerItem(new ItemTPS(config.getItemId("ToiletPaperSandwich", 6699)));
    	
    	UsefulAdditions.log.info("Initialized items.");
    }
    
    public static void registerItem(Item item) {
    	items.put(item.getItemName(), item);
    }

    public static Item get(String itemName) {
        return items.get("item." + itemName);
    }

}
