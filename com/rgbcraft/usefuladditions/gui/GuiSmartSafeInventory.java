package com.rgbcraft.usefuladditions.gui;

import org.lwjgl.opengl.GL11;

import com.rgbcraft.usefuladditions.containers.ContainerSmartSafeInventory;
import com.rgbcraft.usefuladditions.tiles.TileSmartSafe;
import com.rgbcraft.usefuladditions.utils.LanguageManager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;


@SideOnly(Side.CLIENT)
public class GuiSmartSafeInventory extends GuiContainer {

	private TileSmartSafe tileSmartSafe;

	public GuiSmartSafeInventory(InventoryPlayer inventory, TileSmartSafe tileSmartSafe) {
		super(new ContainerSmartSafeInventory(inventory, tileSmartSafe));
		
		this.tileSmartSafe = tileSmartSafe;
		
		this.xSize = 176;
		this.ySize = 222;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().renderEngine.getTexture("/gui/container.png"));
		drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRenderer.drawString(LanguageManager.getTranslation("container.smartSafe.inventory"), 8, 6, 0x404040);
        fontRenderer.drawString(this.tileSmartSafe.getOwner(), (xSize - 7) - fontRenderer.getStringWidth(this.tileSmartSafe.getOwner()), 6, 0x404040);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

}
