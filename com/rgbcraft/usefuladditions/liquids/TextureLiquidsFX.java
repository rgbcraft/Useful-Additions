package com.rgbcraft.usefuladditions.liquids;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLTextureFX;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderEngine;


@SideOnly(Side.CLIENT)
public class TextureLiquidsFX extends FMLTextureFX {

    private final int redMin, redMax, greenMin, greenMax, blueMin, blueMax;

    public TextureLiquidsFX(int redMin, int greenMin, int blueMin, int redMax, int greenMax, int blueMax, int spriteIndex, String texture) {
        super(spriteIndex);
        this.tileImage = Minecraft.getMinecraft().renderEngine.getTexture(texture);

        this.redMin = redMin;
        this.redMax = redMax;
        this.greenMin = greenMin;
        this.greenMax = greenMax;
        this.blueMin = blueMin;
        this.blueMax = blueMax;
        this.setup();
    }

    @Override
    public void setup() {
        super.setup();

        this.red = new float[this.tileSizeSquare];
        this.green = new float[this.tileSizeSquare];
        this.blue = new float[this.tileSizeSquare];
        this.alpha = new float[this.tileSizeSquare];
    }

    @Override
    public void bindImage(RenderEngine renderengine) {
        GL11.glBindTexture(3553 /* GL_TEXTURE_2D */, this.tileImage);
    }

    @Override
    public void onTick() {

        for (int i = 0; i < this.tileSizeBase; ++i)
            for (int j = 0; j < this.tileSizeBase; ++j) {
                float var3 = 0.0F;

                for (int k = i - 1; k <= i + 1; ++k) {
                    int r = k & this.tileSizeMask;
                    int g = j & this.tileSizeMask;
                    var3 += this.red[r + g * this.tileSizeBase];
                }

                this.green[i + j * this.tileSizeBase] = var3 / 3.3F + this.blue[i + j * this.tileSizeBase] * 0.8F;
            }

        for (int i = 0; i < this.tileSizeBase; ++i)
            for (int j = 0; j < this.tileSizeBase; ++j) {
                this.blue[i + j * this.tileSizeBase] += this.alpha[i + j * this.tileSizeBase] * 0.05F;

                if (this.blue[i + j * this.tileSizeBase] < 0.0F)
                    this.blue[i + j * this.tileSizeBase] = 0.0F;

                this.alpha[i + j * this.tileSizeBase] -= 0.1F;

                if (Math.random() < 0.05D)
                    this.alpha[i + j * this.tileSizeBase] = 0.5F;
            }

        float af[] = this.green;
        this.green = this.red;
        this.red = af;
        for (int i1 = 0; i1 < this.tileSizeSquare; i1++) {
            float f1 = this.red[i1];
            if (f1 > 1.0F)
                f1 = 1.0F;
            if (f1 < 0.0F)
                f1 = 0.0F;
            float f2 = f1 * f1;
            int r = (int) (this.redMin + f2 * (this.redMax - this.redMin));
            int g = (int) (this.greenMin + f2 * (this.greenMax - this.greenMin));
            int b = (int) (this.blueMin + f2 * (this.blueMax - this.blueMin));
            if (this.anaglyphEnabled) {
                int i3 = (r * 30 + g * 59 + b * 11) / 100;
                int j3 = (r * 30 + g * 70) / 100;
                int k3 = (r * 30 + b * 70) / 100;
                r = i3;
                g = j3;
                b = k3;
            }

            this.imageData[i1 * 4 + 0] = (byte) r;
            this.imageData[i1 * 4 + 1] = (byte) g;
            this.imageData[i1 * 4 + 2] = (byte) b;
            this.imageData[i1 * 4 + 3] = (byte) 255;
        }

    }

    protected float red[];
    protected float green[];
    protected float blue[];
    protected float alpha[];

}
