package com.rgbcraft.usefuladditions.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rgbcraft.usefuladditions.handlers.ConfigHandler;
import com.rgbcraft.usefuladditions.utils.CreativeTab;
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
    	registerItem(new ItemCanister(config.getItemId("EmptyCanister", 1130), 0, "", false).setItemName("emptyCanister"));
    	registerItem(new ItemCanister(config.getItemId("WaterCanister", 1131), 1, "H2O", false).setItemName("waterCanister"));
    	registerItem(new ItemCanister(config.getItemId("LavaCanister", 1132), 2, "", true).setItemName("lavaCanister"));
    	registerItem(new ItemCanister(config.getItemId("MilkCanister", 1133), 3, "", true).setItemName("milkCanister"));
    	registerItem(new ItemCanister(config.getItemId("OilCanister", 1134), 4, "", false).setItemName("oilCanister"));
    	registerItem(new ItemCanister(config.getItemId("DieselCanister", 1135), 5, "", false).setItemName("dieselCanister"));

    	registerItem(new ItemCanister(config.getItemId("SaltWaterCanister", 1136), 16, "H2O, NaCl", false).setItemName("saltwaterCanister"));
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
    	
    	// Crafting Items
    	registerItem(new ItemBase(config.getItemId("LCDScreen", 1160)).setIconIndex(14).setItemName("lcdScreen"));
    	registerItem(new ItemBase(config.getItemId("Keypad", 1161)).setIconIndex(15).setItemName("keypad"));
    	registerItem(new ItemBase(config.getItemId("BasicPlating", 1162)).setIconIndex(30).setItemName("basicPlating"));
    	registerItem(new ItemBase(config.getItemId("AdvancedPlating", 1163)).setIconIndex(31).setItemName("advancedPlating"));
    	registerItem(new ItemBase(config.getItemId("basicASIC", 1164)).setIconIndex(46).setItemName("basicASIC"));
    	registerItem(new ItemBase(config.getItemId("advancedASIC", 1165)).setIconIndex(47).setItemName("advancedASIC"));
    	registerItem(new ItemBase(config.getItemId("membrane", 1166)).setIconIndex(63).setItemName("membrane"));
    	registerItem(new ItemBase(config.getItemId("membraneHousing", 1167)).setIconIndex(62).setItemName("membraneHousing"));
    	
    	// Other
    	registerItem(new ItemBase(config.getItemId("T.P.", 1168)).setIconIndex(239).setItemName("toiletPaper").setCreativeTab(CreativeTab.tabMaterials));
    	registerItem(new ItemTPS(config.getItemId("T.P.S.", 1169)));
    	registerItem(new ItemFood(10000, 1, 1.5f, false).setAlwaysEdible().setPotionEffect(17, 2, 255, 100.0f).setItemName("bogusCibo").setCreativeTab(CreativeTab.tabBrewing));
    }
    
    private static void registerItem(Item item) {
    	items.put(item.getItemName(), item);
    }

    public static Item get(String itemName) {
        return items.get("item." + itemName);
    }

}
