package com.rgbcraft.usefuladditions;

import java.io.File;
import java.util.logging.Logger;

import com.rgbcraft.usefuladditions.blocks.Blocks;
import com.rgbcraft.usefuladditions.compat.BuildCraftCompat;
import com.rgbcraft.usefuladditions.handlers.AchievementsHandler;
import com.rgbcraft.usefuladditions.handlers.ConfigHandler;
import com.rgbcraft.usefuladditions.handlers.GuiHandler;
import com.rgbcraft.usefuladditions.handlers.RecipesHandler;
import com.rgbcraft.usefuladditions.handlers.WorldGenHandler;
import com.rgbcraft.usefuladditions.items.Items;
import com.rgbcraft.usefuladditions.liquids.Liquids;
import com.rgbcraft.usefuladditions.network.NetworkHandler;
import com.rgbcraft.usefuladditions.proxies.CommonProxy;
import com.rgbcraft.usefuladditions.tiles.Tiles;
import com.rgbcraft.usefuladditions.utils.CommandMain;
import com.rgbcraft.usefuladditions.utils.CreativeTab;
import com.rgbcraft.usefuladditions.utils.LanguageManager;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraft.creativetab.CreativeTabs;

@Mod(modid="usefuladditions", name="Useful Additions", version=UsefulAdditions.version, acceptedMinecraftVersions="1.4.7", dependencies="required-after:IC2; after:ThermalExpansion; after:ThermalExpansion|Transport; after:ThermalExpansion|Energy; after:ThermalExpansion|Factory; after:BuildCraft|Silicon; after:BuildCraft|Core; after:BuildCraft|Transport; after:BuildCraft|Factory; after:BuildCraft|Energy; after:BuildCraft|Builders;")
@NetworkMod(channels={NetworkHandler.NETWORK_CHANNEL}, clientSideRequired=true, serverSideRequired=false, packetHandler=NetworkHandler.class)
public class UsefulAdditions {
	
	public static final String version = "1.0";

	public static final CreativeTabs creativeTab = new CreativeTab(CreativeTabs.getNextID(), "Useful Additions");
	
	private static ConfigHandler config = new ConfigHandler(new File("config/Useful Additions/config.cfg"));
	private static LanguageManager langManager = new LanguageManager(new File("config/Useful Additions/i18n.lang"));
	public static Logger log = Logger.getLogger("Useful Additions");
	
    @Instance("usefuladditions")
    public static UsefulAdditions instance;

    @SidedProxy(clientSide="com.rgbcraft.usefuladditions.proxies.ClientProxy", serverSide="com.rgbcraft.usefuladditions.proxies.CommonProxy")
    public static CommonProxy proxy;

    @PreInit
    public void PreInit(FMLPreInitializationEvent event) {
        log.setParent(FMLLog.getLogger());
        log.info("Initialising Useful Additions...");
        
        config.init();

        proxy.initSounds();
        proxy.initRenderers();

        Items.init(config);
        Items.initTranslations();
        
        Blocks.init(config);
        Blocks.initTranslations();
        
        Liquids.init(config);
        Tiles.init();
        
        Liquids.registerVanillaLiquidsCompatibility();
        BuildCraftCompat.registerBuildCraftLiquidsCompatibility();
    }

    @Init
    public void Init(FMLInitializationEvent event) {
        RecipesHandler.init();
        
        proxy.preloadTextures();
        proxy.applyLiquidFX();
        
        new GuiHandler();
        new WorldGenHandler();
        new AchievementsHandler();
    }
    
    @Mod.ServerStarting
	public void serverLoad(FMLServerStartingEvent event) {
    	event.registerServerCommand(new CommandMain());
    	log.info("Initialized commands.");
	}

    @PostInit
    public void PostInit(FMLPostInitializationEvent event) {
    	langManager.languageFile.save();
    	log.info("Initialized Useful Additions.");
    }

}
