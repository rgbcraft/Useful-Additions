package com.rgbcraft.usefuladditions.guis.components;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;


public class GuiButtonCustom extends GuiButton {

    private int xIndex;
    private int yIndex;
    private String textureFile;

    public GuiButtonCustom(int id, int x, int y, int xIndex, int yIndex, int width, int height, String textureFile) {
        super(id, x, y, width, height, null);
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.textureFile = textureFile;
    }

    public GuiButtonCustom(int id, int x, int y, int xIndex, int yIndex, int width, int height, String text, String textureFile) {
        super(id, x, y, width, height, text);
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.textureFile = textureFile;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        GL11.glBindTexture(3553, mc.renderEngine.getTexture(this.textureFile));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

        if (this.getHoverState(flag) == 0)
            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xIndex, this.height * 2, this.width, this.height);
        else if (this.getHoverState(flag) == 1)
            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xIndex, this.yIndex, this.width, this.height);
        else
            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xIndex, this.height, this.width, this.height);

        this.mouseDragged(mc, mouseX, mouseY);
    }

}
