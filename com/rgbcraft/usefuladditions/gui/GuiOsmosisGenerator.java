package com.rgbcraft.usefuladditions.gui;

import org.lwjgl.opengl.GL11;

import com.rgbcraft.usefuladditions.containers.ContainerOsmosisGenerator;
import com.rgbcraft.usefuladditions.gui.components.GuiTank;
import com.rgbcraft.usefuladditions.tiles.TileOsmosisGenerator;
import com.rgbcraft.usefuladditions.utils.LanguageManager;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;


@SideOnly(Side.CLIENT)
public class GuiOsmosisGenerator extends GuiContainer {

    private GuiTank guiTank;
    private TileOsmosisGenerator tileOsmosisGenerator;
    private int main_width, main_height, left, top, center, middle, right, bottom;

    public GuiOsmosisGenerator(InventoryPlayer inventory, TileOsmosisGenerator tileOsmosisGenerator) {
        super(new ContainerOsmosisGenerator(inventory, tileOsmosisGenerator));
        this.tileOsmosisGenerator = tileOsmosisGenerator;
        
        this.guiTank = new GuiTank(this.tileOsmosisGenerator.tank, 62, 20, 78, 67, 47, 176, 0, Utils.getResource(ResourceType.GUI, "GuiOsmosisGenerator.png"));

        // These are such wonderful things to have, I've just stolen them from SeedManager.
        int BORDER = 4;
        
        this.main_width = this.xSize - BORDER * 2;
        this.main_height = this.ySize - BORDER * 2 - 92;
        
        this.left = BORDER;
        this.top = BORDER;
        this.center = this.left + this.main_width / 2;
        this.middle = this.top + this.main_height / 2;
        this.right = this.left + this.main_width;
        this.bottom = this.top + this.main_height;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        int textureID = mc.renderEngine.getTexture(Utils.getResource(ResourceType.GUI, "GuiOsmosisGenerator.png"));
        mc.renderEngine.bindTexture(textureID);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        // Transfer the coordinate space to within the GUI screen.
        GL11.glPushMatrix();
        GL11.glTranslatef(guiLeft, guiTop, 0.0F);

        // Draw the background.
        drawTexturedModalRect(0, 0, 0, 0, xSize, ySize);
        
        this.guiTank.draw();

        // Restore previous coordinates.
        GL11.glPopMatrix();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        // Easier to work with these when localized to the GUI proper.
        mouseX -= guiLeft;
        mouseY -= guiTop;

        Utils.drawCenteredString(this.fontRenderer, LanguageManager.getTranslation("container.osmosisGenerator"), center, top +  2, 0x404040);

        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), left + 4, bottom + 2, 0x404040);

        this.guiTank.drawTooltip(mouseX, mouseY);
    }

}