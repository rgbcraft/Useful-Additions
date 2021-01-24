package com.rgbcraft.usefuladditions.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;


@SideOnly(Side.CLIENT)
public class ModelSmartSafe extends ModelBase {

    private ModelRenderer bottom;
    private ModelRenderer top;
    private ModelRenderer back;
    private ModelRenderer left;
    private ModelRenderer right;
    public ModelRenderer door;
    public ModelRenderer handleMainPart;
    public ModelRenderer handleSecondPart;

    public ModelSmartSafe() {
        this.textureWidth = 64;
        this.textureHeight = 128;

        this.bottom = new ModelRenderer(this, 0, 19);
        this.bottom.addBox(0F, 0F, 0F, 14, 3, 14);
        this.bottom.setRotationPoint(-7F, 21F, -7F);
        this.bottom.setTextureSize(64, 128);
        this.bottom.mirror = true;
        this.setRotation(this.bottom, 0F, 0F, 0F);

        this.top = new ModelRenderer(this, 0, 0);
        this.top.addBox(0F, 0F, 0F, 14, 3, 14);
        this.top.setRotationPoint(-7F, 8F, -7F);
        this.top.setTextureSize(64, 128);
        this.top.mirror = true;
        this.setRotation(this.top, 0F, 0F, 0F);

        this.back = new ModelRenderer(this, 41, 38);
        this.back.addBox(0F, 0F, 0F, 8, 10, 3);
        this.back.setRotationPoint(-4F, 11F, 4F);
        this.back.setTextureSize(64, 128);
        this.back.mirror = true;
        this.setRotation(this.back, 0F, 0F, 0F);

        this.left = new ModelRenderer(this, 0, 38);
        this.left.addBox(0F, 0F, 0F, 3, 10, 14);
        this.left.setRotationPoint(-7F, 11F, -7F);
        this.left.setTextureSize(64, 128);
        this.left.mirror = true;
        this.setRotation(this.left, 0F, 0F, 0F);

        this.right = new ModelRenderer(this, 0, 64);
        this.right.addBox(0F, 0F, 0F, 3, 10, 14);
        this.right.setRotationPoint(4F, 11F, -7F);
        this.right.setTextureSize(64, 128);
        this.right.mirror = true;
        this.setRotation(this.right, 0F, 0F, 0F);

        this.door = new ModelRenderer(this, 41, 52);
        this.door.addBox(0F, 0F, 0F, 8, 10, 1);
        this.door.setRotationPoint(-4F, 11F, -6.5F);
        this.door.setTextureSize(64, 128);
        this.door.mirror = true;
        this.setRotation(this.door, 0F, 0F, 0F);

        this.handleMainPart = new ModelRenderer(this, 0, 0);
        this.handleMainPart.addBox(0F, 0F, 0F, 1, 4, 1);
        this.handleMainPart.setRotationPoint(2F, 16F, -8F);
        this.handleMainPart.setTextureSize(64, 128);
        this.handleMainPart.mirror = true;
        this.setRotation(this.handleMainPart, 0F, 0F, 0F);

        this.handleSecondPart = new ModelRenderer(this, 5, 0);
        this.handleSecondPart.addBox(0F, 0F, 0F, 1, 1, 1);
        this.handleSecondPart.setRotationPoint(2F, 16F, -7F);
        this.handleSecondPart.setTextureSize(64, 128);
        this.handleSecondPart.mirror = true;
        this.setRotation(this.handleSecondPart, 0F, 0F, 0F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void render() {
        this.bottom.render(0.0625F);
        this.top.render(0.0625F);
        this.back.render(0.0625F);
        this.left.render(0.0625F);
        this.right.render(0.0625F);
        this.door.render(0.0625F);
        this.handleMainPart.render(0.0625F);
        this.handleSecondPart.render(0.0625F);
    }

}
