package com.rgbcraft.usefuladditions.guis.components;

import com.rgbcraft.usefuladditions.utils.Utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;


@SideOnly(Side.CLIENT)
public class GuiTank extends Gui {
    public ILiquidTank tank;

    public String parentTextureFile;

    public int displayHeight;
    public int gradationX;
    public int gradationY;

    private GuiTooltip tooltip;

    private int minX;
    private int minY;

    private int maxX;
    private int maxY;

    public GuiTank(LiquidTank tank, int minX, int minY, int maxX, int maxY, int displayHeight, int gradationX, int gradationY, String parentTextureFile) {
        this.parentTextureFile = parentTextureFile;
        this.displayHeight = displayHeight;

        this.gradationX = gradationX;
        this.gradationY = gradationY;

        this.tank = tank;

        this.minX = minX;
        this.minY = minY;

        this.maxX = maxX;
        this.maxY = maxY;

        this.tooltip = new GuiTooltip();
    }

    public int getScaledAmount() {
        if (tank == null) {
            return 0;
        }

        LiquidStack liquid = tank.getLiquid();

        if (liquid == null) {
            return 0;
        }

        float scaledAmount = (float) (liquid.amount * displayHeight) / (float) tank.getCapacity();
        return MathHelper.ceiling_float_int(scaledAmount);
    }

    public void draw() {
        if ((tank != null) && (tank.getLiquid() != null)) {
            ItemStack liquid = tank.getLiquid().asItemStack();
            int iconIndex = 0;

            if (liquid.itemID < Block.blocksList.length && Block.blocksList[liquid.itemID] != null) {
                ForgeHooksClient.bindTexture(Block.blocksList[liquid.itemID].getTextureFile(), 0);
                iconIndex = Block.blocksList[liquid.itemID].blockIndexInTexture;
            } else if (Item.itemsList[liquid.itemID] != null) {
                ForgeHooksClient.bindTexture(liquid.getItem().getTextureFile(), 0);
                iconIndex = liquid.getIconIndex();
            } else {
                return; // how do you draw something that's not a block or an item?
            }

            int scaledValue = getScaledAmount();

            int imgY = iconIndex / 16;
            int imgX = iconIndex - imgY * 16;

            for (int i = scaledValue; i > 0; i -= 16)
                drawTexturedModalRect(this.minX, this.minY + displayHeight - i, imgX * 16, imgY * 16, 16, Math.min(16, i));
        }

        ForgeHooksClient.bindTexture(parentTextureFile, 0);
        drawTexturedModalRect(this.minX, this.minY + 2, gradationX, gradationY, 16, displayHeight);
    }

    public void drawTooltip(int mouseX, int mouseY) {
        if ((mouseX >= this.minX - 1) && (mouseX < this.maxX + 1) && (mouseY >= this.minY - 1) && (mouseY < this.maxY + 1)) {

            if (this.tank != null) {
                tooltip.lines.clear();

                String amount = "0";
                if (this.tank.getLiquid() != null) {
                    amount = Utils.formatNumber(this.tank.getLiquid().amount);
                }

                tooltip.lines.add(amount + " / " + Utils.formatNumber(this.tank.getCapacity()));

                String liquidName = "Empty";
                if (this.tank.getLiquid() != null) {
                    liquidName = this.tank.getLiquid().asItemStack().getDisplayName();
                }

                tooltip.lines.add("\2477" + liquidName);
            }

            tooltip.draw(mouseX, mouseY);
        }
    }
}
