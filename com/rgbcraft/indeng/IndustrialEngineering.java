package com.rgbcraft.indeng;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import com.rgbcraft.indeng.blocks.Blocks;
import com.rgbcraft.indeng.handlers.ConfigHandler;
import com.rgbcraft.indeng.handlers.EventHandler;
import com.rgbcraft.indeng.handlers.GuiHandler;
import com.rgbcraft.indeng.handlers.RecipesHandler;
import com.rgbcraft.indeng.items.Items;
import com.rgbcraft.indeng.network.PacketHandler;
import com.rgbcraft.indeng.proxies.CommonProxy;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid="IndustrialEngineering", name="Industrial Engineering", version="1.0")
@NetworkMod(channels={"IndustrialEngineering"}, clientSideRequired=true, serverSideRequired=false, packetHandler=PacketHandler.class)
public class IndustrialEngineering {

    @Instance("IndustrialEngineering")
    public static IndustrialEngineering instance;

    @SidedProxy(clientSide="com.rgbcraft.indeng.proxies.ClientProxy", serverSide="com.rgbcraft.indeng.proxies.CommonProxy")
    public static CommonProxy proxy;


    @PreInit
    public void PreInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        proxy.initSounds();

        Items.init();
        Blocks.init();
    }

    @Init
    public void Init(FMLInitializationEvent event) {
        Items.initLanguageNames();

        Blocks.initLanguageNames();
        Blocks.initTileEntities();
        
        RecipesHandler.init();
        new GuiHandler();
    }

    @PostInit
    public void PostInit(FMLPostInitializationEvent event) {}

}
