package com.rgbcraft.usefuladditions.utils;

import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraftforge.common.Configuration;

public class LanguageManager {

    public static Configuration languageFile;
    private static Map<String, String> translations = new HashMap<String, String>();

    public LanguageManager(File file) {
        languageFile = new Configuration(file);
        languageFile.load();

        initBlockTranslations();
        initItemsTranslations();
        initGuisTranslations();
        initMiscTranslations();
        initLiquidsTranslations();
        initAchievementsTranslations();
    }

    public static String addTranslation(String category, String name, String text) {
        text = text.replace("&", "\247");

        languageFile.getCategory(category);
        text = languageFile.get(category, name, text).value;

        translations.put(name, text);
        LanguageRegistry.instance().addStringLocalization(name, text);

        return text;
    }

    public static String getTranslation(String name) {
        return translations.get(name);
    }

    public static String getFormattedTranslation(String name, Object... objects) {
        return MessageFormat.format(getTranslation(name), objects);
    }

    private void initBlockTranslations() {
        LanguageManager.addTranslation("blocks", "tile.saltwaterExtractor.name", "Salt Water Extractor");
        LanguageManager.addTranslation("blocks", "tile.osmosisGenerator.name", "Osmosis Generator");
        LanguageManager.addTranslation("blocks", "tile.smartSafe.name", "Smart Safe");
        LanguageManager.addTranslation("blocks", "tile.fluidCounter.name", "Fluid Counter");

        LanguageManager.addTranslation("blocks", "tile.chassis.0.name", "Basic Chassis");
        LanguageManager.addTranslation("blocks", "tile.chassis.1.name", "Advanced Chassis");

        LanguageManager.addTranslation("blocks", "tile.denseOre.0.name", "Dense Coal Ore");
        LanguageManager.addTranslation("blocks", "tile.denseOre.1.name", "Dense Iron Ore");
        LanguageManager.addTranslation("blocks", "tile.denseOre.2.name", "Dense Lapis Lazuli Ore");
        LanguageManager.addTranslation("blocks", "tile.denseOre.3.name", "Dense Redstone Ore");
        LanguageManager.addTranslation("blocks", "tile.denseOre.4.name", "Dense Emerald Ore");
        LanguageManager.addTranslation("blocks", "tile.denseOre.5.name", "Dense Gold Ore");
        LanguageManager.addTranslation("blocks", "tile.denseOre.6.name", "Dense Diamond Ore");
    }

    private void initItemsTranslations() {
        LanguageManager.addTranslation("items", "item.debugger.name", "Debugger");
        LanguageManager.addTranslation("items", "item.debugger.advanced.id", "&eID: &7{0}");
        LanguageManager.addTranslation("items", "item.debugger.advanced.metadata", "&eMetadata: &7{0}");
        LanguageManager.addTranslation("items", "item.debugger.header", "&8&m+---------------[&r&e DEBUG &8&m]---------------+");
        LanguageManager.addTranslation("items", "item.debugger.footer", "&8&m+--------------------------------------+");
        LanguageManager.addTranslation("items", "item.debugger.advanced.disabled", "&cAdvanced mode disabled.");
        LanguageManager.addTranslation("items", "item.debugger.advanced.enabled", "&aAdvanced mode enabled.");
        LanguageManager.addTranslation("items", "item.debugger.desc.line1.enabled", "Advanced mode: &aEnabled");
        LanguageManager.addTranslation("items", "item.debugger.desc.line1.disabled", "Advanced mode: &cDisabled");
        LanguageManager.addTranslation("items", "item.debugger.desc.line2", "Use &oSHIFT + Right Click&r&7 to change the mode.");

        LanguageManager.addTranslation("items", "item.UASensorKit.name", "U.A. Sensor Kit");

        LanguageManager.addTranslation("items", "item.UASensorCard.name", "U.A. Sensor Card");
        LanguageManager.addTranslation("items", "item.UASensorCard.desc.line1", "&cTarget not found.");
        LanguageManager.addTranslation("items", "item.UASensorCard.desc.line2", "Use \247oSHIFT + Right Click\247r\2477 to revert this card into a kit.");

        LanguageManager.addTranslation("items", "item.canister.empty.name", "Empty Canister");
        LanguageManager.addTranslation("items", "item.canister.water.name", "Water Canister");
        LanguageManager.addTranslation("items", "item.canister.lava.name", "Lava Canister");
        LanguageManager.addTranslation("items", "item.canister.oil.name", "Oil Canister");
        LanguageManager.addTranslation("items", "item.canister.diesel.name", "Diesel Canister");

        LanguageManager.addTranslation("items", "item.canister.saltWater.name", "Salt Water Canister");

        LanguageManager.addTranslation("items", "item.component.lcdScreen.name", "LCD Screen");
        LanguageManager.addTranslation("items", "item.component.keypad.name", "Keypad");
        LanguageManager.addTranslation("items", "item.component.basicPlating.name", "Basic Plating");
        LanguageManager.addTranslation("items", "item.component.advancedPlating.name", "Advanced Plating");
        LanguageManager.addTranslation("items", "item.component.basicASIC.name", "Basic ASIC");
        LanguageManager.addTranslation("items", "item.component.advancedASIC.name", "Advanced ASIC");
        LanguageManager.addTranslation("items", "item.component.membraneHousing.name", "Membrane Housing");
        LanguageManager.addTranslation("items", "item.component.membrane.name", "Membrane");

        LanguageManager.addTranslation("items", "item.toiletPaper.name", "Toilet Paper");
        LanguageManager.addTranslation("items", "item.toiletPaperSandwich.name", "Toilet Paper Sandwich");
        LanguageManager.addTranslation("items", "item.toiletPaperSandwich.desc.line1", "Also known as &o\"TPS\"&r&7!");
        LanguageManager.addTranslation("items", "item.toiletPaperSandwich.desc.line2", "Recommended by 9/10 dentists!");
    }

