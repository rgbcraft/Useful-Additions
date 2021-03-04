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

    private static HashMap<String, Achievement> achievements = new HashMap<>();

    private int baseX = -1;
    private int baseY = 0;
    private int currentID = 3025;

    public AchievementsHandler() {
        this.initAchievements();
        AchievementPage.registerAchievementPage(new AchievementPage("Useful Additions", AchievementsHandler.achievements.values().toArray(new Achievement[AchievementsHandler.achievements.size()])));

        MinecraftForge.EVENT_BUS.register(this);
        GameRegistry.registerCraftingHandler(this);
    }

    public Achievement registerAchievement(String identifier, int x, int y, ItemStack icon, Achievement requirement, boolean isSpecial) {
        Achievement achievement = new Achievement(this.currentID, identifier, this.baseX + x, this.baseY + y, icon, requirement);

        if (isSpecial)
            achievement.setSpecial();

        achievement.registerAchievement();
        AchievementsHandler.achievements.put(identifier, achievement);
        this.currentID++;

        return achievement;
    }

    private void initAchievements() {
        this.registerAchievement("saltwaterPumping", this.baseX, this.baseY, new ItemStack(Blocks.get("saltwaterExtractor")), null, false);
        this.registerAchievement("saltwaterEnergy", this.baseX + 2, this.baseY, new ItemStack(Blocks.get("osmosisGenerator")), AchievementsHandler.achievements.get("saltwaterPumping"), false);
        this.registerAchievement("safeSharing", this.baseX + 2, this.baseY + 2, new ItemStack(Blocks.get("smartSafe")), null, false);
        this.registerAchievement("debugging", this.baseX + 2, this.baseY - 2, new ItemStack(Items.get("debugger")), null, false);
        this.registerAchievement("newOres", this.baseX + 4, this.baseY - 2, new ItemStack(Blocks.get("denseOre"), 1, 3), null, false);
    }

    @Override
    public void onCrafting(EntityPlayer entityPlayer, ItemStack itemStack, IInventory inventory) {
        if (itemStack.isItemEqual(new ItemStack(Blocks.get("osmosisGenerator"))))
            entityPlayer.triggerAchievement(AchievementsHandler.achievements.get("saltWaterEnergy"));
        else if (itemStack.isItemEqual(new ItemStack(Blocks.get("smartSafe"))))
            entityPlayer.triggerAchievement(AchievementsHandler.achievements.get("safeSharing"));
        else if (itemStack.isItemEqual(new ItemStack(Items.get("debugger"))))
            entityPlayer.triggerAchievement(AchievementsHandler.achievements.get("debugging"));
    }

    @Override
    public void onSmelting(EntityPlayer entityPlayer, ItemStack itemStack) {}

    @ForgeSubscribe
    public void onItemPickup(EntityItemPickupEvent event) {
        ItemStack item = event.item.getEntityItem();
        if (item.isItemEqual(new ItemStack(Blocks.get("denseOre"), 1, 0)) || item.isItemEqual(new ItemStack(Blocks.get("denseOre"), 1, 1)) || item.isItemEqual(new ItemStack(Blocks.get("denseOre"), 1, 2)) || item.isItemEqual(new ItemStack(Blocks.get("denseOre"), 1, 3)) || item.isItemEqual(new ItemStack(Blocks.get("denseOre"), 1, 4)) || item.isItemEqual(new ItemStack(Blocks.get("denseOre"), 1, 5)) || item.isItemEqual(new ItemStack(Blocks.get("denseOre"), 1, 6)))
            event.entityPlayer.triggerAchievement(AchievementsHandler.achievements.get("newOres"));
    }

    public static Achievement get(String identifier) {
        return achievements.get(identifier);
    }

}
