package com.rgbcraft.indeng.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSmartSafe extends ModelBase {
	ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
  
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
    
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    	super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	Shape1.render(f5);
    	Shape2.render(f5);
    	Shape3.render(f5);
    	Shape4.render(f5);
    	Shape5.render(f5);
    	Shape6.render(f5);
    	Shape7.render(f5);
    	Shape8.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z) {
    	model.rotateAngleX = x;
    	model.rotateAngleY = y;
    	model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    	super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }    
}