    private void initGuisTranslations() {
        LanguageManager.addTranslation("guis", "container.fluidCounter", "Fluid Counter");
        LanguageManager.addTranslation("guis", "container.fluidCounter.liquid", "Liquid: {0}");
        LanguageManager.addTranslation("guis", "container.fluidCounter.liquid.none", "None");

        LanguageManager.addTranslation("guis", "container.osmosisGenerator", "Osmosis Generator");

        LanguageManager.addTranslation("guis", "container.smartSafe.inventory", "Smart Safe");
        LanguageManager.addTranslation("guis", "container.smartSafe.lock.set", "Enter the PIN:");
        LanguageManager.addTranslation("guis", "container.smartSafe.lock.notSet", "Create a PIN:");
        LanguageManager.addTranslation("guis", "container.smartSafe.lock.toolTip", "&7Use &oSHIFT&r&7 to see the PIN.");
    }

    private void initMiscTranslations() {
        LanguageManager.addTranslation("misc", "misc.saltwaterExtractor.debug.requirement1", "At least 5 sides in contact with water. ({0})");
        LanguageManager.addTranslation("misc", "misc.saltwaterExtractor.debug.requirement2", "Vertical value must be equal or lower than 55. ({0})");

        LanguageManager.addTranslation("misc", "misc.saltwaterExtractor.debug.additionalInfo1", "Owner:");
        LanguageManager.addTranslation("misc", "misc.saltwaterExtractor.debug.additionalInfo1.none", "None");

        LanguageManager.addTranslation("misc", "misc.fluidCounter.sensor.amount", "Amount:");
        LanguageManager.addTranslation("misc", "misc.fluidCounter.sensor.liquid", "Liquid:");

        LanguageManager.addTranslation("misc", "misc.osmosisGenerator.debug.requirement1", "Vertical value must be equal to 69. ({0})");

        LanguageManager.addTranslation("misc", "misc.smartSafe.lock.pinUpdated", "&aThe PIN has been updated successfully!");
        LanguageManager.addTranslation("misc", "misc.smartSafe.lock.cannotUpdatePin", "&cYou can't update the PIN of a safe that not belongs to you!");
        LanguageManager.addTranslation("misc", "misc.smartSafe.lock.pinCreated", "&aThe PIN has been set successfully!");
        LanguageManager.addTranslation("misc", "misc.smartSafe.cannotRemoveIfNotEmpty", "&cYou can't remove the safe if there's something inside.");

        LanguageManager.addTranslation("misc", "misc.UASensorCard.panel.firstRow", "First Row");
        LanguageManager.addTranslation("misc", "misc.UASensorCard.panel.secondRow", "Second Row");
        LanguageManager.addTranslation("misc", "misc.UASensorCard.panel.thirdRow", "Third Row");
    }

    private void initLiquidsTranslations() {
        LanguageManager.addTranslation("liquids", "item.liquid.saltWater.name", "Salt Water");
    }

    private void initAchievementsTranslations() {
        LanguageManager.addTranslation("achievements", "achievement.saltwaterPumping", "Some salt from the water...");
        LanguageManager.addTranslation("achievements", "achievement.saltwaterPumping.desc", "Craft a Salt Water Extractor");

        LanguageManager.addTranslation("achievements", "achievement.saltwaterEnergy", "Energy from Salt Water!");
        LanguageManager.addTranslation("achievements", "achievement.saltwaterEnergy.desc", "Craft a Osmosis Generator");

        LanguageManager.addTranslation("achievements", "achievement.safeSharing", "Safe Sharing!");
        LanguageManager.addTranslation("achievements", "achievement.safeSharing.desc", "Craft a Smart Safe");

        LanguageManager.addTranslation("achievements", "achievement.debugging", "Debugging!");
        LanguageManager.addTranslation("achievements", "achievement.debugging.desc", "Craft a debugger");
    }

}
