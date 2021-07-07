package com.rgbcraft.usefuladditions.guis;

import org.lwjgl.opengl.GL11;

import com.google.common.io.ByteArrayDataOutput;
import com.rgbcraft.usefuladditions.containers.ContainerFluidCounter;
import com.rgbcraft.usefuladditions.network.NetworkHandler;
import com.rgbcraft.usefuladditions.tiles.TileFluidCounter;
import com.rgbcraft.usefuladditions.utils.LanguageManager;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;


@SideOnly(Side.CLIENT)
public class GuiFluidCounter extends GuiContainer {

    private TileFluidCounter tileLiquidCounter;
    private int main_width;
    private int left;
    private int top;
    private int center;
    private GuiButton resetButton;

    public GuiFluidCounter(InventoryPlayer inventory, TileFluidCounter tileFluidCounter) {
        super(new ContainerFluidCounter(tileFluidCounter));

        this.tileLiquidCounter = tileFluidCounter;

        this.xSize = 176;
        this.ySize = 84;

        int BORDER = 4;

        this.main_width = this.xSize - BORDER * 2;
        this.left = BORDER;
        this.top = BORDER;
        this.center = this.left + this.main_width / 2;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1, 1, 1, 1);

        Utils.bindTexture(this.mc, Utils.getResource(ResourceType.GUI, "GuiFluidCounter.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        Utils.drawCenteredString(this.fontRenderer, LanguageManager.getTranslation("container.fluidCounter"), this.center, this.top + 2, 0x404040);
        Utils.drawCenteredString(this.fontRenderer, String.valueOf(Utils.formatNumber(this.tileLiquidCounter.getAmount())) + " mB", this.center, this.top + 20, 0x404040);
        Utils.drawCenteredString(this.fontRenderer, LanguageManager.getFormattedTranslation("container.fluidCounter.liquid", this.tileLiquidCounter.getLiquidName()), this.center, this.top + 30, 0x404040);
    }

    @Override
    public void initGui() {
        super.initGui();

        this.controlList.clear();
        this.controlList.add(this.resetButton = new GuiButton(0, this.guiLeft + 52, this.guiTop + 55, 70, 20, "Reset"));

        this.resetButton.enabled = this.tileLiquidCounter.getAmount() > 0;
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        this.resetButton.enabled = this.tileLiquidCounter.getAmount() > 0;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            ByteArrayDataOutput data = NetworkHandler.createBasePacket("resetCounter", this.tileLiquidCounter);
            data.writeShort(button.id);
            NetworkHandler.sendDataPacketToServer(data);
        }
    }

}
