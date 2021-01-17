package com.rgbcraft.usefuladditions.renderers;

import org.lwjgl.opengl.GL11;

import com.rgbcraft.usefuladditions.models.ModelSmartSafe;
import com.rgbcraft.usefuladditions.tiles.TileSmartSafe;
import com.rgbcraft.usefuladditions.utils.Utils;
import com.rgbcraft.usefuladditions.utils.Utils.ResourceType;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class RenderSmartSafe extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

	private ModelSmartSafe modelSmartSafe;
	private ModelSmartSafe modelSmartSafeInventory;
	private int renderId;
	
	public RenderSmartSafe() {
		this.renderId = RenderingRegistry.getNextAvailableRenderId();
		
		this.modelSmartSafe = new ModelSmartSafe();
		this.modelSmartSafeInventory = new ModelSmartSafe();
	}
	
	private void renderSafe(TileEntity tileEntity, double x, double y, double z, float partialTickTime, ModelSmartSafe model) {
		int rotation = 3;
		if (tileEntity != null && tileEntity instanceof TileSmartSafe) {
			bindTextureByName(Utils.getResource(ResourceType.MODEL, "ModelSmartSafe.png"));

			switch (tileEntity.worldObj.getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord)) {
				case 2:
					rotation = 3;
					break;
				case 3:
					rotation = 1;
					break;
				case 4:
					rotation = 2;
					break;
				case 5:
					rotation = 4;
					break;
			}
		}
		
		GL11.glPushMatrix();
		GL11.glEnable(32826);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);

		GL11.glRotatef(rotation * 90, 0.0F, 1.0F, 0.0F);
		
		if (tileEntity != null && tileEntity instanceof TileSmartSafe) {
			TileSmartSafe tileSmartSafe = (TileSmartSafe) tileEntity;
	        float doorAngle = tileSmartSafe.prevDoorAngle + (tileSmartSafe.doorAngle - tileSmartSafe.prevDoorAngle) * partialTickTime;
	        doorAngle = 1.0f - doorAngle;
	        doorAngle = 1.0f - doorAngle * doorAngle * doorAngle;
	        
	        model.door.rotateAngleY = doorAngle * 3.1415927f / 2.0f;
	        
	        // Hide safe handle when open
	        model.handleMainPart.isHidden = doorAngle != 0;
        	model.handleSecondPart.isHidden = doorAngle != 0;
		}

		model.render();
		
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTickTime) {
		this.renderSafe(tileEntity, x, y, z, partialTickTime, this.modelSmartSafe);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glRotatef(270.0f, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        this.renderSafe(null, 0.0, 0.0, 0.0, 0.0f, this.modelSmartSafeInventory);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return this.renderId;
	}
}
