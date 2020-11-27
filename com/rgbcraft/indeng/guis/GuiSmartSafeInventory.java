package com.rgbcraft.indeng.guis;

import org.lwjgl.opengl.GL11;

import com.rgbcraft.indeng.containers.ContainerSmartSafeInventory;
import com.rgbcraft.indeng.network.PacketHandler;
import com.rgbcraft.indeng.tiles.TileSmartSafe;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiSmartSafeInventory extends GuiContainer {

	private TileSmartSafe tileSmartSafe;

	public GuiSmartSafeInventory(InventoryPlayer inventory, TileSmartSafe tileSmartSafe) {
		super(new ContainerSmartSafeInventory(inventory, tileSmartSafe.getTile()));
		
		this.tileSmartSafe = tileSmartSafe.getTile();
		
		xSize = 176;
		ySize = 222;
	}

	private static final int texture = Minecraft.getMinecraft().renderEngine.getTexture("/com/rgbcraft/indeng/assets/guis/SmartSafeInventory.png");
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRenderer.drawString("Smart Safe", 8, 6, 0x404040);
        fontRenderer.drawString(this.tileSmartSafe.getOwner(), (xSize - 7) - fontRenderer.getStringWidth(this.tileSmartSafe.getOwner()), 6, 0x404040);
	}

}
