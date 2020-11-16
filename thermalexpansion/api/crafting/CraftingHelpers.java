// 
// Decompiled by Procyon v0.5.36
// 

package thermalexpansion.api.crafting;

import net.minecraft.block.Block;
import thermalexpansion.api.core.ItemRegistry;
import net.minecraft.item.ItemStack;

public class CraftingHelpers
{
    private static ItemStack sawdust;
    private static ItemStack slag;
    private static ItemStack slagRich;
    private static ItemStack fluxSand;
    
    public static boolean addPulverizerOreToDustRecipe(final ItemStack inputOre, final ItemStack outputDust) {
        final ItemStack ore = inputOre.copy();
        ore.stackSize = 1;
        final ItemStack primaryDust = outputDust.copy();
        primaryDust.stackSize = 2;
        return CraftingManagers.pulverizerManager.addRecipe(400, ore, primaryDust, false);
    }
    
    public static boolean addPulverizerOreToDustRecipe(final ItemStack inputOre, final ItemStack outputDust, final ItemStack outputSecondary) {
        final ItemStack ore = inputOre.copy();
        ore.stackSize = 1;
        final ItemStack primaryDust = outputDust.copy();
        primaryDust.stackSize = 2;
        final ItemStack secondary = outputSecondary.copy();
        secondary.stackSize = 1;
        return CraftingManagers.pulverizerManager.addRecipe(400, ore, primaryDust, secondary, 10, false);
    }
    
    public static boolean addSawmillLogToPlankRecipe(final ItemStack inputLog, final ItemStack outputPlanks) {
        final ItemStack log = inputLog.copy();
        log.stackSize = 1;
        final ItemStack planks = outputPlanks.copy();
        planks.stackSize = 6;
        return CraftingManagers.sawmillManager.addRecipe(80, log, planks, CraftingHelpers.sawdust, false);
    }
    
    public static boolean addSmelterDustToIngotsRecipe(final ItemStack inputDust, final ItemStack outputIngots) {
        final ItemStack dust = inputDust.copy();
        dust.stackSize = 2;
        final ItemStack ingots = outputIngots.copy();
        ingots.stackSize = 2;
        return CraftingManagers.smelterManager.addRecipe(80, dust, CraftingHelpers.fluxSand, ingots, CraftingHelpers.slag, 25, false);
    }
    
    public static boolean addSmelterOreToIngotsRecipe(final ItemStack inputOre, final ItemStack outputIngots) {
        final ItemStack ore = inputOre.copy();
        ore.stackSize = 1;
        final ItemStack ingots2 = outputIngots.copy();
        ingots2.stackSize = 2;
        final ItemStack ingots3 = outputIngots.copy();
        ingots3.stackSize = 3;
        return CraftingManagers.smelterManager.addRecipe(320, ore, CraftingHelpers.fluxSand, ingots2, CraftingHelpers.slagRich, 5, false) && CraftingManagers.smelterManager.addRecipe(400, ore, CraftingHelpers.slagRich, ingots3, CraftingHelpers.slag, 75, false);
    }
    
    static {
        CraftingHelpers.sawdust = ItemRegistry.getItem("sawdust", 1);
        CraftingHelpers.slag = ItemRegistry.getItem("slag", 1);
        CraftingHelpers.slagRich = ItemRegistry.getItem("slagRich", 1);
        CraftingHelpers.fluxSand = new ItemStack(Block.sand);
    }
}
