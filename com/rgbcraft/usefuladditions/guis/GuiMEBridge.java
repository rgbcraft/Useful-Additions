package com.rgbcraft.usefuladditions.guis;

import org.lwjgl.opengl.GL11;

import com.rgbcraft.usefuladditions.containers.ContainerMEBridge;
import com.rgbcraft.usefuladditions.tiles.TileMEBridge;
import com.rgbcraft.usefuladditions.utils.LanguageManager;
import com.rgbcraft.usefuladditions.utils.Utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;


@SideOnly(Side.CLIENT)
public class GuiMEBridge extends GuiContainer {

    private int top;
    private int center;
    private int main_width;
    private int left;
    private int main_height;
    private int bottom;

    public GuiMEBridge(InventoryPlayer inventory, TileMEBridge tileMEBridge) {
        super(new ContainerMEBridge(inventory, tileMEBridge));

        this.xSize = 176;
        this.ySize = 166;

        int BORDER = 4;

        this.main_width = this.xSize - BORDER * 2;
        this.main_height = this.ySize - BORDER * 2 - 92;

        this.left = BORDER;
        this.top = BORDER;
        this.center = this.left + this.main_width / 2;
        this.bottom = this.top + this.main_height;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1, 1, 1, 1);

        Utils.bindTexture(this.mc, "/gui/trap.png");
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        Utils.drawCenteredString(this.fontRenderer, LanguageManager.getTranslation("container.meBridge"), this.center, this.top + 1, 0x404040);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), this.left + 4, this.bottom + 2, 0x404040);
    }

}
