package com.rgbcraft.usefuladditions.compat;

import com.rgbcraft.usefuladditions.UsefulAdditions;
import com.rgbcraft.usefuladditions.items.Items;
import com.rgbcraft.usefuladditions.liquids.Liquids;

import buildcraft.api.tools.IToolWrench;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class BuildCraftCompat {
	
	public static enum BuildCraftModule {
		Factory("BuildCraftFactory"),
		Transport("BuildCraftTransport"),
		Energy("BuildCraftEnergy");
		
		private String fullName;

		BuildCraftModule(String fullName) {
			this.fullName = fullName;
		}
		
		public String getFullName() {
			return this.fullName;
		}
	}

	public static Item getItem(BuildCraftModule module, String itemName) {
		try {
	        return (Item) Class.forName("buildcraft." + module.getFullName()).getField(itemName).get(null);
	    } catch (Exception ex) {}
		return null;
	}

	public static Block getBlock(BuildCraftModule module, String blockName) {
		try {
	        return (Block) Class.forName("buildcraft." + module.getFullName()).getField(blockName).get(null);
	    } catch (Exception ex) {}
		return null;
	}
	
	public static LiquidStack getLiquid(String liquidName) {
		try {
	        return (LiquidStack) Class.forName("buildcraft." + BuildCraftModule.Energy.getFullName()).getField(liquidName).get(null);
	    } catch (Exception ex) {}
		return null;
	}

	public static boolean isHoldingWrench(EntityPlayer entityPlayer) {
        return entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem().getItem() instanceof IToolWrench;
    }

	public static void registerBuildCraftLiquidsCompatibility() {
		try {
			LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(getLiquid("oilLiquid").itemID, LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(Items.get("canister"), 1, 3), new ItemStack(Items.get("canister"), 1, 0)));
			LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(getLiquid("fuelLiquid").itemID, LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(Items.get("canister"), 1, 4), new ItemStack(Items.get("canister"), 1, 0)));
			UsefulAdditions.log.info("Added BuildCraft liquids compatibility.");
		} catch (Exception ex) {}
	}

}
