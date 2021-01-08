package com.rgbcraft.usefuladditions.handlers;


import java.util.HashMap;

import com.rgbcraft.usefuladditions.blocks.Blocks;
import com.rgbcraft.usefuladditions.items.Items;

import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class AchievementsHandler implements ICraftingHandler {
	
    private static HashMap<String, Achievement> achievements = new HashMap<String, Achievement>();

    private int baseX = -1;
    private int baseY = 0;
    private int currentID = 3025;
    
    public AchievementsHandler() {
    	this.initAchievements();
        AchievementPage.registerAchievementPage(new AchievementPage("Useful Additions", (Achievement[]) this.achievements.values().toArray(new Achievement[this.achievements.size()])));

        MinecraftForge.EVENT_BUS.register(this);
        GameRegistry.registerCraftingHandler(this);
    }
    
    public Achievement registerAchievement(String identifier, int x, int y, ItemStack icon, Achievement requirement, boolean isSpecial) {
        Achievement achievement = new Achievement(this.currentID, identifier, this.baseX + x, this.baseY + y, icon, requirement);
        
        if (isSpecial) {
            achievement.setSpecial();
        }
        
        achievement.registerAchievement();
        this.achievements.put(identifier, achievement);
        this.currentID++;
        
        return achievement;
    }
    
    private void initAchievements() {
    	this.registerAchievement("saltwaterPumping", baseX, baseY, new ItemStack(Blocks.get("saltwaterExtractor")), null, false);
    	this.registerAchievement("saltwaterEnergy", baseX + 2, baseY, new ItemStack(Blocks.get("osmosisGenerator")), this.achievements.get("saltwaterPumping"), false);
    	this.registerAchievement("safeSharing", baseX + 2, baseY + 2, new ItemStack(Blocks.get("smartSafe")), null, false);
    	this.registerAchievement("debugging", baseX + 2, baseY - 2, new ItemStack(Items.get("debugger")), null, false);
    }
    
    @Override
    public void onCrafting(EntityPlayer entityPlayer, ItemStack itemStack, IInventory inventory) {
    	if (itemStack.isItemEqual(new ItemStack(Blocks.get("osmosisGenerator")))) {
    		entityPlayer.triggerAchievement(this.achievements.get("saltWaterEnergy"));
    	} else if (itemStack.isItemEqual(new ItemStack(Blocks.get("smartSafe")))) {
    		entityPlayer.triggerAchievement(this.achievements.get("safeSharing"));
    	} else if (itemStack.isItemEqual(new ItemStack(Items.get("debugger")))) {
    		entityPlayer.triggerAchievement(this.achievements.get("debugging"));
    	}
    }
    
    @Override
    public void onSmelting(EntityPlayer entityPlayer, ItemStack itemStack) {}
    
    @ForgeSubscribe
    public void onItemPickup(EntityItemPickupEvent event) {}
    
    public static Achievement get(String identifier) {
        return achievements.get(identifier);
    }

}
