package com.rgbcraft.usefuladditions.guis.components;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;


@SideOnly(Side.CLIENT)
public class GuiTooltip extends Gui {

    public List<String> lines;
    public FontRenderer fontRenderer;

    public GuiTooltip() {
        this.fontRenderer = Minecraft.getMinecraft().fontRenderer;
        this.lines = new ArrayList<String>();
    }

    // Lovingly stolen from GuiContainer.drawCreativeTabHoveringText and
    // reshaped to suit my needs.
    public void draw(int mouseX, int mouseY) {
        if (this.lines.size() < 1)
            return; // nothing to draw

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        int maxWidth = 12;
        int height = 0;
        for (String line : this.lines) {
            maxWidth = Math.max(maxWidth, this.fontRenderer.getStringWidth(line));
            height += this.fontRenderer.FONT_HEIGHT + 1;
        }
        height -= 1;
        int targetX = mouseX + 12;
        int targetY = mouseY - height - 4;
        this.zLevel = 300.0F;

        int bgCol = 0xF0100010; // In order: ARGB
        this.drawGradientRect(targetX - 3, targetY - 4, targetX + maxWidth + 3, targetY - 3, bgCol, bgCol);
        this.drawGradientRect(targetX - 3, targetY + height + 3, targetX + maxWidth + 3, targetY + height + 4, bgCol, bgCol);
        this.drawGradientRect(targetX - 3, targetY - 3, targetX + maxWidth + 3, targetY + height + 3, bgCol, bgCol);
        this.drawGradientRect(targetX - 4, targetY - 3, targetX - 3, targetY + height + 3, bgCol, bgCol);
        this.drawGradientRect(targetX + maxWidth + 3, targetY - 3, targetX + maxWidth + 4, targetY + height + 3, bgCol, bgCol);
        int var10 = 0x505000FF;
        int var11 = (var10 & 0xFEFEFE) >> 1 | var10 & 0xFF000000;
        this.drawGradientRect(targetX - 3, targetY - 3 + 1, targetX - 3 + 1, targetY + height + 3 - 1, var10, var11);
        this.drawGradientRect(targetX + maxWidth + 2, targetY - 3 + 1, targetX + maxWidth + 3, targetY + height + 3 - 1, var10, var11);
        this.drawGradientRect(targetX - 3, targetY - 3, targetX + maxWidth + 3, targetY - 3 + 1, var10, var10);
        this.drawGradientRect(targetX - 3, targetY + height + 2, targetX + maxWidth + 3, targetY + height + 3, var11, var11);

        for (int i = 0; i < this.lines.size(); i++)
            this.fontRenderer.drawStringWithShadow(this.lines.get(i), targetX, targetY + i * (this.fontRenderer.FONT_HEIGHT + 1), 0xFFFFFFFF);
        this.zLevel = 0.0F;

        GL11.glEnable(GL11.GL_LIGHTING);
        RenderHelper.enableGUIStandardItemLighting();
    }

}
