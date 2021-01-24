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

        bottom = new ModelRenderer(this, 0, 19);
        bottom.addBox(0F, 0F, 0F, 14, 3, 14);
        bottom.setRotationPoint(-7F, 21F, -7F);
        bottom.setTextureSize(64, 128);
        bottom.mirror = true;
        setRotation(bottom, 0F, 0F, 0F);

        top = new ModelRenderer(this, 0, 0);
        top.addBox(0F, 0F, 0F, 14, 3, 14);
        top.setRotationPoint(-7F, 8F, -7F);
        top.setTextureSize(64, 128);
        top.mirror = true;
        setRotation(top, 0F, 0F, 0F);

        back = new ModelRenderer(this, 41, 38);
        back.addBox(0F, 0F, 0F, 8, 10, 3);
        back.setRotationPoint(-4F, 11F, 4F);
        back.setTextureSize(64, 128);
        back.mirror = true;
        setRotation(back, 0F, 0F, 0F);

        left = new ModelRenderer(this, 0, 38);
        left.addBox(0F, 0F, 0F, 3, 10, 14);
        left.setRotationPoint(-7F, 11F, -7F);
        left.setTextureSize(64, 128);
        left.mirror = true;
        setRotation(left, 0F, 0F, 0F);

        right = new ModelRenderer(this, 0, 64);
        right.addBox(0F, 0F, 0F, 3, 10, 14);
        right.setRotationPoint(4F, 11F, -7F);
        right.setTextureSize(64, 128);
        right.mirror = true;
        setRotation(right, 0F, 0F, 0F);

        door = new ModelRenderer(this, 41, 52);
        door.addBox(0F, 0F, 0F, 8, 10, 1);
        door.setRotationPoint(-4F, 11F, -6.5F);
        door.setTextureSize(64, 128);
        door.mirror = true;
        setRotation(door, 0F, 0F, 0F);

        handleMainPart = new ModelRenderer(this, 0, 0);
        handleMainPart.addBox(0F, 0F, 0F, 1, 4, 1);
        handleMainPart.setRotationPoint(2F, 16F, -8F);
        handleMainPart.setTextureSize(64, 128);
        handleMainPart.mirror = true;
        setRotation(handleMainPart, 0F, 0F, 0F);

        handleSecondPart = new ModelRenderer(this, 5, 0);
        handleSecondPart.addBox(0F, 0F, 0F, 1, 1, 1);
        handleSecondPart.setRotationPoint(2F, 16F, -7F);
        handleSecondPart.setTextureSize(64, 128);
        handleSecondPart.mirror = true;
        setRotation(handleSecondPart, 0F, 0F, 0F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void render() {
        bottom.render(0.0625F);
        top.render(0.0625F);
        back.render(0.0625F);
        left.render(0.0625F);
        right.render(0.0625F);
        door.render(0.0625F);
        handleMainPart.render(0.0625F);
        handleSecondPart.render(0.0625F);
    }

}
