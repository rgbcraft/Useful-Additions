package com.rgbcraft.usefuladditions.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelSmartSafe extends ModelBase {
	ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    public ModelRenderer Shape6;
    public ModelRenderer Shape7;
    public ModelRenderer Shape8;
  
    public ModelSmartSafe() {
    	textureWidth = 64;
    	textureHeight = 128;
    
    	Shape1 = new ModelRenderer(this, 0, 19);
    	Shape1.addBox(0F, 0F, 0F, 14, 3, 14);
    	Shape1.setRotationPoint(-7F, 21F, -7F);
    	Shape1.setTextureSize(64, 128);
    	Shape1.mirror = true;
    	setRotation(Shape1, 0F, 0F, 0F);
    	Shape2 = new ModelRenderer(this, 0, 0);
    	Shape2.addBox(0F, 0F, 0F, 14, 3, 14);
    	Shape2.setRotationPoint(-7F, 8F, -7F);
    	Shape2.setTextureSize(64, 128);
    	Shape2.mirror = true;
    	setRotation(Shape2, 0F, 0F, 0F);
    	Shape3 = new ModelRenderer(this, 41, 38);
    	Shape3.addBox(0F, 0F, 0F, 8, 10, 3);
    	Shape3.setRotationPoint(-4F, 11F, 4F);
    	Shape3.setTextureSize(64, 128);
    	Shape3.mirror = true;
    	setRotation(Shape3, 0F, 0F, 0F);
    	Shape4 = new ModelRenderer(this, 0, 38);
    	Shape4.addBox(0F, 0F, 0F, 3, 10, 14);
    	Shape4.setRotationPoint(-7F, 11F, -7F);
    	Shape4.setTextureSize(64, 128);
    	Shape4.mirror = true;
    	setRotation(Shape4, 0F, 0F, 0F);
    	Shape5 = new ModelRenderer(this, 0, 64);
    	Shape5.addBox(0F, 0F, 0F, 3, 10, 14);
    	Shape5.setRotationPoint(4F, 11F, -7F);
    	Shape5.setTextureSize(64, 128);
    	Shape5.mirror = true;
    	setRotation(Shape5, 0F, 0F, 0F);
    	Shape6 = new ModelRenderer(this, 41, 52);
    	Shape6.addBox(0F, 0F, 0F, 8, 10, 1);
    	Shape6.setRotationPoint(-4F, 11F, -6.5F);
    	Shape6.setTextureSize(64, 128);
    	Shape6.mirror = true;
    	setRotation(Shape6, 0F, 0F, 0F);
    	Shape7 = new ModelRenderer(this, 0, 0);
    	Shape7.addBox(0F, 0F, 0F, 1, 4, 1);
    	Shape7.setRotationPoint(2F, 16F, -8F);
    	Shape7.setTextureSize(64, 128);
    	Shape7.mirror = true;
    	setRotation(Shape7, 0F, 0F, 0F);
    	Shape8 = new ModelRenderer(this, 5, 0);
    	Shape8.addBox(0F, 0F, 0F, 1, 1, 1);
    	Shape8.setRotationPoint(2F, 16F, -7F);
    	Shape8.setTextureSize(64, 128);
    	Shape8.mirror = true;
    	setRotation(Shape8, 0F, 0F, 0F);
    }
    
    public void render() {
    	Shape1.render(0.0625F);
    	Shape2.render(0.0625F);
    	Shape3.render(0.0625F);
    	Shape4.render(0.0625F);
    	Shape5.render(0.0625F);
    	Shape6.render(0.0625F);
    	Shape7.render(0.0625F);
    	Shape8.render(0.0625F);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z) {
    	model.rotateAngleX = x;
    	model.rotateAngleY = y;
    	model.rotateAngleZ = z;
    } 
}
