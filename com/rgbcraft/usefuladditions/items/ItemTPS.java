package com.rgbcraft.usefuladditions.items;

import java.util.List;

import com.rgbcraft.usefuladditions.utils.LanguageManager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemTPS extends ItemFood {

	public ItemTPS(int id) {
		super(id, 20, 1F, true);
		
		setItemName("toiletPaperSandwich");
		setTextureFile(Items.textureFile);
		setIconIndex(255);
		setMaxStackSize(16);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List toolTip, boolean parBool) {
		if (GuiScreen.isShiftKeyDown()) {
			toolTip.add(LanguageManager.addTranslation("items", "item.toiletPaperSandwich.desc.line1", "Also known as &0\"TPS\"&r&7!"));
			toolTip.add(LanguageManager.addTranslation("items", "item.toiletPaperSandwich.desc.line2", "Recommended by 9/10 dentists!"));
		}
	}

}
